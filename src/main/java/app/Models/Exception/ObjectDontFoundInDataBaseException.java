package app.Models.Exception;

public class ObjectDontFoundInDataBaseException extends RuntimeException{
    public ObjectDontFoundInDataBaseException(String text){
        super(text);
    }
    public ObjectDontFoundInDataBaseException(){
        super();
    }
}
