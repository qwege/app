package app.Models.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Photo")
public class PhotoEntity {
    @Id
    @SequenceGenerator(name = "PhotoSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PhotoSequence")
    int id_photo;
    @Column(name = "bytes", length = 16777215)
    byte[] bytes;
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id_product")

    private ProductEntity productEntity;
    int Width;
    int Height;

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
