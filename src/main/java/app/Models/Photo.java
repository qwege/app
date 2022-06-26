package app.Models;

import app.Models.Entity.PhotoEntity;
import app.Models.Entity.ProductEntity;


public class Photo {
    int id_photo;
    byte[] bytes;
    int product_id;

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    int Width;
    int Height;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getId_photo() {
        return id_photo;
    }

    public void setId_photo(int id_photo) {
        this.id_photo = id_photo;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public Photo() {
    }

    public Photo(byte[] bytes) {
        this.bytes = bytes;
    }

    public PhotoEntity toEntity(ProductEntity product) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setBytes(bytes);
        photoEntity.setId(id_photo);
        photoEntity.setProduct(product);
        photoEntity.setHeight(getHeight());
        photoEntity.setWidth(getWidth());
        return photoEntity;
    }


    public static Photo getInstance(PhotoEntity photoEntity,int product_id) {
        Photo photo = new Photo();
        photo.id_photo = photoEntity.getId();
        photo.bytes = photoEntity.getBytes();
        photo.setProduct_id(product_id);
        photo.setHeight(photoEntity.getHeight());
        photo.setWidth(photo.getWidth());
        return photo;

    }
}
