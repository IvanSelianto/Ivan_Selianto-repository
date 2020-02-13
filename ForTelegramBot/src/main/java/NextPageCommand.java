import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageReplyMarkup;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class NextPageCommand implements Command {
    @Override
    public void perform(Update update, RuTracker ruTracker)  {
        try {
            Bot.getBot().execute(new EditMessageReplyMarkup()
                      .setMessageId( update.getCallbackQuery().getMessage().getMessageId())
                      .setChatId(update.getCallbackQuery().getMessage().getChatId())
                      .setReplyMarkup(Bot.getBot().sendInlineKeyBoardMessage( Bot.getBot().getLinkNumber() + 5, Bot.getBot().getLinkNumber() + 10)));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
