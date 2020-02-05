import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import pw.spn.crawler.rutracker.http.RutrackerHttpService;
import pw.spn.crawler.rutracker.model.RutrackerLink;
import java.io.*;
import java.util.*;


public class Bot extends TelegramLongPollingBot {
    private final String BOT_TOKEN = "1033076323:AAEPhpQK12h0RJOWv90GiuYKPJbB64b-n5A";
    private final String BOT_USERNAME = "@DownloaderTorrentBot";
    private Update update = new Update();


    private static RutrackerHttpService rutrackerHttpService = new RutrackerHttpService();
    private RuTracker ruTracker = new RuTracker();
    private List<RutrackerLink> rutrackerLinks = new ArrayList();
    private int linkNumber;


    public static void main(String[] args) {
        rutrackerHttpService.login("pashka-one", "3210310");

        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }


    }


    public InlineKeyboardMarkup sendInlineKeyBoardMessage(int linkNumber, int bound) {


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList();

        if (linkNumber >= rutrackerLinks.size()) {
            linkNumber = 0;
            bound = 5;
        }
        if (linkNumber < 0 && rutrackerLinks.size() % 5 == 0) {
            bound = rutrackerLinks.size();
            linkNumber = bound - 5;

        }

        if (linkNumber < 0) {
            bound = rutrackerLinks.size();
            linkNumber = bound - bound % 5;

        }
        this.linkNumber = linkNumber;
        if (rutrackerLinks.size() % 5 != 0 && bound > rutrackerLinks.size()) {
            bound = rutrackerLinks.size();
        }


        for (; linkNumber < bound; linkNumber++) {
            rowList.add(Arrays.asList(new InlineKeyboardButton()
                    .setText("\u2139" + rutrackerLinks.get(linkNumber).getTitle())
                    .setCallbackData("Full information:")));
            rowList.add(Arrays.asList(new InlineKeyboardButton().setText(
                    "\u2B07 Размер: " + ruTracker.sizeConversion(rutrackerLinks.get(linkNumber).getSizeInBytes()) +
                            " | Сиды: " + rutrackerLinks.get(linkNumber).getSeeds() +
                            " | Личи: " + rutrackerLinks.get(linkNumber).getLeechs()).setCallbackData(Integer.toString(linkNumber))));
        }
        rowList.add(Arrays.asList(

                new InlineKeyboardButton().setText("<-").setCallbackData("Предыдущая страница"),
                new InlineKeyboardButton().setText(bound + "/" + rutrackerLinks.size()).setCallbackData("dd"),
                new InlineKeyboardButton().setText("->").setCallbackData("Следующая страница")));


        return inlineKeyboardMarkup.setKeyboard(rowList);


    }

    public void setButtons(SendMessage sendMessage) {


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Фильмы"));
        keyboardFirstRow.add(new KeyboardButton("Музыка"));
        keyboardFirstRow.add(new KeyboardButton("Книги"));
        keyboardRows.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);


    }


    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {

            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.getText().equals("/start")
                    || message.getText().equals("Фильмы")
                    || message.getText().equals("Книги")
                    || message.getText().equals("Музыка")) {
                CommandsEnum.findCommandsEnum(message.getText()).perform(message, ruTracker);
            } else {
                ruTracker.setSearchResult(rutrackerHttpService.search(message.getText(), ruTracker.getTopicsArray()));
                try {
                    rutrackerLinks = ruTracker.createRutrackerLinks();
                    execute(new SendMessage()
                            .setChatId(message.getChatId())
                            .setText("Результаты поиска:")
                            .setReplyMarkup(sendInlineKeyBoardMessage(0, 5)));
                } catch (Exception e) {
                    e.printStackTrace();
                    sendMsg(message, "Прости, по твоему запросу ничего не найдено.\n\nПопробуй переформулировать.");
                }

            }
        } else if (update.hasCallbackQuery()) {
            try {
                if (update.getCallbackQuery().getData().equals("Следующая страница")) {
                    execute(new EditMessageReplyMarkup()
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                            .setChatId(update.getCallbackQuery().getMessage().getChatId())
                            .setReplyMarkup(sendInlineKeyBoardMessage(linkNumber + 5, linkNumber + 10)));

                }
                if (update.getCallbackQuery().getData().equals("Предыдущая страница")) {
                    execute(new EditMessageReplyMarkup()
                            .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                            .setChatId(update.getCallbackQuery().getMessage().getChatId())
                            .setReplyMarkup(sendInlineKeyBoardMessage(linkNumber - 5, linkNumber)));

                }

                if (update.getCallbackQuery().getData().equals("Full information:")) {
                    execute(new SendMessage().setText(
                            update.getCallbackQuery().getData()).setChatId(update.getCallbackQuery().getMessage().getChatId()));
                    execute(new SendMessage().setText("Название: "
                            + rutrackerLinks.get(linkNumber).getTitle()
                            + "\nТема: "
                            + rutrackerLinks.get(linkNumber).getTopic().getName()
                            + "\nРазмер: "
                            + ruTracker.sizeConversion(rutrackerLinks.get(linkNumber).getSizeInBytes())
                            + "\nЛичи: "
                            + rutrackerLinks.get(linkNumber).getLeechs()
                            + "\nСиды: "
                            + rutrackerLinks.get(linkNumber).getSeeds()
                            + "\nСсылка на источник: "
                            + rutrackerLinks.get(linkNumber).getDownloadUrl()).setChatId(update.getCallbackQuery().getMessage().getChatId()));
                }

                if (update.getCallbackQuery().getData().equals(Integer.toString(linkNumber))) {
                    sendTorrentFile(Integer.parseInt(update.getCallbackQuery().getData()), update.getCallbackQuery().getMessage());
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private Message sendTorrentFile(int fileNumber, Message message) {
        byte[] bytes = rutrackerHttpService.downloadFile(rutrackerLinks.get(fileNumber).getDownloadUrl());
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(message.getChatId().toString());
        File file = new File("torrent.torrent");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(bytes);
            os.close();

            sendDocument.setNewDocument(file);
            return sendDocument(sendDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;

    }


    public Update getUpdate() {
        return update;
    }

}
