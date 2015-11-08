package Csv;

import java.util.ArrayList;


public class mainCsv {

	public static void main(String[] args) {
		ArrayList<String[]> dataTab= new ArrayList<String []>();
		String[][] tabSt = {
				{ "prenom", "nom" },
				{ "Remi", "BOULLE" },
				{ "Jean-Michel", "BRUEL" },
				{ "Marie-Francoise", "CANUT" },
				{ "Laurent", "CHANCOGNE" },
				{ "Xavier", "DARAN" },
				{ "Marianne", "DE MICHIEL" },
				{ "Laurent", "DEMAY" },
				{ "Laurent", "HARDY" },
				{ "Jean-Michel", "INGLEBERT" },
				{ "Isabelle", "LAFON" },
				{ "Cyril", "MOULIN" },
				{ "Laurent", "NONNE" },
				{ "Iulian", "OBER" },
				{ "Andre", "PENINOU" },
				{ "Laurence", "REDON" },
				{ "Olivier", "ROQUES" },
				{ "Pascal", "SOTIN" },
				{ "Patricia", "STOLF" },
				{ "Olivier", "TESTE" },
				{ "Evelyne", "TISSIER" },
				{ "Michele", "VERDIER" }
			};
		for(int i=0;i<tabSt.length;i++){
			dataTab.add(tabSt[i]);
		}
		
		try {
			LibCsv.CSV_Write(dataTab, "intervenants2015_2016.csv");
			System.out.println("All Clear!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String[]> dataTab2= new ArrayList<String []>();
		String[][] tabSt2 = {
				{"Numero de Sprint"},
				{"Sprint -1"},
				{"Sprint 0"},
				{"Sprint 1"}
		};
		for(int i=0;i<tabSt2.length;i++){
			dataTab.add(tabSt2[i]);
		}
		
		try {
			LibCsv.CSV_Write(dataTab2, "fichierUneColonne.csv");
			System.out.println("All Clear!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String[]> dataTab3= new ArrayList<String []>();
		String[][] tabSt3 = {	{ "prénom", "âge" },
				{ "Cécile", "18 ans"}
			};
		for(int i=0;i<tabSt3.length;i++){
			dataTab.add(tabSt3[i]);
		}
		
		try {
			LibCsv.CSV_Write(dataTab3, "testContenu.csv");
			System.out.println("All Clear!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<String[]> dataTab4= new ArrayList<String []>();
		String[][] tabSt4 = {
			};
		for(int i=0;i<tabSt4.length;i++){
			dataTab.add(tabSt4[i]);
		}
		
		try {
			LibCsv.CSV_Write(dataTab4, "fichierVide.csv");
			System.out.println("All Clear!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//##################################################
		
		
		ArrayList<String[]> tabTest= new ArrayList<String []>();
		String[][] tabTemp = {	{ "id", "titre" },
				{ "1", "titre1"}, { "2", "titre2"}, { "3", "titre3"}, { "4", "titre4"}, { "5", "titre5"}, { "6", "titre6"}, { "7", "titre7"}
			};
		for(int i=0;i<tabTemp.length;i++){
			tabTest.add(tabTemp[i]);
		}
		
		try {
			LibCsv.CSV_Write(tabTest, "testContenu.csv");
			System.out.println("All Clear!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
	}

}
