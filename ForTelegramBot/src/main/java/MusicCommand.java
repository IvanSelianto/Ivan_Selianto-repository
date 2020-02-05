import org.telegram.telegrambots.api.objects.Message;

public class MusicCommand implements Command {
    Bot bot = new Bot();
    @Override
    public void perform(Message message, RuTracker ruTracker ){
        ruTracker.setTopicsArray(ruTracker.getMusic());
        bot.sendMsg(bot.getUpdate().getMessage(), "Введи поисковой запрос:");

    }
}
