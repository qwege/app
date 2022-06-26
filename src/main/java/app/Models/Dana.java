package app.Models;

import app.Models.Entity.DanaEntity;
import app.Models.Entity.ProductEntity;

public class Dana {
    private int id_dana;
    private String name;
    private String value;
    private int product_id;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Dana() {
    }

    public Dana(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public int getId_dana() {
        return id_dana;
    }

    public void setId_dana(int id_dana) {
        this.id_dana = id_dana;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public DanaEntity toEntity(ProductEntity product){
        DanaEntity danaEntity = new DanaEntity();
        danaEntity.setId(id_dana);
        danaEntity.setValue(value);
        danaEntity.setName(name);
        danaEntity.setProduct(product);
        return danaEntity;
    }


    public static Dana getInstance(DanaEntity danaEntity,int product_id){
        Dana dana =new Dana();
        dana.id_dana = danaEntity.getId();
        dana.name=danaEntity.getName();
        dana.value=danaEntity.getValue();
        dana.setProduct_id(product_id);
        return dana;

    }
}
