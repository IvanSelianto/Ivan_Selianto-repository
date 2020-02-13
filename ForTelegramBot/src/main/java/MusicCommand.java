import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

public class MusicCommand implements Command {


    @Override
    public void perform(Update update, RuTracker ruTracker){
        ruTracker.setTopicsArray(ruTracker.getMusic());
        Bot.getBot().sendMsg(Bot.getBot().getUpdate().getMessage(), "Введи поисковой запрос:");

    }
}
