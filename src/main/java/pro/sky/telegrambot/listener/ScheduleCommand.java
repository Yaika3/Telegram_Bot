package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ScheduleCommand implements Command{
    private static final DateTimeFormatter DATA_TIME_FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy HH:mm");
    private static final Pattern PATTERN = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    @Override
    public void handle(Update update) {
       var matcher= PATTERN.matcher(update.message().text());
       if (matcher.matches()){
          var dataTime = LocalDateTime.parse(matcher.group(1));

           var text = matcher.group(3);
       }

    }

    @Override
    public boolean ifSuitable(Update update) {
       return Optional.of(update)
                .map(Update::message)
                .map(Message::text)
                .map(PATTERN :: matcher)
                .map(Matcher::matches)
                .orElse(false);


    }

}
//53 33