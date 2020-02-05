import java.util.Comparator;

public class SortByTarget implements Comparator<Credit> {

    public int compare(Credit o1, Credit o2) {
        return  o1.getTarget().compareTo(o2.getTarget());
    }
}
