package app.Models.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Photo")
public class PhotoEntity {
    @Id
    @SequenceGenerator(name = "PhotoSequence",sequenceName = "PhotoSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PhotoSequence")
    int id_photo;
    @Column(name = "bytes", length = 16777215)
    byte[] bytes;
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product")

    private ProductEntity productEntity;
    int Width;
    int Height;
    int sortVal;

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public int getId_photo() {
        return id_photo;
    }

    public void setId_photo(int id_photo) {
        this.id_photo = id_photo;
    }

    public int getSortVal() {
        return sortVal;
    }

    public void setSortVal(int sortVal) {
        this.sortVal = sortVal;
    }

    public PhotoEntity(byte[] bytes) {
        this.bytes = bytes;
    }

    public ProductEntity getProduct() {
        return productEntity;
    }

    public void setProduct(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public PhotoEntity() {
    }


    public int getId() {
        return id_photo;
    }

    public void setId(int id) {
        this.id_photo = id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

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


}
