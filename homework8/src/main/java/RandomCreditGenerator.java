import java.util.Random;

public class RandomCreditGenerator {
    private static final Random RANDOM = new Random();
    public Credit creditGenerator() {

        Credit credit = new Credit();
        int target = RANDOM.nextInt(6 - 1) + 1;

        switch (target) {
            case 1:
                credit.setTarget("Любая");
                break;
            case 2:
                credit.setTarget("На лечение");
                break;
            case 3:
                credit.setTarget("На образование");
                break;
            case 4:
                credit.setTarget("На отдых");
                break;
            case 5:
                credit.setTarget("На покупку товара");
                break;
        }

        int typeOfCollateral = RANDOM.nextInt((5 - 1)) + 1;
        switch (typeOfCollateral) {
            case 1:
                credit.setTypeOfCollateral("Любой");
                break;
            case 2:
                credit.setTypeOfCollateral("Гарантийный депозит");
                break;
            case 3:
                credit.setTypeOfCollateral("Залог");
                break;
            case 4:
                credit.setTypeOfCollateral("Поручительство");
                break;
        }

        int bankCreditor = RANDOM.nextInt(6 - 1) + 1;
        switch (bankCreditor) {
            case 1:
                credit.setBankCreditor("Любой");
                break;
            case 2:
                credit.setBankCreditor("ВТБ");
                break;
            case 3:
                credit.setBankCreditor("БеларусБанк");
                break;
            case 4:
                credit.setBankCreditor("Москва-Минск банк");
                break;
            case 5:
                credit.setBankCreditor("Тиньков банк");
                break;
        }

        int sum = RANDOM.nextInt(100001 - 10000) + 10000;
        credit.setSum(Math.floor(sum / 10000) * 10000);

        int term = RANDOM.nextInt(20 - 1) + 1;
        credit.setTerm(6 * term);


        return credit;
    }
}
