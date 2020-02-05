import java.util.Comparator;

public class SortByTypeOfCollateral implements Comparator<Credit> {

    public int compare(Credit o1, Credit o2) {
        return  o1.getTypeOfCollateral().compareTo(o2.getTypeOfCollateral());
    }
}
