import java.util.*;

public class ConsoleMenu {
    private static final String EXIT_COMMAND = "exit";
    public static String clientRequest;

    public void showConsoleMenu() {
        Bank bank = new Bank();
        Credit creditClient = new Credit();
        System.out.println("Вас привествует контора по подбору кредитов!");
        System.out.println("1. Сформировать потребительский кредит\n2. Выйти из программы");
        try (
                Scanner scanner = new Scanner(System.in)) {
            boolean isExit = false;
            clientRequest= scanner.next();
            while (!isExit) {
                try {
                    if (clientRequest.equals(EXIT_COMMAND) || clientRequest.equals("2")) {
                        isExit = true;
                        continue;
                    }
                    ConsoleCommandsEnum.findCommandsEnum(clientRequest).execute(clientRequest, creditClient, bank);
                } catch (InputMismatchException e) {
                    System.out.println("Ты ввел фигню, давай по новой");
                    scanner.nextLine();
                }

            }
        }
        bank.setCredit(creditClient);
    }
}
