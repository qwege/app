import app.Models.CategoryDir.Bluza;
import app.Operations.Cache;
import app.Operations.WebRequest;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       /* WebRequest.update();
        Category category = Cache.getCategories().get(0);
        for(int i=0;i<10;i++){
        Product p = new Bluza();
        p.setOpis("opis test "+i);
        p.setGroupDescription("super");
        Cache.saveProduct(p);
        }
     List<Product> productList = Cache.getProductsByCategory(category);
     System.out.println(productList.get(0).getCena());*/
        WebRequest.update();



    }
}

