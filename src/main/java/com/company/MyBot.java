package com.company;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class MyBot extends TelegramLongPollingBot {
    public MyBot(String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            String response = getAIResponse(text);

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(response);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String getAIResponse(String userInput) {

//        String text = "You are the Gemini 1.5 Flash API version. Your task is to respond only with the current weather information for Uzbekistan's regions in degrees Celsius. List the weather for each region of Uzbekistan in a simple format. Avoid including unrelated information, additional explanations, or weather data outside Uzbekistan's regions. " + LocalDate.now()  + userInput;
        String text = "You are a translator. Translate the given text from any language to Uzbek. Do not provide explanations or additional comments—just the translated text in Uzbek."  + userInput;
        try {
            HttpClient client = HttpClient.newHttpClient();
            String jsonBody = "{\n" +
                    "  \"contents\": [\n" +
                    "    { \"parts\": [{ \"text\": \"" + text + "\" }] }\n" +
                    "  ]\n" +
                    "}";


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + "AIzaSyDMtzSaDFAVoBJ2Uas_Ew5LtnRzPyhwQ00"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonElement json = JsonParser.parseString(response.body());
                JsonArray array = json.getAsJsonObject().getAsJsonArray("candidates");
                if (array != null && array.size() > 0) {
                    JsonObject candidate = array.get(0).getAsJsonObject();
                    JsonObject content = candidate.getAsJsonObject("content");
                    JsonArray parts = content.getAsJsonArray("parts");
                    if (parts != null && parts.size() > 0) {
                        JsonObject part = parts.get(0).getAsJsonObject();
                        return part.get("text").getAsString();
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "I think there's some problem. Because of that, I can't generate response. Please contact with [Your contact info]";
    }
    @Override
    public String getBotUsername() {
        return "@TranslateBot_1_bot";
    }
}
