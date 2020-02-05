import java.util.List;
import java.util.Map;

public interface Shop {
    void addProduct(Product product);
    Paycheck getCheck(List<Long> ids,Shop shop);
     Map<Long, Product> getIdsToProducts();
}
