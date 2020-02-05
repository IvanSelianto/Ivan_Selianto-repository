import org.telegram.telegrambots.api.objects.Message;

public class BooksCommand implements Command {
    Bot bot = new Bot();
    @Override
    public void perform(Message message,  RuTracker ruTracker) {

      ruTracker.setTopicsArray(ruTracker.getBooks());
        bot.sendMsg(message, "Введи поисковой запрос:");

    }
}
