package app.Operations;


import app.Models.CategoryDir.*;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static List<String> getCategory(){
        List<String> stringList= new ArrayList<>();
        stringList.add(Bluza.class.getSimpleName());
        stringList.add(Bluzka.class.getSimpleName());
        stringList.add(Body.class.getSimpleName());
        stringList.add(Chustka.class.getSimpleName());
        stringList.add(Czapka.class.getSimpleName());
        stringList.add(Koszula.class.getSimpleName());
        stringList.add(Leggins.class.getSimpleName());
        stringList.add(Shorty.class.getSimpleName());
        stringList.add(Skarpety.class.getSimpleName());
        stringList.add(Spodnie.class.getSimpleName());
        stringList.add(Spudnica.class.getSimpleName());
        stringList.add(Sukienka.class.getSimpleName());
        return stringList;

    }
}
