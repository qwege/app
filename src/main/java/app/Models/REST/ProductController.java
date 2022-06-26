package app.Models.REST;

import app.Models.Entity.ProductEntity;
import app.Models.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource(name = "ProductService")
    private ProductService produService;

    @PostMapping("/save")
    public void saveCustomer( @RequestBody Product product) {
        produService.save(product.toEntity());

    }
    @RequestMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductList getAll(){
        return new ProductList(produService.getProductsAll());
    }

    @RequestMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getByID(@PathVariable int id) {
        return Product.getInstance(produService.getProductByID(id));
    }

    @RequestMapping(value = "/getByCatId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductList getListByCat (@PathVariable int id){
        return new ProductList( produService.getProductsByCat(id));

    }
}
