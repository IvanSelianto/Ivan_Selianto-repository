import java.util.Objects;

public class Product {
    private String name;
    private long id;
    private int cost;


    public String getName() {

        return name;
    }

    public long getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static ProductBuilder builder() {

        return new Product().new ProductBuilder();
    }

    public class ProductBuilder {

        private ProductBuilder() {

        }

        public ProductBuilder setName(String name) {
            Product.this.name = name;
            return this;
        }

        public ProductBuilder setCost(int cost) {
            Product.this.cost = cost;
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


