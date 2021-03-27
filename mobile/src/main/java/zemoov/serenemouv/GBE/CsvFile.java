package zemoov.serenemouv.GBE;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * @author Mélanie
 * parseur créé lors de l'UE interoperabilite
 */
public class CsvFile {
	private static GBE gbe;
	static final String PATH = "./FileDropbox/";
	public static ArrayList<ArrayList<String>> parseFileToCvs(String name) throws Exception {
		BufferedReader bf = new BufferedReader(new FileReader(PATH+name));
		String str = null;
		if ((str = bf.readLine())!=  null) { //Enlever l'entete
			parseLine(str);
		}
		ArrayList<ArrayList<String>> tableau = new ArrayList<ArrayList<String>>();
		while ((str=bf.readLine())!= null) {
			tableau.add(parseLine(str));
		}
		return tableau ; 
		
	}
	
	public static ArrayList<String> parseLine(String s) {
		Boolean b= false;
		ArrayList<String> ligne = new ArrayList<String>();
		String ns = new String();
		for (Character c : s.toCharArray()) {
			if (c.equals('"')) {
				b = !b;
			}
			else if (c.equals(';')&&b==false) {
				ligne.add(ns);
				ns = new String();
			}else {
				ns = ns.concat(c.toString());
			}
		}
		return ligne;
	}
	
	public static void main(String[] args) {
		readfile();
	}

	public static void readfile() {
		try {
			int x=0;
			int y =0;
			ArrayList<ArrayList<String>> a = parseFileToCvs("bornes-irve-20210220.csv");
			for (ArrayList<String> arrayList : a) {
				System.out.print("Ligne "+x+" : ");
				x++;
				int ct=0;
				for (String ster : arrayList) {

					System.out.print("["+y+"] = "+ ster +";");
					if(ct==7){
						gbe.setXlongitude(Double.valueOf(ster));
					}
					if(ct==8){
						gbe.setYlatitude(Double.valueOf(ster));
					}
					y++;
					ct++;
				}
				y=0;
				System.out.println("");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
