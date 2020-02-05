import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Product {
    private String name;
    private double price;
    private long id;
    private static List<Product> products = createProductsList();

    private Product() {

    }

    public static List<Product> createProductsList() {
        return Arrays.asList(
                Product.builder().setId(30).setName("Lenovo").setPrice(2000).build(),
                Product.builder().setId(12).setName("A4Tech").setPrice(20).build(),
                Product.builder().setId(57).setName("HP").setPrice(1000).build(),
                Product.builder().setId(44).setName("Sony").setPrice(300).build(),
                Product.builder().setId(98).setName("Senheiser").setPrice(600).build(),
                Product.builder().setId(132).setName("Marshall").setPrice(1500).build(),
                Product.builder().setId(8).setName("Les Paul").setPrice(3000).build(),
                Product.builder().setId(307).setName("Fender").setPrice(2500).build(),
                Product.builder().setId(16).setName("Washburn").setPrice(700).build(),
                Product.builder().setId(2).setName("Samsung").setPrice(200).build(),
                Product.builder().setId(45).setName("Lenovo").setPrice(1).build()
        );

    }

    public static int getAmount(Product product) {
        int counter = 0;
        for (int i = 0; i < products.size(); i++) {
            if (product.getName() == products.get(i).getName()) {
                counter++;
            }
        }
        return counter;
    }

    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }


    public long getId() {
        return id;
    }


    public static ProductBuilder builder() {
        return new Product().new ProductBuilder();
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public class ProductBuilder {

        private ProductBuilder() {

        }

        public ProductBuilder setName(String name) {
            Product.this.name = name;
            return this;
        }

        public ProductBuilder setPrice(double price) {
            Product.this.price = price;
            return this;
        }

        public ProductBuilder setId(long id) {
            Product.this.id = id;
            return this;
        }

        public Product build() {
            return Product.this;
        }

    }
}
