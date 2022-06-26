package app.Operations;

import app.Models.Category;
import app.Models.Exception.ObjectDontFoundInDataBaseException;
import app.Models.Product;
import app.Models.View;

import java.util.*;

public class Cache {
    private static Map<Integer, Product> productList;
    private static Map<Integer, View> viewList;
    private static Map<Integer, Category> categoryList;


    public static Product getProductById(int id) {
        if (productList == null) {
            productList = new HashMap<>();
        }
        Product p = null;
        p = productList.get(id);
        if (p != null) return p;
        p = (Product) WebRequest.setGetOne(Product.class, WebRequest.GetProductByID + id);
        if (p == null) throw new ObjectDontFoundInDataBaseException();
        if (productList.size() >= 101) {
            productList.remove(productList.keySet().toArray()[0]);

        }
        productList.put((int) p.getId_product(), p);
        return p;
    }

    public static void saveProduct(Product p) {
        if(productList==null) productList=new HashMap<>();
        if (productList.size() >= 101) {
            try {
                productList.remove(productList.keySet().toArray()[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
                productList.remove(productList.keySet().toArray()[0]);
            }
        }
        productList.put((int) p.getId_product(), p);
        WebRequest.setPOST(p, WebRequest.SaveProduct);

    }
    public static List<Product> getProductsAll(){
        return WebRequest.setGetProductList(WebRequest.GetProductsAll);
    }

    public static List<Product> getProductsByCategory(Category category) {
        return WebRequest.setGetProductList(WebRequest.GetProductsByCategoryId + category.getId_category());
    }

    public static List<Category> getCategories() {
        WebRequest.update();
        if (categoryList == null) {
            categoryList = new HashMap<>();
            List<Category> c = WebRequest.setGetCategoryList(WebRequest.GetCategoryList);
            for (Category category : c) {
                categoryList.put(category.getId_category(), category);
            }
        }

        List<Category> categories =new ArrayList<>();
        for( int a :categoryList.keySet()){
            categories.add(categoryList.get(a));
        }
        return categories;
    }

    public static Category getCategoryById(int id) {
        if (categoryList == null) {
            getCategories();
        }
        return categoryList.get(id);
    }

    public static View getViewById(int id) {
        if (viewList == null) {
            viewList = new HashMap<>();
        }
        View p = null;
        p = viewList.get(id);
        if (p != null) return p;
        p = (View) WebRequest.setGetOne(View.class, WebRequest.GetViewByID + id);
        if (p == null) throw new ObjectDontFoundInDataBaseException();
        if (viewList.size() >= 101) {
            viewList.remove(viewList.keySet().toArray()[0]);
        }
        viewList.put( p.getId_view(), p);
        return p;
    }

    public static List<Product> getProductsByViewId(int id){
        if (viewList == null) {
            viewList = new HashMap<>();
        }
        List<Product> productList = new ArrayList<>();
        View v = null;
        v = viewList.get(id);
        if (v != null) {
            for(long val : v.getProductList())
            productList.add(getProductById((int) val));
            return productList;
        }
        v= (View) WebRequest.setGetOne(View.class,WebRequest.GetViewByID+id);
        if (v != null) {
            for(long val : v.getProductList())
                productList.add(getProductById((int) val));
            return productList;
        }
        return null;
    };
}
