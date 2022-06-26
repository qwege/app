package app.Models.REST;

import app.Models.Entity.ViewEntity;
import app.Models.View;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/myView")
public class ViewController {
    @Resource(name = "ViewService")
    ViewService viewController;
    @RequestMapping("/getById/{id}")
    public View getById(@PathVariable int id){
        return View.getInstance(viewController.getViewById(id));
    }
}
