package app.Models.REST;

import app.Models.Entity.CategoryEntity;
import app.Models.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query("select p from ProductEntity p join CategoryEntity c on c=p.categoryEntity where c.id_category = ?1")
    List<ProductEntity> findByCatId( int id);

}
