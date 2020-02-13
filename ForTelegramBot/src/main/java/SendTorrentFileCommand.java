import org.telegram.telegrambots.api.objects.Update;

public class SendTorrentFileCommand implements  Command {
    @Override
    public void perform(Update update, RuTracker ruTracker) {
     Bot.getBot().sendTorrentFile(Integer.parseInt(update.getCallbackQuery().getData().split("[A-zА-я]+")[0]), update.getCallbackQuery().getMessage());


    }
}
