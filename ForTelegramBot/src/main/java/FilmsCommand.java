import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class FilmsCommand implements Command {


    @Override
    public void perform(Update update, RuTracker ruTracker) {

       ruTracker.setTopicsArray(ruTracker.getFilms());
        Bot.getBot().sendMsg(update.getMessage(), "Введи поисковой запрос:");

    }
}
