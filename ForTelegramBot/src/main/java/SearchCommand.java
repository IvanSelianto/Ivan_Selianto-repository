import org.telegram.telegrambots.api.methods.send.SendMessage;

import org.telegram.telegrambots.api.objects.Update;


public class SearchCommand implements Command {
    @Override
    public void perform(Update update, RuTracker ruTracker) {
        ruTracker.setSearchResult(Bot.getRutrackerHttpService().search(update.getMessage().getText(), ruTracker.getTopicsArray()));
        try {

            Bot.getBot().setRutrackerLinks(ruTracker.createRutrackerLinks());
           Bot.getBot().execute(new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Результаты поиска:")
                    .setReplyMarkup(Bot.getBot().sendInlineKeyBoardMessage(0, 5)));
        } catch (Exception e) {
            e.printStackTrace();
           Bot.getBot().sendMsg(update.getMessage(), "Прости, по твоему запросу ничего не найдено.\n\nПопробуй переформулировать.");
        }

    }



}
