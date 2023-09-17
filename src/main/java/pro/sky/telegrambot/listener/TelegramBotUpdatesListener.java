package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final TelegramBot bot;
    private final List<Command >commands;
    public TelegramBotUpdatesListener(TelegramBot bot,List<Command> commands) {
        this.commands=commands;
        this.bot=bot;
    }
    @PostConstruct
    void unit(){
        bot.setUpdatesListener(this);
    }
    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            logger.debug("Handle update: {}",update);
            commands.stream()
                    .filter(command -> command.ifSuitable(update))
                    .forEach(command -> command.handle(update));
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
