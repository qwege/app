package app.Models.REST;

import app.Models.Category;
import app.Models.Entity.CategoryEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

@RestController
@RequestMapping("/cat")
public class CategoryController {
    @Resource(name = "CategoryService")
    private CategoryService categoryService;

    @RequestMapping(value = "/getByName/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getByID(@PathVariable String name){

        return Category.getInstance(categoryService.getCategoryByName(name));
    }
    @RequestMapping(value = "/getById/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Category getByID(@PathVariable int id ){

        return Category.getInstance(categoryService.getCategoryById(id));
    }
    @RequestMapping(value = "/list")
    public CategoryList getList(){
        categoryService.update();
        List<Category> categoryList= new ArrayList<>();
        for(CategoryEntity categoryEntity :categoryService.getCategoryEntityList()){
            categoryList.add(Category.getInstance(categoryEntity));
        }
        return new CategoryList( categoryList);
    }

    @RequestMapping(value = "/init")
    public void init(){
         categoryService.update();
    }
    @PostMapping(value = "/update")
    public void update(final @RequestBody Category category){

        categoryService.update(category.toEntity());
    }
}
