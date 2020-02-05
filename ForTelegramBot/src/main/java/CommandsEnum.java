import java.util.Arrays;

public enum CommandsEnum {


    START("/start", new StartCommand()),
    FILMS("Фильмы", new FilmsCommand()),
    BOOKS("Книги", new BooksCommand()),
    MUSIC("Музыка", new MusicCommand()),
    SEARCH("/search", new SearchCommand());
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
