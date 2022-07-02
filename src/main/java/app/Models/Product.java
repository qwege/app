package app.Models;

import app.Models.Entity.*;
import app.Models.Exception.ObjectDontFoundInDataBaseException;
import app.Operations.WebRequest;

import javax.persistence.*;
import java.util.*;

public class Product {
    private long id_product;

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    private String groupDescription;
    private double cena;
    private String nazwa;

    List<Dana> danas ;

    List<Photo> photos;

    private int  view_id;

    private int category_id;

    public Product() {
        setDanas(new ArrayList<>());
        setPhotos(new ArrayList<>());
        nazwa="";
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Dana> getDanas() {
        return danas;
    }

    public void setDanas(List<Dana> danas) {
        this.danas = danas;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public int getView_id() {
        return view_id;
    }

    public void setView_id(int view_id) {
        this.view_id = view_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public ProductEntity toEntity(){
        ProductEntity productEntity =new ProductEntity();
        productEntity.setId(id_product);
        productEntity.setNazwa(nazwa);
        productEntity.setCategory(((Category)WebRequest.setGetOne(Category.class,WebRequest.GetCategoryById+category_id)).toEntity());
        productEntity.setCena(cena);
        productEntity.setGroupDescrition(groupDescription);
        try{
            if(view_id==0) throw  new ObjectDontFoundInDataBaseException();
        productEntity.setView(((View)WebRequest.setGetOne(View.class,WebRequest.GetViewByID+view_id)).toEntity());
        }catch (ObjectDontFoundInDataBaseException e){
            productEntity.setView(new ViewEntity());
        }

        for(Dana dana:danas)
        productEntity.getDanaList().add(dana.toEntity(productEntity));

        for(Photo photo:photos)
            productEntity.getPhotos().add(photo.toEntity(productEntity));
        return productEntity;
    }

    public static Product getInstance(ProductEntity productEntity){
      Product product= new Product();
        product.id_product=productEntity.getId();
        product.cena = productEntity.getCena();
        product.nazwa=productEntity.getNazwa();
        product.groupDescription= product.getGroupDescription();
        product.category_id=productEntity.getCategory().getId();
        product.view_id=productEntity.getView().getId_view();

        for( PhotoEntity photoEntity :productEntity.getPhotos()){
            product.photos.add(Photo.getInstance(photoEntity,photoEntity.getId()));
        }
        product.danas=new ArrayList<>();
        for( DanaEntity danaEntity :productEntity.getDanaList()){
            product.danas.add(Dana.getInstance(danaEntity, (int) productEntity.getId()));
        }
        return product;
    }
    public void sortPhotos(){
        Photo[] photosNew = new Photo[photos.size()];
        for(Photo photo:photos){
            photosNew[photo.getSortVal()]=photo;
        }
        photos= Arrays.stream(photosNew).toList();
    }

}
