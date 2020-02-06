import java.util.Collections;
import java.util.Comparator;

public class SortByTypeOfCollateral implements Comparator<Credit>, SortCommand {

    public int compare(Credit o1, Credit o2) {
        return  o1.getTypeOfCollateral().compareTo(o2.getTypeOfCollateral());
    }
    @Override
    public void execute(Bank bank) {
        Collections.sort(bank.getFilteredCreditsList(), new SortByTypeOfCollateral());
        for (int i = 0; i < bank.getFilteredCreditsList().size(); i++) {
            System.out.println((i + 1) + ". " + bank.getFilteredCreditsList().get(i));

        }

    }
}
