package com.company;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(new MyBot("7716198470:AAFva7fXXtfBbWgRDzc1OmfxoSl8f99lXu4"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}