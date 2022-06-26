package app.Vadinn;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


@Route("Login")
public class LoginPage extends VerticalLayout {

    public LoginPage(){
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        TextField loginField=new TextField();
        PasswordField passwordField = new PasswordField();
        Button submit = new Button("Submit");
        add(loginField);
        add(passwordField);
        add(submit);
        submit.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                if(loginField.getValue().equals("Ela")&& passwordField.getValue().equals("Ela"))
                VaadinSession.getCurrent().setAttribute("auth","true");
                UI.getCurrent().getPage().setLocation("http://localhost:8080/Admin");
            }
        });

    }
}
