package zemoov.serenemouv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;

public class csvFile {
    int column=0;
    csvFile(){}

    public void readfile() throws FileNotFoundException {
        //Get scanner instance
        Scanner scanner = new Scanner(new File("bornes-irve-20210220.csv"));
         
        //Set the delimiter used in file
        scanner.useDelimiter(";");
         
        //Get all tokens and store them in some data structure
        while (scanner.hasNext()) 
        {
            System.out.print(scanner.next() + "|");
        }
         
        //Do not forget to close the scanner  
        scanner.close();
    }
    public static void main(String[] args) {
        csvFile csvFile = new csvFile(); 
    }
}
