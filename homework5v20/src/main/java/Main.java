import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Shop onliner = new Onliner();
        onliner.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        onliner.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        onliner.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        onliner.addProduct(PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());
        onliner.addProduct(PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());


        List<Long> ids = new ArrayList<>();
        ids.add(1020L);
        ids.add(1020L);
        ids.add(1020L);
        ids.add(1020L);
        ids.add(32L);
        ids.add(32L);
        ids.add(32L);


        System.out.println(onliner.getCheck(ids, onliner));

        Shop amazon = new Amazon();
        amazon.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        amazon.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        amazon.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        amazon.addProduct(PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());
        amazon.addProduct(PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());

        System.out.println(amazon.getCheck(ids, amazon));

        ProductReader productReader = new ProductReader();

        System.out.println(productReader.read("products.csv"));


    }


}
