package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.model.Update;

public interface Command {
    void handle(Update update);

    boolean ifSuitable(Update update);

}
