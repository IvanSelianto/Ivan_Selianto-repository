import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmazonTest {

    private Shop amazon;
    private List<Long> ids ;


    @Before
    public void init() {
        amazon = new Onliner();
        ids  = new ArrayList();
        amazon.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        amazon.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        amazon.addProduct(Mouse.builder().setName("Bloody").setCost(20).setId(1020).build());
        amazon.addProduct(PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());
        amazon.addProduct(PowerBank.builder().setName("Xiaomi").setCost(21).setId(32).build());

        ids.add(1020L);
        ids.add(1020L);
        ids.add(1020L);
        ids.add(32L);
        ids.add(32L);

    }

    @Test
    public void addProduct() {
        Product bloody = Mouse.builder().setName("JBL").setCost(200).setId(1256).build();

        Assert.assertNotNull(bloody);
    }

    @Test
    public void getCheck(){

        Paycheck check = amazon.getCheck(ids, amazon);
        Assert.assertEquals(102,check.getTotalAmount() );
    }
    @Test
    public void getIdsToProduct(){
        amazon.getIdsToProducts().put(1024L ,Mouse.builder().setName("BloodyV8").setCost(20).setId(1024).build() );
        Assert.assertTrue(amazon.getIdsToProducts().containsValue(Mouse.builder().setName("BloodyV8").setCost(20).setId(1024).build()));
    }

}