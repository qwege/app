package app.Models.REST;

import app.Models.Product;
import java.util.List;

public class ProductList {
    public List<Product> productList;

    public ProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductList() {
    }
}
