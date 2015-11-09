package Csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import Model.Entity;
import Model.Etudiant;
import Model.Groupe;
import Model.Intervenant;
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
	public static  ArrayList<Sujet> translateArraySujet( ArrayList<String[]> array) {
		
		 ArrayList<Sujet> goodArray = new ArrayList<Sujet>();
		 int id = 0;
		 String acronyme = "";
		 String titre = "";
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
	
	/**
	 * Transforme une ArrayList de String[] en ArrayList de Etudiants et ArrayList de Groupes
	 * @param array Une ArrayList de String[]
	 * @return goodArray une ArrayList d'Etudiant et une ArrayList de Groupe dans une ArrayList
	 */
	public static  ArrayList<ArrayList<? extends Entity>> translateArrayEtudiant( ArrayList<String[]> array) {
		
		ArrayList<Etudiant> etus = new ArrayList<>(array.size());
		ArrayList<Groupe> grps = new ArrayList<>();
		Groupe grp = null;
		String idGP = array.get(1)[0], idGN = "";
		if(!idGP.equals("")){
			grp = new Groupe(1);
			grps.add(grp);
		}
		int idG = 1;
		
		
		for(int i=1; i<array.size(); i++){
			idGN = array.get(i)[0];
			Etudiant etu = new Etudiant(Integer.parseInt(array.get(i)[1]), array.get(i)[2], array.get(i)[3]);
			etus.add(etu);
			if(idGP.equals(idGN) && !idGN.equals(""))
				grp.addEtudiant(etu);
			else
				if(!idGN.equals("")){
					grp = new Groupe(++idG);
					grp.addEtudiant(etu);
					grps.add(grp);
				}
			idGP = idGN;
		}
		
		ArrayList<ArrayList<? extends Entity>> a = new ArrayList<>();
		a.add(etus);
		a.add(grps);
		
		return a;
	}
	
	/**
	 * Transforme une ArrayList de String[] en ArrayList d'Intervenant
	 * @param array Une ArrayList de String[]
	 * @return goodArray une ArrayList d'Intervenants
	 */
	public static  ArrayList<Intervenant> translateArrayInterv( ArrayList<String[]> array) {
		ArrayList<Intervenant> intervs = new ArrayList<>();
		
		for(int i=1; i<array.size(); i++)
			intervs.add(new Intervenant(Integer.parseInt(array.get(i)[0]), array.get(i)[1], array.get(i)[2]));
		
		return intervs;
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
