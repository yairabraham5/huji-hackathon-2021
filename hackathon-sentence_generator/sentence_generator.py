# # from transformers import AutoModelForCausalLM, AutoTokenizer, GPT2LMHeadModel, GPT2Tokenizer, GPT2Config
# # import torch
# from google_trans_new import google_translator
#
# # import gradio as gr
#
# # tokenizer = AutoTokenizer.from_pretrained("microsoft/DialoGPT-small")
# # model = AutoModelForCausalLM.from_pretrained("microsoft/DialoGPT-large")
# # gpt2_small_config = GPT2Config()
# # model = GPT2LMHeadModel(gpt2_small_config)
#
# # model.load_state_dict(torch.load("small_ft.pkl"), strict=False)
# # model.load_state_dict(torch.load("pytorch_model.bin", map_location='cpu'), strict=False)
# # model.load_state_dict(torch.load("pytorch_model_skiny.bin", map_location='cpu'), strict=False)
# # model.eval()
#
# def dialogpt(text):
#     # encode the new user input, add the eos_token and return a tensor in Pytorch
#     for step in range(50000):
#
#         new_user_input_ids = tokenizer.encode(text + tokenizer.eos_token, return_tensors='pt')
#
#         # append the new user input tokens to the chat history
#         bot_input_ids = torch.cat([chat_history_ids, new_user_input_ids], dim=-1) if step > 0 else new_user_input_ids
#
#         # generated a response while limiting the total chat history to 1000 tokens,
#         chat_history_ids = model.generate(bot_input_ids, max_length=1000, pad_token_id=tokenizer.eos_token_id)
#
#         # pretty print last ouput tokens from bot
#         return tokenizer.decode(chat_history_ids[:, bot_input_ids.shape[-1]:][0], skip_special_tokens=True)
#
#
# if __name__ == "__main__":
#     # demo(25)
#     # print(dialogpt("Does money buy happiness?"))
#     # print(dialogpt("What is the best way to buy happiness ?"))
#     # print(dialogpt("This is so difficult !"))
#     # print(dialogpt("Don't be, you are great! What is your name?"))
#     # print(dialogpt("This is so difficult !"))
#     # print(dialogpt("This is so difficult !"))
#     translator = google_translator()
#     txt = translator.translate('how are you?', lang_src='en', lang_tgt='he')
#     print(txt)




