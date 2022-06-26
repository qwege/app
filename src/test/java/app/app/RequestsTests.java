package app.app;

import app.Models.Entity.CategoryEntity;
import app.Models.Entity.ProductEntity;
import app.Operations.WebRequest;
import org.junit.jupiter.api.Test;


public class RequestsTests {

    @Test
    void testGetConnections(){
        CategoryEntity categoryEntity = (CategoryEntity) WebRequest.setGetOne(CategoryEntity.class,WebRequest.GetCategoryByName+"Bluza");
        assert categoryEntity!=null;
        ProductEntity productEntity =  (ProductEntity) WebRequest.setGetOne(ProductEntity.class,WebRequest.GetProductByID+"1");
        assert productEntity !=null;
    }
}
