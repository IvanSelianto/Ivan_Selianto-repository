import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import pw.spn.crawler.rutracker.http.RutrackerHttpService;

public class SearchCommand implements Command {
   /* Bot bot = new Bot();
    @Override
    public void perform() {
        RuTracker ruTracker = bot.getRuTracker();
        RutrackerHttpService rutrackerHttpService = Bot.getRutrackerHttpService();

        ruTracker.setSearchResult(rutrackerHttpService.search(bot.getUpdate().getMessage().getText(), ruTracker.getTopicsArray()));
        try {
            bot.setRutrackerLinks(ruTracker.createRutrackerLinks());
           bot.execute(new SendMessage()
                    .setChatId(bot.getUpdate().getMessage().getChatId())
                    .setText("Результаты поиска:")
                    .setReplyMarkup(bot.sendInlineKeyBoardMessage(0, 5)));
        } catch (Exception e) {
            e.printStackTrace();
           bot.sendMsg(bot.getUpdate().getMessage(), "Прости, по твоему запросу ничего не найдено.\n\nПопробуй переформулировать.");
        }
    }*/



    @Override
    public void perform(Message message, RuTracker ruTracker) {

    }
}
