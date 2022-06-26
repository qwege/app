package app.Models.REST;

import app.Models.Entity.CategoryEntity;
import app.Models.Entity.ProductEntity;
import app.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ProductService")
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public void save(ProductEntity productEntity){
        productRepository.save(productEntity);
        productRepository.flush();

    }
    public ProductEntity getProductByID(int id){
        return productRepository.getReferenceById(Integer.toUnsignedLong(id));
    }

    public List<Product> getProductsByCat(int id) {
       List<ProductEntity> productEntities= productRepository.findByCatId(id);
       List<Product> productList = new ArrayList<>();
       for(ProductEntity productEntity :productEntities){
           productList.add(Product.getInstance(productEntity));
       }
       return productList;
    }

    public List<Product> getProductsAll() {
        List<ProductEntity> productEntities= productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for(ProductEntity productEntity :productEntities){
            productList.add(Product.getInstance(productEntity));
        }
        return productList;
    }
}
