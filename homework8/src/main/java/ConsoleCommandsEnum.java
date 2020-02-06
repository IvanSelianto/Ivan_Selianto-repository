import java.util.Arrays;

public enum ConsoleCommandsEnum {
    FORM_CREDIT("1", new FormCreditConsoleCommand()),
    FIND_CREDIT("find", new FindCreditConsoleCommand());

    private String code;
    private ConsoleCommand consoleCommand;

    ConsoleCommandsEnum(String code, ConsoleCommand consoleCommand) {
        this.code = code;
        this.consoleCommand = consoleCommand;

    }

    public static ConsoleCommand findCommandsEnum(String code) {
        return Arrays.stream(ConsoleCommandsEnum.values())
                .filter(commandsEnum -> commandsEnum.code.equals(code))
                .findAny()
                .get().consoleCommand;
    }

}
