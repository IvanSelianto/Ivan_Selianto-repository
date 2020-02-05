import java.util.Comparator;

public class SortByTerm implements Comparator<Credit> {

    public int compare(Credit o1, Credit o2) {
        return o1.getTerm()-o2.getTerm();
    }
}
