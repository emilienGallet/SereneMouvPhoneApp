package zemoov.serenemouv.CMTA.Exceptions;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CMTAWarning extends Exception{
    public CMTAWarning(String s){
        try {
            printStackTrace(new PrintWriter(s));
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
