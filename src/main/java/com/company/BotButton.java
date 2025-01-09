package com.company;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BotButton {

    public ReplyKeyboardMarkup createReplyKeyboard() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Yordam"));
        row1.add(new KeyboardButton("Qo'llanma"));
        KeyboardRow row =new KeyboardRow();
        row.add(new KeyboardButton("Tarjima qilish"));

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);
        keyboard.add(row);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }
}
