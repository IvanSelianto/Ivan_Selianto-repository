import org.junit.Assert;
import org.junit.Test;

public class ProductBuilderTest {
    @Test
    public void setName() {
       Assert.assertNotNull( Product.builder().setName("dfl").build());

    }

}
