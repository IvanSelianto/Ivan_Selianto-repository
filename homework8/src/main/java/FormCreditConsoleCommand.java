import java.util.Scanner;

public class FormCreditConsoleCommand implements ConsoleCommand {


    @Override
    public void execute(String string, Credit creditClient, Bank bank) {
        boolean isExit =false;
        while (!isExit) {
            if (string.equals("1") || string.equals("+")) {
                System.out.println("Выбери один из интересущих тебя параметров." +
                        " Для подбора кредита по назначенным тобой параметрам напиши \"find\". Для добавления еще одного параметра напиши \"+ \"" +
                        "\n1.Сумма кредита;" +
                        "\n2.Срок кредита" +
                        "\n3.Банк кредитор" +
                        "\n4.Тип обеспечения" +
                        "\n5.Цель");
                string = new Scanner(System.in).next();
            }

            CreditSetterEnum.findCommandsEnum(string).execute(creditClient);
            string = new Scanner(System.in).next();
            if(string.equals("find")){
                ConsoleMenu.clientRequest ="find";
                isExit = true;

            }

        }
    }
}
