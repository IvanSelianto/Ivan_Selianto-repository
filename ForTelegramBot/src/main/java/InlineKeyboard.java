import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import pw.spn.crawler.rutracker.model.RutrackerLink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InlineKeyboard {
    public InlineKeyboardMarkup sendInlineKeyBoardMessage(int linkNumber, int bound) {
        List<RutrackerLink> rutrackerLinks = Bot.getBot().getRutrackerLinks();
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
        Bot.getBot().setLinkNumber(linkNumber);
        if (rutrackerLinks.size() % 5 != 0 && bound > rutrackerLinks.size()) {
            bound = rutrackerLinks.size();
        }


        while (linkNumber < bound) {
            rowList.add(Arrays.asList(new InlineKeyboardButton()
                    .setText("\u2139" + rutrackerLinks.get(linkNumber).getTitle())
                    .setCallbackData("Full information:")));
            rowList.add(Arrays.asList(new InlineKeyboardButton().setText(
                    "\u2B07 Размер: " + new UnitConversionFileSize().unitConversion(rutrackerLinks.get(linkNumber).getSizeInBytes()) +
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
}
