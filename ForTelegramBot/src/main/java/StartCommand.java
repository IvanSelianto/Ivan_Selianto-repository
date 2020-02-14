
import org.telegram.telegrambots.api.objects.Update;

public class StartCommand implements Command {
    @Override
    public void perform(Update update, RuTrackerLinksCreator ruTrackerLinksCreator) {

        Bot.getBot().sendMsg(update.getMessage(), "Привет, " + update.getMessage().getChat().getFirstName() +
                ", я Торрент бот, и я помогу тебе найти интересующий тебя торрент-файл.\n\n Введи поисковой запрос:");

    }
}
