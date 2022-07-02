package app.Vadinn.SubPage;

import app.Models.Product;
import app.Operations.VaadinOperation;
import app.Vadinn.SubPage.Compontent.ProductWindow;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;
public class SelectProductPage extends VerticalLayout {
    List<ProductWindow> productWindowList;
    FormLayout formLayout;
    public SelectProductPage(List<Product> products){
        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        updateProducts(products);

    }
    private void updateProducts(List<Product> products){
        try {
            for(ProductWindow p : productWindowList)formLayout.remove(p);
            remove(formLayout);
        }catch (NullPointerException ignore){}
        productWindowList=new ArrayList<>();
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
        for(Product product:products){
            ProductWindow productWindow = new ProductWindow(product);
            productWindow.addClickListener(new ComponentEventListener<ClickEvent<FormLayout>>() {
                @Override
                public void onComponentEvent(ClickEvent<FormLayout> formLayoutClickEvent) {
                    VaadinSession.getCurrent().setAttribute("currentProduct",((ProductWindow)formLayoutClickEvent.getSource()).getProduct());
                    try {
                        if (VaadinOperation.isAdminLogged())
                            UI.getCurrent().getPage().setLocation("/Admin/EditProduct");
                    } catch (Throwable e) {
                        System.out.println("Client Product Page Not Implemented");
                        UI.getCurrent().getPage().setLocation("/Product");
                    }


                }
            });
            formLayout.add(productWindow);
            productWindowList.add(productWindow);
        }

    }

}
