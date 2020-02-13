
import org.telegram.telegrambots.api.objects.Update;

public class BooksCommand implements Command {


    @Override
    public void perform(Update update, RuTracker ruTracker) {

      ruTracker.setTopicsArray(ruTracker.getBooks());
        Bot.getBot().sendMsg(update.getMessage(), "Введи поисковой запрос:");

    }
}
