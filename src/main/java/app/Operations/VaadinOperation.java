package app.Operations;

import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;

public class VaadinOperation {

    public static boolean isAdminLogged(){
        return VaadinSession.getCurrent().getAttribute("auth").equals("true");
    }


}
