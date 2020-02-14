import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageReplyMarkup;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class PreviousPageCommand implements Command {
    @Override
    public void perform(Update update, RuTrackerLinksCreator ruTrackerLinksCreator) {
        try {
            Bot.getBot().execute(new EditMessageReplyMarkup()
                     .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                     .setChatId(update.getCallbackQuery().getMessage().getChatId())
                     .setReplyMarkup(new InlineKeyboard().sendInlineKeyBoardMessage(Bot.getBot().getLinkNumber()- 5, Bot.getBot().getLinkNumber())));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }
}
