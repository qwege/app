package app.Models.CategoryDir;


import app.Models.Category;
import app.Models.Dana;
import app.Models.Entity.CategoryEntity;
import app.Models.Entity.DanaEntity;
import app.Models.Entity.ProductEntity;
import app.Models.Product;
import app.Operations.WebRequest;

public class Skarpety  extends Product {
    public Skarpety() {
        super();
        setCategory_id(((Category) WebRequest.setGetOne(Category.class,WebRequest.GetCategoryByName+this.getClass().getSimpleName())).getId_category());
        this.getDanas().add(new Dana("Rozmiar",""));
        this.getDanas().add(new Dana("Kolor",""));
        this.getDanas().add(new Dana("Skład",""));
        this.getDanas().add(new Dana("Kraj Pochodzenia",""));
    }
}
