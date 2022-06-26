package app.Operations;

import java.io.*;
import java.util.Scanner;

public class MySequence {
   private static long val =0l;
   public static long getValue(){
       if(val==0l){
               readVal();
       }
       val++;
       save();
       return val;
   }

    private static void save() {
        try {
            Writer writer = new FileWriter("Sequence.bin");
            writer.write(Long.toString(val));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readVal()  {
        Scanner reader = null;
        try {
            reader = new Scanner(new File("Sequence.bin"));
        } catch (FileNotFoundException e) {
            save();
        }

        val= reader.nextLong();
        reader.close();
    }


}
