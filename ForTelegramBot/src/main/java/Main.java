
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;


public class Main {


    public static void main(String[] args) {

        Bot.getRutrackerHttpService().login("pashka-one", "3210310");

        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(Bot.getBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }

    }

}



