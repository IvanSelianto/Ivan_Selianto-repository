


import java.util.HashMap;

import java.util.Map;


public class Paycheck {
    private Map<String, Long> productsNameToAmount = new HashMap<>();
    private int totalAmount;

    public Map<String, Long> getProductsNameToAmount() {
        return productsNameToAmount;
    }

    public void setProductsNameToAmount(Map<String, Long> productsNameToAmount) {
        this.productsNameToAmount = productsNameToAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Paycheck{" +
                "productsNameToAmount=" + productsNameToAmount +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
