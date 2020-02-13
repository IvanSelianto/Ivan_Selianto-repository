import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;

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
    private static Bot instance;

    public static RutrackerHttpService getRutrackerHttpService() {
        return rutrackerHttpService;
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

    public static void main(String[] args) {
        rutrackerHttpService.login("pashka-one", "3210310");

        ApiContextInitializer.init();
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(Bot.getBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }


    }

    private Bot() {

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


        while (linkNumber < bound) {
            rowList.add(Arrays.asList(new InlineKeyboardButton()
                    .setText("\u2139" + rutrackerLinks.get(linkNumber).getTitle())
                    .setCallbackData("Full information:")));
            rowList.add(Arrays.asList(new InlineKeyboardButton().setText(
                    "\u2B07 Размер: " + ruTracker.sizeConversion(rutrackerLinks.get(linkNumber).getSizeInBytes()) +
                            " | Сиды: " + rutrackerLinks.get(linkNumber).getSeeds() +
                            " | Личи: " + rutrackerLinks.get(linkNumber).getLeechs()).setCallbackData(linkNumber + "linkNumber")));
            linkNumber++;
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
                CommandsEnum.findCommandsEnum(message.getText()).perform(update, ruTracker);
            } else {
                CommandsEnum.findCommandsEnum("/search").perform(update, ruTracker);

            }
        } else if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("Предыдущая страница")
                    || update.getCallbackQuery().getData().equals("Следующая страница")
                    || update.getCallbackQuery().getData().equals("Full information:")) {
                CommandsEnum.findCommandsEnum(update.getCallbackQuery().getData()).perform(update, ruTracker);
            } else {
                CommandsEnum.findCommandsEnum(update.getCallbackQuery().getData().split("\\d")[1]).perform(update, ruTracker);
            }
        }
    }

    public Message sendTorrentFile(int fileNumber, Message message) {
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
