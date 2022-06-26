package app.Models.REST;

import app.Models.Entity.ViewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service("ViewService")
public class ViewService {
    @Autowired
    ViewRepository viewRepository;
    public void merge(int id1 , int id2){
        Optional<ViewEntity> view= viewRepository.findById(id1);
        ViewEntity viewEntity1 = view.get();
        view=viewRepository.findById(id2);
        ViewEntity viewEntity2 =view.get();
        viewEntity1.getProducts().addAll(viewEntity2.getProducts());
        viewEntity2.setProducts(new HashSet<>());
        viewRepository.save(viewEntity1);
        viewRepository.delete(viewEntity2);
        viewRepository.flush();
    }
    public ViewEntity getViewById(int id){
        return viewRepository.findById(id).get();
    }
}
