import java.util.Scanner;

public class CreditSumSetter implements CreditSetterCommand {

    @Override
    public void execute(Credit creditClient) {
        System.out.println("Введи сумму кредита");
        creditClient.setSum(new Scanner(System.in).nextDouble());

    }
}
