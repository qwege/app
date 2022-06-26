package app.Models;

import app.Models.Entity.ProductEntity;
import app.Models.Entity.ViewEntity;

import java.util.ArrayList;
import java.util.List;

public class View {
    int id_view;
    List<Long> productList;

    public int getId_view() {
        return id_view;
    }

    public void setId_view(int id_view) {
        this.id_view = id_view;
    }

    public List<Long> getProductList() {
        return productList;
    }

    public void setProductList(List<Long> productList) {
        this.productList = productList;
    }

    public View() {
        productList=new ArrayList<>();
    }

    public ViewEntity toEntity(){
        ViewEntity viewEntity= new ViewEntity();
        viewEntity.setId_view(id_view);
        return viewEntity;
    }

    public static View getInstance(ViewEntity viewEntity){
        View view= new View();
        view.id_view=viewEntity.getId_view();
        view.productList = new ArrayList<>();
        for(ProductEntity productEntity : viewEntity.getProducts()){
            view.productList.add(productEntity.getId());
        }
        return view;
    }
}
