import java.util.Random;

public class RandomCreditGenerator {
    private static final Random RANDOM = new Random();

    public Credit creditGenerator() {
        CreditBankCreditorSetter creditBankCreditorSetter = new CreditBankCreditorSetter();
        CreditTargetSetter creditTargetSetter = new CreditTargetSetter();
        CreditTypeOfCollateralSetter creditTypeOfCollateralSetter = new CreditTypeOfCollateralSetter();

        Credit credit = new Credit();
        int target = RANDOM.nextInt(6 - 1) + 1;
        credit.setTarget(creditTargetSetter.getCommandCodeToTarget().get(Integer.toString(target)));


        int typeOfCollateral = RANDOM.nextInt((5 - 1)) + 1;
        credit.setTypeOfCollateral(creditTypeOfCollateralSetter.getCommandCodeToTypeOfCollateral().get(Integer.toString(typeOfCollateral)));

        int bankCreditor = RANDOM.nextInt(6 - 1) + 1;
        credit.setBankCreditor(creditBankCreditorSetter.getCommandCodeToBankCreditor().get(Integer.toString(bankCreditor)));

        int sum = RANDOM.nextInt(100001 - 10000) + 10000;
        credit.setSum(Math.floor(sum / 10000) * 10000);

        int term = RANDOM.nextInt(20 - 1) + 1;
        credit.setTerm(6 * term);


        return credit;
    }
}
