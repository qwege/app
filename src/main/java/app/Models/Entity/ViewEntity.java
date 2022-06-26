package app.Models.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "widok")
public class ViewEntity {
    @Id
    @SequenceGenerator(name = "ViewSequence")
    @GeneratedValue (strategy = GenerationType.SEQUENCE,generator = "ViewSequence")
    int id_view;
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "viewEntity")

    Set<ProductEntity> productEntities;

    public int getId_view() {
        return id_view;
    }

    public void setId_view(int id_view) {
        this.id_view = id_view;
    }

    public Set<ProductEntity> getProducts() {
        return productEntities;
    }

    public void setProducts(Set<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }


    public ViewEntity() {
        setProducts(new HashSet<>());
    }

}
