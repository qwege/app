package app.Vadinn;

import app.Operations.Colors;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;


@Route("mainPage")
public class MainPage extends VerticalLayout {
    VerticalLayout content;


    public MainPage() {
        content = new VerticalLayout();
        content.setSizeFull();
        add(content);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        content.add(horizontalLayout);
        Label label = new Label("Modne Dziecko Bączek");
        horizontalLayout.getStyle().set("background", Colors.getColor(150,230,250));
        horizontalLayout.setAlignItems(Alignment.CENTER);

        horizontalLayout.setHeight(100f, Unit.PIXELS);
        horizontalLayout.setWidthFull();
        horizontalLayout.add(label);
        add(horizontalLayout);
        TextField textField = new TextField();
        add(textField);
        Button button = new Button("submit");
        add(button);
        button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {
                VaadinSession.getCurrent().setAttribute("i", textField.getValue());
             //  UI.getCurrent().getPage().setLocation("http://localhost:8080/mainPage");
                Label label = new Label("Nazwa");
            }
        });
    }
}
