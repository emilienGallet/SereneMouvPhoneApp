package zemoov.serenemouv.GBE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
