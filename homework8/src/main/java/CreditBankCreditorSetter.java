import java.util.Scanner;

public class CreditBankCreditorSetter implements CreditSetterCommand {
    @Override
    public void execute(Credit creditClient) {
        System.out.println("Выбери банк кредитор:" +
                "\n1.Любой" +
                "\n2.ВТБ" +
                "\n3.БеларусБанк" +
                "\n4.Москва-Минск банк" +
                "\n5.Тиньков банк");
        switch (new Scanner(System.in).next()) {
            case "1":
                creditClient.setBankCreditor("Любой");
                break;
            case "2":
                creditClient.setBankCreditor("ВТБ");
                break;
            case "3":
                creditClient.setBankCreditor("БеларусБанк");
                break;
            case "4":
                creditClient.setBankCreditor("Москва-Минск банк");
                break;
            case "5":
                creditClient.setBankCreditor("Тиньков банк");
                break;


        }
    }
}
