import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreditTargetSetter implements CreditSetterCommand {
    private Map<String, String> commandCodeToTarget = new HashMap();

    @Override
    public void execute(Credit creditClient) {
        commandCodeToTarget.put("1", "Любая");
        commandCodeToTarget.put("2", "На лечение");
        commandCodeToTarget.put("3", "На образование");
        commandCodeToTarget.put("4", "На отдых");
        commandCodeToTarget.put("5", "На покупку товара");
        System.out.println("Какова цель кредита?" +
                "\n1.Любая" +
                "\n2.На лечение" +
                "\n3.На образование" +
                "\n4.На отдых" +
                "\n5.На покупку товара");

        creditClient.setTarget(commandCodeToTarget.get(new Scanner(System.in).next()));
    }

    public Map<String, String> getCommandCodeToTarget() {
        return commandCodeToTarget;
    }
}
