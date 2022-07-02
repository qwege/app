package app.Models.Entity;

import javax.persistence.*;

@Entity
@Table(name="Dana")
public class DanaEntity {
    @Id
    @SequenceGenerator(name = "DanaSequence",sequenceName = "DanaSequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "DanaSequence")
    @Column(name = "id_dana")
    private int id_dana;
    private String name;
    private String value;
    @ManyToOne
    @JoinColumn(name="id_product",referencedColumnName = "id_product")
    private ProductEntity productEntity;

    public DanaEntity() {
    }

    public DanaEntity(String name, String value) {
        this.name = name;
        this.value = value;
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

    public ProductEntity getProduct() {
        return productEntity;
    }

    public void setProduct(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public void setId(int id) {
        this.id_dana = id;
    }

    public int getId() {
        return id_dana;
    }

}
