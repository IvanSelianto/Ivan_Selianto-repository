import java.util.Scanner;

public class CreditTermSetter implements CreditSetterCommand {


    @Override
    public void execute(Credit creditClient) {
        System.out.println("Введи желаемый срок выплаты кредита(месяцы)");
        creditClient.setTerm((int) Math.ceil(new Scanner(System.in) .nextDouble() / 6) * 6);

    }
}
