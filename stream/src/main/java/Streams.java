import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Streams {
    public List<String> reverseProductsNames(List<Product> products) {
        List<String> reverseProductsNames = products.stream().map(product -> {
            char[] chars = product.getName().toCharArray();
            char[] reverse = new char[chars.length];
            int counter = 0;
            for (int i = chars.length - 1; i > -1; i--) {

                reverse[counter] = chars[i];
                counter++;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (char ch : reverse
            ) {
                stringBuilder.append(ch);
            }
            return stringBuilder.toString();
        })
                .collect(Collectors.toList());

        return reverseProductsNames;
    }

    public Optional<String> productsNamesToOneString(List<Product> products) {

        Optional<String> productsNames = products.stream()
                .map(product -> product.getName())
                .reduce((s, s2) -> s + " " + s2);
        return productsNames;
    }

    public List<Product> sortProductsByPrice(List<Product> products) {
        List<Product> productsSortedByPrice = products.stream()
                .sorted((o1, o2) -> (int) (o2.getPrice() - o1.getPrice()))
                .skip(5)
                .limit(3)
                .collect(Collectors.toList());
        return productsSortedByPrice;

    }

    public OptionalDouble averageProductsPrice(List<Product> products) {
        return products.stream().mapToInt(product -> (int) product.getPrice()).average();
    }

    public Map<Long, Product> createMapIdToProduct(List<Product> products) {
        Map<Long, Product> idToProduct = products.stream().collect(Collectors.toMap(Product::getId, product -> product));
        return idToProduct;
    }

    public Map<Product, Integer> createMapProductToAmount(List<Product> products) {

        Map<Product, Integer> productToCount = products.stream()
                .distinct()
                .collect(Collectors.toMap(product -> product, product -> 1));
        return productToCount;
    }

}
