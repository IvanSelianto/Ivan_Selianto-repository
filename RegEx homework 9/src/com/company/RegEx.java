package com.company;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
    private final String REGEX_FOR_FIND_SENTENCES_WITH_ONE_WORD = "[А-Я0-9][а-я]++[.?!]";
    private final String REGEX_FOR_VALIDATE_NUMBER = "(\\+375|375)(\\d{2}|\\(\\d{2}\\))(\\d{3}| \\d{3})(\\d{2}| \\d{2}|-\\d{2})(\\d{2}| \\d{2}|-\\d{2})";
    private final String REGEX_FOR_FIND_DATE = "\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}:\\d{2}";
    private final String REGEX_FOR_VALIDATE_WEBSITE = "(https://([A-z]+\\.)([[.]A-z]+)[.\\w])|(([A-z]+\\.)([[.]A-z]+)[.\\w])";

    private String text = "Регулярные. Выражения? — тема, которую программисты, даже опытные, зачастую откладывают на потом." +
            " Однако большинству Java-разработчиков рано или поздно придётся столкнуться с обработкой текстовой информации." +
            " Чаще всего — с операциями поиска в тексте и редактированием." +
            " Без регулярных выражений продуктивный и компактный программный код, связанный с обработкой текстов, попросту немыслим." +
            " Так что хватит откладывать, разберёмся с «регулярками» прямо сейчас. Это не такая уж и сложная задача." +
            "Что такое регулярное выражение RegEx?\n" +
            "На! самом деле регулярное выражение – это шаблон для поиска строки в тексте." +
            " В Java исходным представлением этого шаблона всегда является строка, то есть объект класса String." +
            " Однако не любая строка может быть скомпилирована в регулярное выражение, а только та, которая соответствует правилам написания регулярного выражения" +
            " – синтаксису, определенному в спецификации языка.\n" +
            "\n" +
            "Для написания регулярного выражения используются буквенные и цифровые символы, а также метасимволы" +
            " – символы, имеющие специальное значение в синтаксисе регулярных выражений.";

    public String findWordsByLetter(char letter) {
        return finder(" " + letter + "[а-я]++", text);
    }

    public String findSentencesWithOneWord() {
        return finder(REGEX_FOR_FIND_SENTENCES_WITH_ONE_WORD, text);
    }

    public String checkMobileNumber(String mobileNumber) {
        return finder(REGEX_FOR_VALIDATE_NUMBER, mobileNumber);
    }

    public String dateFinder(String date) {
        return finder(REGEX_FOR_FIND_DATE, date);
    }

    public String webSiteChecker(String site) {
        return finder(REGEX_FOR_VALIDATE_WEBSITE, site);
    }

    private String finder(String regEx, String text) {
        String group = "";
        Pattern pattern = Pattern.compile(regEx);

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            group += matcher.group() + " ";
        }
        return group;
    }
}

