package app.Models.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Category")
public class CategoryEntity {
    @Id
    @SequenceGenerator(name = "CategorySequence")
    @GeneratedValue (strategy =  GenerationType.SEQUENCE,generator = "CategorySequence")
    int id_category;
    String name;

    public CategoryEntity() {
    }

    public int getId() {
        return id_category;
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id_category = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
