package app.Vadinn.SubPage.Admin;

import app.Models.Dana;
import app.Models.Photo;
import app.Models.Product;
import app.Operations.Cache;
import app.Operations.ImageOperation;
import app.Operations.VaadinOperation;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


import java.util.ArrayList;
import java.util.List;

@Route("Admin/EditProduct")
public class EditProductPage extends VerticalLayout {
    VerticalLayout imagePanel;
    HorizontalLayout images;
    List<Image> imageList;
    VerticalLayout mainImage;
    Product product;
    HorizontalLayout mainPanel;
    VerticalLayout danaPanel;
    ArrayList<TextArea> danaAreas;
    TextArea cenaArea;

    public EditProductPage() {
        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        try{
        if (VaadinOperation.isAdminLogged())
        this.add(new Label("Edit Product Page"));
        product = (Product) VaadinSession.getCurrent().getAttribute("currentProduct");
        }catch (NullPointerException e){
            UI.getCurrent().getPage().setLocation("/Admin");
            return;
        }
        product.sortPhotos();
        mainPanel = new HorizontalLayout();
        this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        initSubmitBorder();
        add(mainPanel);
        initImagePanel(product);
        initDane();
    }

    private void initSubmitBorder() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setDefaultHorizontalComponentAlignment(Alignment.END);
        verticalLayout.setHeight("34px");
        verticalLayout.setWidth("100%");
        HorizontalLayout h= new HorizontalLayout();
        h.setWidth("20%");
        h.setVerticalComponentAlignment(Alignment.END);
        Button submit=new Button("Potwierdź");
        submit.setHeight("30px");
        submit.setWidth("50%");
        submit.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                try{
                product.setCena(Double.parseDouble(cenaArea.getValue()));
                for(int i=0;i<danaAreas.size();i++){
                    product.getDanas().get(i).setValue(danaAreas.get(i).getValue());
                    Cache.saveProduct(product);
                    UI.getCurrent().getPage().setLocation("/Admin");
                }
                }catch (Throwable e){

                }
            }
        });
        h.add(submit);
        Button delete=new Button("Usuń");
        delete.setHeight("30px");
        delete.setWidth("50%");
        delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
        delete.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                try{
                    System.out.println("Not implemented");
                }catch (Throwable e){

                }
            }
        });
        h.add(delete);
        verticalLayout.add(h);
        add(verticalLayout);
    }

    private void initDane(){
        danaPanel=new VerticalLayout();
        Label cena = new Label("cena");
        cena.getStyle().set("font-weight", "bold");
        cena .setWidth("199px");
         cenaArea= new TextArea();
        cenaArea.setValue(String.valueOf(product.getCena()));
        cenaArea.getStyle().set("font-weight", "bold");
        cenaArea.setWidth("199px");
        HorizontalLayout cenaLayout= new HorizontalLayout();
        cenaLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        cenaLayout.add(cena);
        cenaLayout.add(cenaArea);
        danaPanel.setWidth("464px");
        mainPanel.add(danaPanel);
        danaPanel.add(cenaLayout);
        danaAreas= new ArrayList<>();
        for(Dana dana:product.getDanas()){
            HorizontalLayout horizontalLayout= new HorizontalLayout();
            horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
            Label label = new Label(dana.getName());
            label.setWidth("199px");
            TextArea textAreaa= new TextArea();
            textAreaa.setValue(dana.getValue());
            textAreaa.setWidth("199px");
            horizontalLayout.add(label);
            horizontalLayout.add(textAreaa);
            danaAreas.add(textAreaa);
            danaPanel.add(horizontalLayout);
        }
        HorizontalLayout buttonLayout = new HorizontalLayout();
        TextArea textArea1 = new TextArea();
        textArea1.setWidth("200px");

        Button button = new Button();
        button.setWidth("200px");
        button.setText("Dodaj Dane");
        button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                product.getDanas().add(new Dana(textArea1.getValue(),""));
                mainPanel.remove(danaPanel);
                initDane();

            }
        });
        buttonLayout.add(textArea1);
        buttonLayout.add(button);
        buttonLayout.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        danaPanel.add(buttonLayout);
    }

    private void initImagePanel(Product product) {
        imagePanel = new VerticalLayout();
        imagePanel.setWidth("772px");
        imagePanel.setHeight("832px");
        imagePanel.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        imageList = new ArrayList<>();
        updateImage(null);
        imagePanel.add(mainImage);
        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);
        scroller.setHeight("60px");
        scroller.setWidthFull();
        images = new HorizontalLayout();

        for (Photo photo : product.getPhotos()) {
            Image image = ImageOperation.convertToImage(photo.getBytes());
            image.setHeight("60px");
            imageList.add(image);
            image.setWidth((product.getPhotos().get(0).getWidth() * 60) / product.getPhotos().get(0).getHeight(), Unit.PIXELS);
            image.addClickListener(new ComponentEventListener<ClickEvent<Image>>() {
                @Override
                public void onComponentEvent(ClickEvent<Image> imageClickEvent) {
                    updateImage(imageClickEvent.getSource());
                }
            });
            images.add(image);
        }
        scroller.setContent(images);
        imagePanel.add(scroller);
        mainPanel. add(imagePanel);
    }

    private void updateImage(Image image) {
        if (image == null) setMainImage(product.getPhotos().get(0));
        for (int i = 0; i < imageList.size(); i++)
            if (imageList.get(i).equals(image)) {
                setMainImage(product.getPhotos().get(i));
            }
    }

    private void setMainImage(Photo photo) {
        try {
            mainImage.removeAll();
        } catch (Throwable ignored) {
            mainImage=new VerticalLayout();
            mainImage.setHeight("700px");
            mainImage.setWidth("700px");
            imagePanel.add(mainImage);
            mainImage.setHorizontalComponentAlignment(Alignment.CENTER,mainImage);
        }
        Image image =  ImageOperation.convertToImage(photo.getBytes());
        if (photo.getHeight() < 700 && photo.getWidth() < 700) {
        } else {
            if (photo.getWidth() > photo.getHeight()) {
                image.setWidth("700px");
                image.setHeight(photo.getHeight() * 700 / photo.getWidth(), Unit.PIXELS);
            } else {
                image.setHeight("700px");
                image.setWidth(photo.getWidth() * 700 / photo.getHeight(), Unit.PIXELS);
            }
        }
        mainImage.add(image);

    }
}
