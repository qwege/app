package app.Models.REST;

import app.Models.Category;
import java.util.List;

public class CategoryList {
    public List<Category> categoryList;

    public CategoryList() {
    }

    public CategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

}
