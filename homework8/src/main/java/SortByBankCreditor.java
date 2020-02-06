import java.util.Collections;
import java.util.Comparator;

public class SortByBankCreditor implements Comparator<Credit>, SortCommand {

    public int compare(Credit o1, Credit o2) {
        return o1.getBankCreditor().compareTo(o2.getBankCreditor());
    }

    @Override
    public void execute(Bank bank) {
        Collections.sort(bank.getFilteredCreditsList(), new SortByBankCreditor());
        for (int i = 0; i < bank.getFilteredCreditsList().size(); i++) {
            System.out.println((i + 1) + ". " + bank.getFilteredCreditsList().get(i));

        }
    }
}
