package app.Operations;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class ImageOperation {
    public static Image convertToImage(byte[] imageData)
    {
        StreamResource streamResource = new StreamResource("isr", new InputStreamFactory() {
            @Override
            public InputStream createInputStream() {
                return new ByteArrayInputStream(imageData);
            }
        });
        return new Image(streamResource, "photo");
    }


}
