import java.util.Arrays;

public enum CommandsEnum {


    START("/start", new StartCommand()),
    SEARCH("/search", new SearchCommand()),
    NEXT_PAGE("Следующая страница", new NextPageCommand()),
    PREVIOUS_PAGE("Предыдущая страница", new PreviousPageCommand()),
    FULL_INFO("Full information:", new FullInfoCommand()),
    SEND_TORRENT_FILE("linkNumber", new SendTorrentFileCommand());
    private String code;
    private Command command;

    CommandsEnum(String code, Command command) {
        this.code = code;
        this.command = command;
    }

    public static Command findCommandsEnum(String code) {
        return Arrays.stream(CommandsEnum.values())
                .filter(commandsEnum -> commandsEnum.code.equals(code))
                .findAny()
                .get().command;
    }

}
