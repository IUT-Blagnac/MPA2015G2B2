package Csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import Model.Sujet;

public class LibCsv {
	
	public static final String separator = ";";
	
	/**
	 * Genere un fichier CSV a partir d'une ArrayList<String[]>
	 * 
	 * @param dataTab
	 * @param csvPath
	 * @throws Exception 
	 */
	public static void CSV_Write(ArrayList<String[]> dataTab, String csvPath) throws Exception {
		BufferedWriter csvFile = new BufferedWriter(new FileWriter(new File(csvPath)));
		String dataStr;
		for (int i = 0; i < dataTab.size(); i++) {
			dataStr = "";
			for (int u = 0; u < dataTab.get(i).length; u++) {
				dataStr += dataTab.get(i)[u] + separator;
			}
			csvFile.write( dataStr.substring(0, dataStr.length() - 1)+ "\r\n");
		}
		csvFile.close();
	}

	/**
	 * Lit et convertit un fichier CSV en ArrayList<String[]>
	 * 
	 * @param csvPath
	 * @return dataTab
	 * @throws Exception 
	 */
	public static ArrayList<String[]> CSV_Read(String csvPath) throws Exception {
		ArrayList<String[]> dataTab = new ArrayList<String[]>();
		BufferedReader csvFile = new BufferedReader(new FileReader(csvPath));
		String line;
		while ((line = csvFile.readLine()) != null) {
			if(line.substring(0, 0) != "#")
				dataTab.add(new String(line.getBytes(), Charset.forName("UTF-8")).split(separator));
		}
		csvFile.close();
		return dataTab;
	}
	
	/**
	 * Transforme une ArrayList de String[] en ArrayList de Sujet
	 * @param array Une ArrayList de String[]
	 * @return goodArray Une ArrayList de Sujet
	 */
	public static  ArrayList<Sujet> translateArray( ArrayList<String[]> array) {
		
		 ArrayList<Sujet> goodArray = new ArrayList<Sujet>();
		 int id = 0;
		 String acronyme = null;
		 String titre = null;
		 String contexte = "";
		 String besoins = "";
		 String outils = "";
		 String correspondant = "";
		 int j;
		for(int i=1; i<array.size(); i++) {
				j=0;
				
				id = Integer.parseInt(array.get(i)[j]);
				if(array.get(i)[j+1] != null) acronyme = array.get(i)[j+1];
				if (array.get(i)[j+2] != null) titre = array.get(i)[j+2];
				try{
					if (array.get(i)[j+3] != null) contexte = array.get(i)[j+3];
				}catch(Exception e){
					contexte = "";
					}
				try{
					if (array.get(i)[j+4] != null) besoins = array.get(i)[j+4];
				}
				catch(Exception e){
					besoins = "";
				}
				try{
					if (array.get(i)[j+5] != null) outils = array.get(i)[j+5];
				}catch(Exception e){
					outils = "";
				}
				try{
					if (array.get(i)[j+6] != null) correspondant = array.get(i)[j+6];
				}catch(Exception e){
					correspondant = "";
				}
				
				goodArray.add(new Sujet(id,acronyme,titre,contexte,besoins,outils,correspondant));
			}

		return goodArray;
		
	}
	
	public static  ArrayList<String[]> translateArraySujetToString( ArrayList<Sujet> array) {
		
		ArrayList<String[]> goodArray = new ArrayList<String[]>();
		String[] legende = new String[1];
		legende[0] = "id;titre;acronyme;contexte;besoins;outils;correspondant";
		goodArray.add(legende);
		for(int i=0; i<array.size(); i++) {
		
			String[] test = new String[7];
			test[0] = ""+array.get(i).getId();
			test[1] = array.get(i).getAcronyme();
			test[2] = array.get(i).getTitre();
			test[3] = array.get(i).getContexte();
			test[4] = array.get(i).getBesoins();
			test[5] = array.get(i).getOutils();
			test[6] = array.get(i).getCorrespondant();
			
			goodArray.add(test);


			}
		
		return goodArray;
		
	}


	
}
