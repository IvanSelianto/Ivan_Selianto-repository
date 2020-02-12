import java.util.Arrays;
import java.util.Comparator;

public enum CommandsSortEnum {
    SORT_BY_SUM("1", new SortBySum()),
    SORT_BY_TERM("2", new SortByTerm()),
    SORT_BY_BANK_CREDITOR("3", new SortByBankCreditor()),
    SORT_BY_TYPE_OF_COLLATERAL("4", new SortByTypeOfCollateral()),
    SORT_BY_TARGET("5", new SortByTarget());
    private String code;
    private SortCommand comparator;

    CommandsSortEnum(String code, SortCommand comparator) {
        this.code = code;
        this.comparator = comparator;

    }

    public static SortCommand findCommandsEnum(String code) {
        return Arrays.stream(CommandsSortEnum.values())
                .filter(commandsEnum -> commandsEnum.code.equals(code))
                .findAny()
                .get().comparator;
    }

}
