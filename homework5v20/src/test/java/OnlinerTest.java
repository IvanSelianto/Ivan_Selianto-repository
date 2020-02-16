import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OnlinerTest {
    private Shop onliner;
    private List<Long> ids ;


    @Before
    public void init() {
        onliner = new Onliner();
        ids  = new ArrayList();
        onliner.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        onliner.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        onliner.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        onliner.addProduct(PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());
        onliner.addProduct(PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());

        ids.add(1020L);
        ids.add(1020L);
        ids.add(1020L);
        ids.add(32L);
        ids.add(32L);

    }

    @Test
    public void addProduct() {
        Product bloody = Mouse.builder().setName("Bloody").setCost(20).setId(1020).build();

        Assert.assertNotNull(bloody);
    }
    @Test
    public void addProductToMap() {
        Product bloody = Mouse.builder().setName("Bloody").setCost(20).setId(1020).build();

        Assert.assertNotNull(bloody);
    }
    @Test
    public void testAddDuplicateShop() {
        onliner.addProduct(PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());
        Assert.assertTrue(Onliner.getProducts().size()  == 6);
    }


    @Test
    public void getProducts() {

        List<Product> expected = Onliner.getProducts();
        List<Product> actual = Arrays.asList(
                Mouse.builder().setName("Bloody").setCost(20).setId(1020).build(),
                Mouse.builder().setName("Bloody").setCost(20).setId(1020).build(),
                Mouse.builder().setName("Bloody").setCost(20).setId(1020).build(),
                PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build(),
                PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());

        Assert.assertEquals(expected, actual);


    }
    @Test
    public void getCheck(){

        Paycheck check = onliner.getCheck(ids, onliner);
        Assert.assertEquals(102,check.getTotalAmount() );
    }
    @Test
    public void getIdsToProduct(){
        onliner.getIdsToProducts().put(1024L ,Mouse.builder().setName("BloodyV8").setCost(20).setId(1024).build() );
        Assert.assertTrue(onliner.getIdsToProducts().containsValue(Mouse.builder().setName("BloodyV8").setCost(20).setId(1024).build()));
    }


}