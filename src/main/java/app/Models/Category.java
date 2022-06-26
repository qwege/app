package app.Models;

import app.Models.Entity.CategoryEntity;

public class Category {
    int id_category;
    String name;

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity toEntity(){
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setId(id_category);
        categoryEntity.setName(name);
        return categoryEntity;
    }

    public static Category getInstance(CategoryEntity categoryEntity){
        Category category = new Category();
        category.id_category=categoryEntity.getId();
        category.name=categoryEntity.getName();
        return category;
    }
}
