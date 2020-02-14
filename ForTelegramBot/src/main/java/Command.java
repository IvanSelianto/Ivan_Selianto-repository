import org.telegram.telegrambots.api.objects.Update;

public interface Command {
    void perform(Update update, RuTrackerLinksCreator ruTrackerLinksCreator);
}
