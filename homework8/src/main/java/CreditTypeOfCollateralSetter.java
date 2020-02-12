import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CreditTypeOfCollateralSetter implements CreditSetterCommand {
    private Map<String,String> commandCodeToTypeOfCollateral = new HashMap();
    @Override
    public void execute(Credit creditClient) {
        commandCodeToTypeOfCollateral.put("1", "Любой");
        commandCodeToTypeOfCollateral.put("2", "Гарантийный депозит");
        commandCodeToTypeOfCollateral.put("3", "Залог");
        commandCodeToTypeOfCollateral.put("4", "Поручительство");
        System.out.println("Выбери тип обеспечения:" +
                "\n1.Любой" +
                "\n2.Гарантийный депозит" +
                "\n3.Залог" +
                "\n4.Поручительство");
        creditClient.setTypeOfCollateral(commandCodeToTypeOfCollateral.get(new Scanner(System.in).next()));


    }

    public Map<String, String> getCommandCodeToTypeOfCollateral() {
        return commandCodeToTypeOfCollateral;
    }
}
