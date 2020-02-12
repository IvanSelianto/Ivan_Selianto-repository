import java.util.Collections;
import java.util.Comparator;

public class SortByTypeOfCollateral implements SortCommand {

    @Override
    public void execute(Bank bank) {
        Collections.sort(bank.getFilteredCreditsList(),Comparator.comparing(Credit::getTypeOfCollateral));
        for (int i = 0; i < bank.getFilteredCreditsList().size(); i++) {
            System.out.println((i + 1) + ". " + bank.getFilteredCreditsList().get(i));

        }

    }
}
