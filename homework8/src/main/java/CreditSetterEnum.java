import java.util.Arrays;

public enum CreditSetterEnum {
    SET_SUM("1", new CreditSumSetter()),
    SET_TERM("2", new CreditTermSetter()),
    SET_BANK_CREDITOR("3", new CreditBankCreditorSetter()),
    SET_TYPE_OF_COLLATERAL("4", new CreditTypeOfCollateralSetter()),
    SET_TARGET("5",  new CreditTargetSetter());
    private CreditSetterCommand creditFieldSetter;
    private String code;

    CreditSetterEnum(String code, CreditSetterCommand creditFieldSetter) {
        this.code = code;
        this.creditFieldSetter = creditFieldSetter;

    }

    public static CreditSetterCommand findCommandsEnum(String code) {
        return Arrays.stream(CreditSetterEnum.values())
                .filter(commandsEnum -> commandsEnum.code.equals(code))
                .findAny()
                .get().creditFieldSetter;
    }
}
