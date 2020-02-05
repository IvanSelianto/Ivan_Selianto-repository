import java.util.Comparator;

public class SortBySum implements Comparator<Credit> {
    public int compare(Credit o1, Credit o2) {
        return (int) (o1.getSum() - o2.getSum());
    }
}
