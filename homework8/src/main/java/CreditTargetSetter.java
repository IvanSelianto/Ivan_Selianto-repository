import java.util.Scanner;

public class CreditTargetSetter implements CreditSetterCommand {
    @Override
    public void execute(Credit creditClient) {
        System.out.println("Какова цель кредита?" +
                "\n1.Любая" +
                "\n2.На лечение" +
                "\n3.На образование" +
                "\n4.На отдых" +
                "\n5.На покупку товара");
        switch (new Scanner(System.in) .next()) {
            case "1":
                creditClient.setTarget("Любой");
                break;
            case "2":
                creditClient.setTarget("На лечение");
                break;
            case "3":
                creditClient.setTarget("На образование");
                break;
            case "4":
                creditClient.setTarget("На отдых");
                break;
            case "5":
                creditClient.setTarget("На покупку товара");
                break;


        }
    }
}
