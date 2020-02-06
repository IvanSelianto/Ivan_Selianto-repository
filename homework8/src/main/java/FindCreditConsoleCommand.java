import java.util.List;
import java.util.Scanner;

public class FindCreditConsoleCommand implements ConsoleCommand {
    @Override
    public void execute(String string, Credit creditClient, Bank bank) {
        Scanner scanner = new Scanner(System.in);
        Writer writer = new Writer();
        System.out.println("Вот предложения кредитов, возможно подходящих тебе:");
        System.out.println(creditClient);
        List<Credit> credits = bank.findCredit(creditClient);
        for (int i = 0; i < credits.size(); i++) {
            System.out.println((i + 1) + ". " + credits.get(i));

        }
        System.out.println("1.Выбрать кредит" +
                "\n2.Сортировать список кредитов");


        int consoleInt = scanner.nextInt();
        if (consoleInt == 1) {
            System.out.println("Выбери интересующий тебя кредит");

            int creditNumber = scanner.nextInt() - 1;
            creditClient = bank.getFilteredCreditsList().get(creditNumber);
            System.out.println(creditClient);
            System.out.println("Договор об оформлении кредита был сохранен в отдельном файле в корневой файловой директории.");
            String contract = "Договор\n" +
                    "1. Кредитор " + creditClient.getBankCreditor()
                    + " предоставляет Заемщику кредит в сумме " + creditClient.getSum()
                    + " рублей на срок " + creditClient.getTerm()
                    + " на " + creditClient.getTarget()
                    + "\n Типом обеспечения в данном случае является " + creditClient.getTypeOfCollateral();
            writer.write("Contract.txt", contract);
            ConsoleMenu.clientRequest = "exit";


        } else {

            System.out.println("Сортировать по:" +
                    "\n1. По сумме" +
                    "\n2. По сроку кредита" +
                    "\n3. По банку кредитору(по алфавиту)" +
                    "\n4. По типу обеспечению(по алфавиту) " +
                    "\n5. По цели(по алфавиту)");
            String sortString = scanner.next();
            CommandsSortEnum.findCommandsEnum(sortString).execute(bank);

            System.out.println("Выбери интересующий тебя кредит");
            int creditNumber = scanner.nextInt() - 1;
            creditClient = bank.getFilteredCreditsList().get(creditNumber);
            System.out.println(creditClient);
            System.out.println("Договор об оформлении кредита был сохранен в отдельном файле в корневой файловой директории.");
            ConsoleMenu.clientRequest = "exit";
        }

    }
}
