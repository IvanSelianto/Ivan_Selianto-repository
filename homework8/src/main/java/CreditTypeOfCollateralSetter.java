import java.util.Scanner;

public class CreditTypeOfCollateralSetter implements CreditSetterCommand {
    @Override
    public void execute(Credit creditClient) {
        System.out.println("Выбери тип обеспечения:" +
                "\n1.Любой" +
                "\n2.Гарантийный депозит" +
                "\n3.Залог" +
                "\n4.Поручительство");
        switch (new Scanner(System.in).next()) {
            case "1":
                creditClient.setTypeOfCollateral("Любой");
                break;
            case "2":
                creditClient.setTypeOfCollateral("Гарантийный депозит");
                break;
            case "3":
                creditClient.setTypeOfCollateral("Залог");
                break;
            case "4":
                creditClient.setTypeOfCollateral("Поручительство");
                break;
        }

    }
}
