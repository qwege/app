package app.Models.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Product")

public class ProductEntity {
    @Id
    @SequenceGenerator(name = "ProductSequence",sequenceName = "ProductSequence" )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductSequence")
    private long id_product;
    @Column(name = "Prize")
    private double cena;
    @Column(name = "Productname")
    private String nazwa;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "productEntity", cascade = CascadeType.ALL,orphanRemoval = true)
    Set<DanaEntity> danaEntityList;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "productEntity", cascade = CascadeType.ALL,orphanRemoval = true)
    Set<PhotoEntity> photoEntities;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "id_view", referencedColumnName = "id_view")
    private ViewEntity viewEntity;
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    private CategoryEntity categoryEntity;
    @Column(name = "group_descrition")
    private String groupDescrition;

    public String getGroupDescrition() {
        return groupDescrition;
    }

    public void setGroupDescrition(String groupDescrition) {
        this.groupDescrition = groupDescrition;
    }

    public ViewEntity getView() {
        return viewEntity;
    }

    public void setView(ViewEntity viewEntity) {
        this.viewEntity = viewEntity;
    }

    public CategoryEntity getCategory() {
        return categoryEntity;
    }

    public void setCategory(CategoryEntity category) {
        this.categoryEntity = category;
    }

    public Set<PhotoEntity> getPhotos() {
        return photoEntities;
    }

    public void setPhotos(Set<PhotoEntity> photoEntities) {
        this.photoEntities = photoEntities;
    }

    public Set<DanaEntity> getDanaList() {
        return danaEntityList;
    }

    public void setDanaList(Set<DanaEntity> danaEntityList) {
        this.danaEntityList = danaEntityList;
    }

    public ProductEntity() {
        setDanaList(new HashSet<>());
        setPhotos(new HashSet<>());
        viewEntity = new ViewEntity();
    }

    public ProductEntity(double cena, String opis) {
        this.cena = cena;
        this.nazwa = nazwa;
    }

    public long getId() {
        return id_product;
    }

    public void setId(long id) {
        this.id_product = id;
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

    public void setNazwa(String opis) {
        this.nazwa = opis;
    }


}
