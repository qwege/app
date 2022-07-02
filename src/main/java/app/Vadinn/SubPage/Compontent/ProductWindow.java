package app.Vadinn.SubPage.Compontent;

import app.Models.Product;
import app.Operations.ImageOperation;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.text.DecimalFormat;


public class ProductWindow extends FormLayout {
    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductWindow(Product product) {
        this.setHeight("290px");
        this.setWidth("200px");
        product.sortPhotos();
        this.product = product;
        try {
            Image image = ImageOperation.convertToImage(product.getPhotos().get(0).getBytes());
            int h = (product.getPhotos().get(0).getHeight() * 190) / product.getPhotos().get(0).getWidth();
            if (h <= 280) {
                image.setHeight((product.getPhotos().get(0).getHeight() * 190) / product.getPhotos().get(0).getWidth(), Unit.PIXELS);
                image.setWidth(190f, Unit.PIXELS);
            } else {
                image.setHeight(280, Unit.PIXELS);
                image.setWidth((float) (product.getPhotos().get(0).getWidth() * 280) / product.getPhotos().get(0).getHeight(), Unit.PIXELS);
            }
            HorizontalLayout verticalLayout = new HorizontalLayout();
            verticalLayout.setHeight("250px");
            verticalLayout.getStyle().set("background-color", "white");
            this.add(verticalLayout);
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            verticalLayout.add(horizontalLayout);
            verticalLayout.getStyle().set("background-color","#fffff7");
            horizontalLayout.add(image);
            horizontalLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER,image);
            verticalLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER, horizontalLayout);
            verticalLayout.setMargin(false);
            verticalLayout.setPadding(false);
            HorizontalLayout horizontalLayout1 = new HorizontalLayout();
            horizontalLayout1.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
            VerticalLayout verticalLayout1 = new VerticalLayout();
            verticalLayout1.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.END);
            verticalLayout1.setHeight("30px");
            this.add(verticalLayout1);
            verticalLayout1.add(horizontalLayout1);
            Label n = new Label(product.getNazwa());
            n.getStyle().set("font-weight", "bold");
            n.getStyle().set("font-size", "120%");
            horizontalLayout1.add(n);
            Label l = new Label(String.valueOf(new DecimalFormat("##.##").format(product.getCena())));
            l.getStyle().set("font-weight", "bold");
            horizontalLayout1.add(l);
            this.getStyle().set("background-color","#fffff7");

        } catch (IndexOutOfBoundsException ignored) {
        }
    }

}
