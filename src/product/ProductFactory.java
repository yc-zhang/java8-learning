package product;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {

    private final static Map<String, Supplier<Product>> PRODUCTS = new HashMap();

    static {
        PRODUCTS.put("loan", Loan::new);
        PRODUCTS.put("stock", Stock::new);
        PRODUCTS.put("bond", Bond::new);
    }


    public static Product createProduct(String name) {
        Supplier<Product> supplier = PRODUCTS.get(name);
        if (supplier == null) throw new RuntimeException("No found such product " + name);
        return supplier.get();
    }

    public static void main(String args[]) {
        System.out.println(ProductFactory.createProduct("loan").getClass());
    }
}

class Product {

}

class Loan extends Product {

}

class Stock extends Product {

}

class Bond extends Product {

}
