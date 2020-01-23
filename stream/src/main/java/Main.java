import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Product> products  = Product.createProductsList();
        Streams streams = new Streams();

        System.out.println(streams.reverseProductsNames(products));
        System.out.println(streams.productsNamesToOneString(products));
        System.out.println(streams.sortProductsByPrice(products));
        System.out.println(streams.averageProductsPrice(products));
        System.out.println(streams.createMapIdToProduct(products));
        System.out.println(streams.createMapProductToAmount(products));





    }

}
