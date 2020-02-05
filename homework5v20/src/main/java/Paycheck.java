


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Paycheck {
    private Map<String, Long> productsNameToAmount = new HashMap<>();
    private int cost;

    public Paycheck getCheck(List<Long> ids, Shop shop) {

        productsNameToAmount = ids.stream()
                .map(id -> shop.getIdsToProducts().get(id).getName())
                .collect(Collectors.groupingBy(productName -> productName, Collectors.counting()));
        cost = ids.stream()
                .map(id -> shop.getIdsToProducts().get(id).getCost())
                .mapToInt(subSum -> Integer.parseInt(subSum.toString())).sum();

        return Paycheck.this;

    }

    @Override
    public String toString() {
        return "Paycheck{" +
                "productsNameToAmount=" + productsNameToAmount +
                ", cost=" + cost +
                '}';
    }
}
