package app.Models.REST;

import app.Models.CategoryDir.*;
import app.Models.Entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("CategoryService")
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    List<CategoryEntity> categoryEntityList;

    public List<CategoryEntity> getCategoryEntityList() {
        return categoryEntityList;
    }

    public void update(CategoryEntity categoryEntity){
        categoryRepository.save(categoryEntity);
    }

    public CategoryEntity getCategoryByName(String name) {
        update();
        for (CategoryEntity categoryEntity : categoryEntityList) {
            if (categoryEntity.getName().equals(name)) return categoryEntity;
        }
        return null;
    }
    public CategoryEntity getCategoryById(int id ) {
        update();
       return   categoryRepository.getReferenceById(id);

    }

    public void update() {
        if (categoryEntityList == null) {
            categoryEntityList = categoryRepository.findAll();
            if (categoryEntityList.size() == 0) {
                categoryEntityList = categoryRepository.findAll();
                fill();
                update();
            }
        }
    }

    private void fill() {
        categoryRepository.save(new CategoryEntity(Bluza.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Bluzka.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Body.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Chustka.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Czapka.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Koszula.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Leggins.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Shorty.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Skarpety.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Spodnie.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Spudnica.class.getSimpleName()));
        categoryRepository.save(new CategoryEntity(Sukienka.class.getSimpleName()));
    }
}
