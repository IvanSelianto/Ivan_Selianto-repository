import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreditBankCreditorSetter implements CreditSetterCommand {
 private    Map<String,String> commandCodeToBankCreditor = new HashMap();

    @Override
    public void execute(Credit creditClient) {
        commandCodeToBankCreditor.put("1", "Любой");
        commandCodeToBankCreditor.put("2", "ВТБ");
        commandCodeToBankCreditor.put("3", "БеларусБанк");
        commandCodeToBankCreditor.put("4", "Москва-Минск банк");
        commandCodeToBankCreditor.put("5", "Тиньков банк");
        System.out.println("Выбери банк кредитор:" +
                "\n1.Любой" +
                "\n2.ВТБ" +
                "\n3.БеларусБанк" +
                "\n4.Москва-Минск банк" +
                "\n5.Тиньков банк");
        creditClient.setBankCreditor(commandCodeToBankCreditor.get(new Scanner(System.in).next()));
    }

    public Map<String, String> getCommandCodeToBankCreditor() {
        return commandCodeToBankCreditor;
    }
}
