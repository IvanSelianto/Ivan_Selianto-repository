import org.telegram.telegrambots.api.methods.send.SendDocument;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class TorrentFileSender {
    public Message sendTorrentFile(int fileNumber, Message message) {
        byte[] bytes = Bot.getRutrackerHttpService().downloadFile(Bot.getBot().getRutrackerLinks().get(fileNumber).getDownloadUrl());
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(message.getChatId().toString());
        File file = new File("torrent.torrent");
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(bytes);
            os.close();
            return new DefaultAbsSender(new DefaultBotOptions()) {
                @Override
                public String getBotToken() {
                    return Bot.getBot().getBotToken();
                }
            }.sendDocument(sendDocument.setNewDocument(file));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
