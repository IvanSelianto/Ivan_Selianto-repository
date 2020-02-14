import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import pw.spn.crawler.rutracker.http.RutrackerHttpService;
import pw.spn.crawler.rutracker.model.RutrackerLink;


import java.util.*;


public class Bot extends TelegramLongPollingBot {
    private final String BOT_TOKEN = "1033076323:AAEPhpQK12h0RJOWv90GiuYKPJbB64b-n5A";
    private final String BOT_USERNAME = "@DownloaderTorrentBot";


    private static RutrackerHttpService rutrackerHttpService = new RutrackerHttpService();
    private RuTrackerLinksCreator ruTrackerLinksCreator = new RuTrackerLinksCreator();
    private List<RutrackerLink> rutrackerLinks = new ArrayList();


    private static Bot instance;
    private int linkNumber;

    public static RutrackerHttpService getRutrackerHttpService() {
        return rutrackerHttpService;
    }

    private Bot() {

    }

    public static Bot getBot() {
        if (instance == null) {
            instance = new Bot();
        }
        return instance;
    }

    public void setLinkNumber(int linkNumber) {
        this.linkNumber = linkNumber;
    }

    public int getLinkNumber() {
        return linkNumber;
    }

    public List<RutrackerLink> getRutrackerLinks() {
        return rutrackerLinks;
    }

    public void setRutrackerLinks(List<RutrackerLink> rutrackerLinks) {
        this.rutrackerLinks = rutrackerLinks;
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {

            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.getText().equals("/start")) {
                CommandsEnum.findCommandsEnum(message.getText()).perform(update, ruTrackerLinksCreator);
            } else {
                CommandsEnum.findCommandsEnum("/search").perform(update, ruTrackerLinksCreator);

            }
        } else if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("Предыдущая страница")
                    || update.getCallbackQuery().getData().equals("Следующая страница")
                    || update.getCallbackQuery().getData().equals("Full information:")) {
                CommandsEnum.findCommandsEnum(update.getCallbackQuery().getData()).perform(update, ruTrackerLinksCreator);
            } else {
                CommandsEnum.findCommandsEnum(update.getCallbackQuery().getData().split("\\d+")[1]).perform(update, ruTrackerLinksCreator);
            }
        }
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;

    }


}
