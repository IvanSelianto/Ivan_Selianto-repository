
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import pw.spn.crawler.rutracker.model.RutrackerLink;

import java.util.List;

public class FullInfoCommand implements Command {
    @Override
    public void perform(Update update, RuTrackerLinksCreator ruTrackerLinksCreator) {
        List<RutrackerLink> rutrackerLinks = Bot.getBot().getRutrackerLinks();
        int linkNumber = Bot.getBot().getLinkNumber();
        try {
            Bot.getBot().execute(new SendMessage().setText(
                    update.getCallbackQuery().getData()).setChatId(update.getCallbackQuery().getMessage().getChatId()));
            Bot.getBot().execute(new SendMessage().setText("Название: "
                    +  rutrackerLinks.get(linkNumber).getTitle()
                    + "\nТема: "
                    + rutrackerLinks.get(linkNumber).getTopic().getName()
                    + "\nРазмер: "
                    + new UnitConversionFileSize().unitConversion(rutrackerLinks.get(linkNumber).getSizeInBytes())
                    + "\nЛичи: "
                    + rutrackerLinks.get(linkNumber).getLeechs()
                    + "\nСиды: "
                    + rutrackerLinks.get(linkNumber).getSeeds()
                    + "\nСсылка на источник: "
                    + rutrackerLinks.get(linkNumber).getDownloadUrl()).setChatId(update.getCallbackQuery().getMessage().getChatId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
