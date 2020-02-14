public enum RegexEnum {
    REGEX_FOR_IDS("<a data-topic_id=\"(.*)\""),
    REGEX_FOR_TITLES("\">(.*)</a>"),
    REGEX_FOR_SEEDS("<b class=\"seedmed\">(.+)</b>"),
    REGEX_FOR_LEECHS("<td class=\"row4 leechmed bold\" title=\"Личи\">(.*)</td>"),
    REGEX_FOR_SIZE_IN_BYTES(" <td class=\"row4 small nowrap tor-size\" data-ts_text=\"(\\d.\\d*)\">");

    private final String regex;

    @Override
    public String toString() {
        return regex;
    }

    RegexEnum(String regex) {
        this.regex = regex;
    }
}
