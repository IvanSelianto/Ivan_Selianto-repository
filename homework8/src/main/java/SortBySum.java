import java.util.Collections;
import java.util.Comparator;

public class SortBySum implements Comparator<Credit>, SortCommand {
    public int compare(Credit o1, Credit o2) {
        return (int) (o1.getSum() - o2.getSum());
    }
    @Override
    public void execute(Bank bank) {
        Collections.sort(bank.getFilteredCreditsList(), new SortBySum());
        for (int i = 0; i < bank.getFilteredCreditsList().size(); i++) {
            System.out.println((i + 1) + ". " + bank.getFilteredCreditsList().get(i));

        }

    }
}
