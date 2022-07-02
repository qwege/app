package app.Vadinn;

import app.Operations.Cache;
import app.Operations.VaadinOperation;
import app.Vadinn.SubPage.Admin.AddProductPage;
import app.Vadinn.SubPage.SelectProductPage;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;

@Route("Admin")
public class AdminPage extends VerticalLayout {
    Tabs tabs;
    VerticalLayout content;

    public AdminPage() {
        try {
            if (!VaadinOperation.isAdminLogged()){
                UI.getCurrent().getPage().setLocation("/Login");
            return;}
        } catch (Throwable e) {
                UI.getCurrent().getPage().setLocation("/Login");
                return;
        }
        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        tabs = new Tabs();
        Tab addProduct = new Tab("Dodaj Produkt");
        Tab edit = new Tab("Edycja");
        tabs.add(edit);
        tabs.add(addProduct);
        tabs.addSelectedChangeListener(new ComponentEventListener<Tabs.SelectedChangeEvent>() {
            @Override
            public void onComponentEvent(Tabs.SelectedChangeEvent selectedChangeEvent) {
                if (edit.isSelected()) {
                    remove(content);
                    content =new SelectProductPage(Cache.getProductsAll());
                    add(content);
                }
                else if (addProduct.isSelected()) {
                    remove(content);
                    content =new AddProductPage();
                    add(content);
                }
            }
        });


        this.add(tabs);
        content =new SelectProductPage(Cache.getProductsAll());
        add(content);

    }
}
