# Copyright 2018 Google LLC
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import torch
import torch.nn.functional as F
# [START gae_python38_app]
# [START gae_python3_app]
from flask import Flask
# pylint: disable=no-name-in-module
from flask import request
# import huggingface transformers
from transformers import GPT2LMHeadModel, GPT2Tokenizer, GPT2Config


# from google_trans_new import google_translator

# If `entrypoint` is not defined in app.yaml, App Engine will look for an app
# called `app` in `main.py`.


def top_filtering(logits, top_k=0, top_p=0.0, filter_value=-float('Inf')):
    """ Filter a distribution of logits using top-k, top-p (nucleus) and/or threshold filtering
        Args:
            logits: logits distribution shape (vocabulary size)
            top_k: <=0: no filtering, >0: keep only top k tokens with highest probability.
            top_p: <=0.0: no filtering, >0.0: keep only a subset S of candidates, where S is the smallest subset
                whose total probability mass is greater than or equal to the threshold top_p.
                In practice, we select the highest probability tokens whose cumulative probability mass exceeds
                the threshold top_p.
    """
    # batch support!
    if top_k > 0:
        # pylint: disable=no-member
        values, _ = torch.topk(logits, top_k)
        min_values = values[:, -1].unsqueeze(1).repeat(1, logits.shape[-1])
        logits = torch.where(logits < min_values,
                             torch.ones_like(logits, dtype=logits.dtype) * -float('Inf'),
                             logits)
    if top_p > 0.0:
        # Compute cumulative probabilities of sorted tokens
        # pylint: disable=no-member
        sorted_logits, sorted_indices = torch.sort(logits, descending=True)
        # pylint: disable=no-member
        cumulative_probabilities = torch.cumsum(F.softmax(sorted_logits, dim=-1), dim=-1)

        # Remove tokens with cumulative probability above the threshold
        sorted_indices_to_remove = cumulative_probabilities > top_p
        # Shift the indices to the right to keep also the first token above the threshold
        sorted_indices_to_remove[..., 1:] = sorted_indices_to_remove[..., :-1].clone()
        sorted_indices_to_remove[..., 0] = 0

        sorted_logits = sorted_logits.masked_fill_(sorted_indices_to_remove, filter_value)
        logits = torch.zeros_like(logits).scatter(1, sorted_indices, sorted_logits)

    return logits


# [END gae_python3_app]
# [END gae_python38_app]
gpt2_small_config = GPT2Config()
gpt2_medium_config = GPT2Config(n_ctx=1024, n_embd=1024, n_layer=24, n_head=16)
gpt2_large_config = GPT2Config(n_ctx=1024, n_embd=1280, n_layer=36, n_head=20)

# load the tokenizer
tokenizer = GPT2Tokenizer.from_pretrained("gpt2")

# load the model
model_size = "small"
model = GPT2LMHeadModel(gpt2_small_config)

# model.load_state_dict(torch.load("small_ft.pkl"), strict=False)
# model.load_state_dict(torch.load("pytorch_model.bin", map_location='cpu'), strict=False)
model.load_state_dict(torch.load("pytorch_model_skiny.bin", map_location='cpu'), strict=False)
# pylint: disable=no-member
device = torch.device("cpu")  # torch.device("cuda")
model = model.to(device)

# beg huggingface not to change this anymore
model.lm_head.weight.data = model.transformer.wte.weight.data

# translator = google_translator()

eos = [tokenizer.encoder["<|endoftext|>"]]

past = None
temperature = 0.95
top_k = -1
top_p = 0.9

model.eval()
prev_input = None


def create_sentence(seed: str) -> str:
    print(f"Creat sentence called with: {seed}")
    global past
    if seed == "":
        return ""
    with torch.no_grad():
        # input and update B's utterance

        user = tokenizer.encode(seed)
        prev_input = user

        prev_input = torch.LongTensor(prev_input).unsqueeze(0).to(device)
        _, past = model(prev_input, past=past)

        prev_input = torch.LongTensor([eos]).to(device)

        sent = []
        for i in range(500):
            logits, past = model(prev_input, past=past)
            logits = logits[:, -1, :] / temperature
            logits = top_filtering(logits, top_k=top_k, top_p=top_p)

            probs = torch.softmax(logits, dim=-1)

            prev_input = torch.multinomial(probs, num_samples=1)
            prev_word = prev_input.item()

            if prev_word == eos[0]:
                break
            sent.append(prev_word)

        result = tokenizer.decode(sent)
        print(f"Result: {result}")
        # print("Bot:", translator.translate(tokenizer.decode(sent), lang_src='en', lang_tgt='fr'))

        prev_input = torch.LongTensor([eos]).to(device)
        _, past = model(prev_input, past=past)

        return result
