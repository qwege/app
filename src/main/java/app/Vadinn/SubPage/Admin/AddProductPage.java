package app.Vadinn.SubPage.Admin;


import app.Models.Dana;
import app.Models.Photo;
import app.Models.Product;
import app.Operations.Cache;
import app.Operations.DataBase;
import app.Operations.ImageOperation;
import app.Operations.WebRequest;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.communication.PushMode;
import app.Models.CategoryDir.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;


public class AddProductPage extends VerticalLayout {
    Product futureProduct;
    VerticalLayout productInfo = new VerticalLayout();
    ComboBox<String> comboBox;
    ComponentEventListener focusListener;
    Label[] metadatas;
    TextField[] datas;


    public AddProductPage() {
        VaadinSession.getCurrent().setAttribute("AddCategory", null);
        UI.getCurrent().getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
        setSizeFull();
        focusListener = new ComponentEventListener() {
            @Override
            public void onComponentEvent(ComponentEvent componentEvent) {
                save();
            }
        };

        comboBox = new ComboBox<>();
        comboBox.setItems(DataBase.getCategory());
        add(comboBox);
        Button submit = new Button("Submit");
        submit.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                Cache.saveProduct(futureProduct);
            }
        });
        add(submit);
        futureProduct = (Product) VaadinSession.getCurrent().getAttribute("AddCategory");
        if (futureProduct != null)
            comboBox.setValue(futureProduct.getClass().getSimpleName());

        comboBox.addValueChangeListener(new HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<ComboBox<String>, String>>() {
            @Override
            public void valueChanged(AbstractField.ComponentValueChangeEvent<ComboBox<String>, String> comboBoxStringComponentValueChangeEvent) {
                String val = comboBox.getValue();

                if (val.equals(Bluza.class.getSimpleName())) {
                    futureProduct = new Bluza();
                } else if (val.equals(Bluzka.class.getSimpleName())) {
                    futureProduct = new Bluzka();
                } else if (val.equals(Body.class.getSimpleName())) {
                    futureProduct = new Body();
                } else if (val.equals(Chustka.class.getSimpleName())) {
                    futureProduct = new Chustka();
                } else if (val.equals(Czapka.class.getSimpleName())) {
                    futureProduct = new Czapka();
                } else if (val.equals(Koszula.class.getSimpleName())) {
                    futureProduct = new Koszula();
                } else if (val.equals(Leggins.class.getSimpleName())) {
                    futureProduct = new Leggins();
                } else if (val.equals(Shorty.class.getSimpleName())) {
                    futureProduct = new Shorty();
                } else if (val.equals(Skarpety.class.getSimpleName())) {
                    futureProduct = new Skarpety();
                } else if (val.equals(Spudnica.class.getSimpleName())) {
                    futureProduct = new Spudnica();
                } else if (val.equals(Spodnie.class.getSimpleName())) {
                    futureProduct = new Spodnie();
                } else if (val.equals(Sukienka.class.getSimpleName())) {
                    futureProduct = new Sukienka();
                }
                datas = null;
                updateProductInfo();
                checkReload();
            }
        });


    }

    private void save() {

        try {
            if (datas != null) {
                futureProduct.setCena(Double.parseDouble(datas[0].getValue()));
                datas[0].setErrorMessage("");


                List<Dana> danas = new ArrayList<>();
                List<Dana> danaList = futureProduct.getDanas();
                Object[] danalist = danaList.toArray();
                for (int i = 1; i < metadatas.length; i++) {
                    Dana dana = (Dana) danalist[i - 1];
                    dana.setValue(datas[i].getValue());
                    danas.add(dana);
                }
                futureProduct.setDanas(danas);

            }
        } catch (
                NumberFormatException e) {
            datas[0].setErrorMessage("Niewłaściwy format");
        }
        VaadinSession.getCurrent().setAttribute("AddCategory", futureProduct);
    }

    private void checkReload() {
        if (!((Product) VaadinSession.getCurrent().getAttribute("AddCategory")).getClass().getSimpleName().equals(comboBox.getValue()))
            UI.getCurrent().push();
    }

    private void updateProductInfo() {
        this.remove(productInfo);
        productInfo = new VerticalLayout();
        add(productInfo);
        Label name = new Label(futureProduct.getClass().getSimpleName());
        productInfo.add(name);
        HorizontalLayout[] horizontalLayouts = new HorizontalLayout[futureProduct.getDanas().size() + 1];
        metadatas = new Label[horizontalLayouts.length];
        datas = new TextField[horizontalLayouts.length];
        metadatas[0] = new Label("Cena");
        datas[0] = new TextField();
        datas[0].setValue(String.valueOf(futureProduct.getCena()));
        datas[0].setHelperText("Liczba rozdzielana kropką");
        horizontalLayouts[0] = new HorizontalLayout();
        horizontalLayouts[0].setAlignItems(Alignment.BASELINE);
        horizontalLayouts[0].add(metadatas[0]);
        horizontalLayouts[0].add(datas[0]);
        productInfo.add(horizontalLayouts[0]);
        List<Dana> list = new ArrayList<>();
        list.addAll(futureProduct.getDanas());

        for (int i = 1; i < list.size() + 1; i++) {
            metadatas[i] = new Label(list.get(i - 1).getName());
            datas[i] = new TextField();
            datas[i].setValue(list.get(i - 1).getValue());
            horizontalLayouts[i] = new HorizontalLayout();
            horizontalLayouts[i].setAlignItems(Alignment.BASELINE);
            horizontalLayouts[i].add(metadatas[i]);
            horizontalLayouts[i].add(datas[i]);
            productInfo.add(horizontalLayouts[i]);
        }
        for (int i = 0; i < datas.length; i++) {
            datas[0].addFocusListener(focusListener);
        }


        FileBuffer fileBuffer = new FileBuffer();
        Upload singleFileUpload = new Upload(fileBuffer);
        productInfo.add(singleFileUpload);
        singleFileUpload.setMaxFiles(1);
        singleFileUpload.addSucceededListener(event -> {
            try {
                byte[] bytes= new FileInputStream(fileBuffer.getFileData().getFile()).readAllBytes();
                Photo photo= new Photo(bytes);
                BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
                photo.setHeight( bufferedImage.getHeight());
                photo.setWidth(bufferedImage.getWidth());

                futureProduct.getPhotos().add(photo);

            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            save();
            UI.getCurrent().push();
            updateProductInfo();
        });


        HorizontalLayout imageLayout = new HorizontalLayout();
        productInfo.add(imageLayout);

        for (Photo photo : futureProduct.getPhotos()) {
            Image image = ImageOperation.convertToImage(photo.getBytes());
            image.setHeight(200f, Unit.PIXELS);
            image.setWidth(200f, Unit.PIXELS);
            imageLayout.add(image);
        }
        save();
    }

}
