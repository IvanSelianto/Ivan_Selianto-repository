import org.telegram.telegrambots.api.objects.Message;

public class FilmsCommand implements Command {
    Bot bot = new Bot();
    @Override
    public void perform(Message message, RuTracker ruTracker) {

       ruTracker.setTopicsArray(ruTracker.getFilms());
        bot.sendMsg(message, "Введи поисковой запрос:");

    }
}
