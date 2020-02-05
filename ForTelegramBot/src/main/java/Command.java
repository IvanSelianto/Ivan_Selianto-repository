import org.telegram.telegrambots.api.objects.Message;

public interface Command {
    void perform(Message message , RuTracker ruTracker);
}
