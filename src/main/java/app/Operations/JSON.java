package app.Operations;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSON {
    public static String ObjectToJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
