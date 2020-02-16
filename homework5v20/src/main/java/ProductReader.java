

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


public class ProductReader {
    private List<Product> products = new ArrayList<>();

    public List<Product> read(String filename) {
        try (
                FileReader fileReader = new FileReader(filename);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            while (bufferedReader.read() != -1) {
                String[] product = bufferedReader.readLine().split("([,;]\\s)|([,;])");
                products.add(Product.builder().setName(product[0]).setCost(Integer.parseInt(product[1])).setId(Long.parseLong(product[2])).build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
