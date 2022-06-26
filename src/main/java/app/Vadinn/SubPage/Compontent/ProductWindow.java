package app.Vadinn.SubPage.Compontent;

import app.Models.Product;
import app.Operations.ImageOperation;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public class ProductWindow extends FormLayout {
    Image image;
    Product product;

    public ProductWindow(Product product) {
        this.setHeight("290px");
        this.setWidth("200px");
        this.product = product;
        try{
        image = ImageOperation.convertToImage(product.getPhotos().get(0).getBytes());
        System.out.println();
        float h=(float)(product.getPhotos().get(0).getHeight()*190)/product.getPhotos().get(0).getWidth();
        if(h<=280){
        image.setHeight((float)(product.getPhotos().get(0).getHeight()*190)/product.getPhotos().get(0).getWidth(), Unit.PIXELS);
        image.setWidth(190f, Unit.PIXELS);
        }
        else {
            image.setHeight(280, Unit.PIXELS);
            image.setWidth((float)(product.getPhotos().get(0).getWidth()*280)/product.getPhotos().get(0).getHeight(), Unit.PIXELS);
        }
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("190px");
        verticalLayout.setHeight("280px");
        verticalLayout.add(image);
        this.add(verticalLayout);
        verticalLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER,image);
        this.setResponsiveSteps(
                new ResponsiveStep("0", 1),
                new ResponsiveStep("100px", 2));
        this.setColspan(image,2);
        this.add(new VerticalLayout());
        Label l=new Label(String.valueOf(product.getCena()));
        l.getStyle().set("font-weight", "bold");
        this.add(l);
        }catch (IndexOutOfBoundsException ignored){
        }
    }

}
