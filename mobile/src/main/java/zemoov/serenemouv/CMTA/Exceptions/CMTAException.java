package zemoov.serenemouv.CMTA.Exceptions;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CMTAException extends Exception {
    CMTAException(String s){
        try {
            printStackTrace(new PrintWriter(s));
        } catch (
        FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
