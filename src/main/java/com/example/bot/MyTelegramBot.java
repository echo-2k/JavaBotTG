package com.example.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                Long chatId = message.getChatId();
                SendMessage response = new SendMessage();
                response.setChatId(chatId);
                if ("/start".equals(text)) {
                    String userInfo = String.format("Welcome %s! Your Telegram ID is %d and your username is %s.",
                            message.getFrom().getFirstName(),
                            message.getFrom().getId(),
                            message.getFrom().getUserName());
                    response.setText(userInfo);
                    response.setReplyMarkup(createKeyboard());
                } else {
                    response.setText("Unknown command. Use /start to begin.");
                }
                try {
                    execute(response);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private InlineKeyboardMarkup createKeyboard() {
        InlineKeyboardButton supportButton = new InlineKeyboardButton();
        supportButton.setText("Support");
        supportButton.setCallbackData("support");

        InlineKeyboardButton servicesButton = new InlineKeyboardButton();
        servicesButton.setText("Services");
        servicesButton.setCallbackData("services");

        List<InlineKeyboardButton> row = Arrays.asList(supportButton, servicesButton);
        List<List<InlineKeyboardButton>> rows = Arrays.asList(row);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }
}
