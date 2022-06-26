package app.Vadinn.SubPage;

import app.Models.Product;
import app.Vadinn.SubPage.Compontent.ProductWindow;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;
public class SelectProductPage extends VerticalLayout {
    List<Product> productList;
    FormLayout formLayout;
    public SelectProductPage(List<Product> products){
        productList=products;
        updateProducts();

    }
    private void updateProducts(){
        try {
            remove(formLayout);
        }catch (NullPointerException ignore){}
        formLayout= new FormLayout();
        formLayout.setWidthFull();
        add(formLayout);
        setHorizontalComponentAlignment(Alignment.CENTER,formLayout);
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("200px", 2),
                new FormLayout.ResponsiveStep("400px", 3),
                new FormLayout.ResponsiveStep("600px", 4),
                new FormLayout.ResponsiveStep("800px", 5));
        for(Product product:productList){
            formLayout.add(new ProductWindow(product));
        }

    }

}
