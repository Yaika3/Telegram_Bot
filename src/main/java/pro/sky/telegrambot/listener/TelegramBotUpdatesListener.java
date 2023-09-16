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

    @Autowired
    private final TelegramBot bot;
//    private final Map<String, Command> commands;
    private final List<Command >commands;

    public TelegramBotUpdatesListener(TelegramBot bot,List<Command> commands) {
        this.bot = bot;
//        this.commands=commands.stream().collect(Collectors.toMap(Command::commandText, Function.identity()));
        this.commands=commands;
    }

    @PostConstruct
    public void init() {
        bot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            commands.stream()
                    .filter(command -> command.ifSuitable(update))
                    .forEach(command -> command.handle(update));


//            var command = commands.get(update.message().text());
//            if (command!= null){
//                command.handle(update);
//            }

        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
