import java.util.*;
import java.util.stream.Collectors;


public class Onliner implements Shop {
    private List<Product> products = new ArrayList();
    private Map<Long, Product> idsToProducts = new HashMap<>();
    private Map<Product, Long> productsToAmount = new HashMap<>();

    public List<Product> getProducts() {
        return products;
    }

    public Map<Long, Product> getIdsToProducts() {
        return idsToProducts;
    }

    public void addProduct(Product product) {
        products.add(product);
        idsToProducts.put(product.getId(), product);
    }

    public Paycheck getCheck(List<Long> ids, Shop onliner) {


        Map<Long, Long> idProductToExpectedAmount = ids.stream().collect(Collectors.groupingBy(id -> id, Collectors.counting()));
       idProductToExpectedAmount.keySet().stream()
                .forEach(id -> {
                    if (idProductToExpectedAmount.get(id) > (getProductsToAmount().get(idsToProducts.get(id)))) {
                        ids.remove(id);
                    }
                });

        ids.forEach(id -> {
                    if (products.contains(idsToProducts.get(id))) {
                        products.remove(idsToProducts.get(id));
                    }
                });

        return new Paycheck().getCheck(ids, onliner);
    }

    public Map<Product, Long> getProductsToAmount() {
        productsToAmount = products.stream()
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()));
        return productsToAmount;
    }

}
