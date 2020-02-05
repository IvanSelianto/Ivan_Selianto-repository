import org.telegram.telegrambots.api.objects.Message;

public class StartCommand implements Command {
    Bot bot = new Bot();
    @Override
    public void perform(Message message, RuTracker ruTracker) {

        bot.sendMsg(message, "Привет, " + message.getChat().getFirstName() +
                ", я Торрент бот, и я помогу тебе найти интересующий тебя торрент-файл.");

    }
}
