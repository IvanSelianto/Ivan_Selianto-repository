import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Amazon implements Shop {
    private int counter;
    private Map<Product, Integer> productsToAmount = new HashMap<>();
    private Map<Long, Product> idsToProducts = new HashMap<>();

    public Map<Product, Integer> getProductsToAmount() {
        System.out.println();
        return productsToAmount;
    }

    @Override
    public void addProduct(Product product) {
        if (productsToAmount.containsKey(product)) {
            counter++;
        } else {
            counter = 1;
        }
        productsToAmount.put(product, counter);
        idsToProducts.put(product.getId(), product);


    }

    public Paycheck getCheck(List<Long> ids, Shop amazon) {
        Map<Long, Long> idProductToExpectedAmount = ids.stream().collect(Collectors.groupingBy(id -> id, Collectors.counting()));
        idProductToExpectedAmount.keySet().stream()
                .forEach(id -> {
                    if (idProductToExpectedAmount.get(id) > (getProductsToAmount().get(idsToProducts.get(id)))) {
                        ids.remove(id);
                    }
                });

        ids.forEach(id -> {

            if (productsToAmount.containsKey(idsToProducts.get(id))) {
                productsToAmount.remove(idsToProducts.get(id));
            }
        });
        return new Paycheck().getCheck(ids, amazon);
    }

    @Override
    public Map<Long, Product> getIdsToProducts() {
        return idsToProducts;
    }


}
