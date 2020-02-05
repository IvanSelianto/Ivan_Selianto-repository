import java.util.Comparator;

public class SortByBankCreditor implements Comparator<Credit> {

    public int compare(Credit o1, Credit o2) {
        return o1.getBankCreditor().compareTo(o2.getBankCreditor());
    }
}
