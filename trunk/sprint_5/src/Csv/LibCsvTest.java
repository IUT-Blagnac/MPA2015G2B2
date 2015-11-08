package Csv;

import junit.framework.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class LibCsvTest extends TestCase {
	
	private static final String testFilesPath = "../";
	// Mettre 	"./bin/" 	pour que les tests fonctionne sous Eclipse
	// Mettre 	"./" 		pour que les tests lancés par le make.bat fonctionne
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new TestSuite(LibCsvTest.class));
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public void test_read_unknown_csv() {
		boolean exception = false ;
		try { LibCsv.CSV_Read("ThisRandomFile"+randInt(100,999)+"NotExist.csv"); }
		catch (Exception e) { exception = true ; };
		assertTrue("LibCsv.CSV_Read() doit lever une exception en cas de fichier inconnu", exception);
	}
	
	public void test_read_content_csv() {
		boolean fichierChargee;
		try {
			ArrayList<String[]> arrayListRecuperee = LibCsv.CSV_Read(testFilesPath+"intervenants2015_2016.csv");
			fichierChargee = true;
			
			if(fichierChargee) {
				String[][] donneesAttendues = {
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
				for(int i=0; i<arrayListRecuperee.size(); i++) {
					for(int j=0; j<arrayListRecuperee.get(i).length; j++) {
						assertEquals("Verification contenu retourné/attendu",donneesAttendues[i][j],arrayListRecuperee.get(i)[j]);
					}
				}
			}
		} catch(Exception e) {
			fichierChargee = false;
		}
		assertTrue("Chargement du fichier intervenants2015_2016.csv",fichierChargee);
	}
	
	public void test_read_csv_vide() {
		boolean fichierChargee = false;
		try {
			ArrayList<String[]> listeLecture = LibCsv.CSV_Read(testFilesPath+"fichierVide.csv");
			fichierChargee = true;
			
			boolean listeVide = true;
			if(listeLecture.size() != 0) {
				listeVide = false;
			}
			assertTrue("fichierVide.csv non vide", listeVide);
		} catch(Exception e) {
			fichierChargee = false;
		}
		assertTrue("Chargement du fichier fichierVide.csv", fichierChargee);
	}
	
	public void test_read_csv_uneColonne() {
		boolean fichierChargee = false;
		try {
			ArrayList<String[]> arrayListRecuperee = LibCsv.CSV_Read(testFilesPath+"fichierUneColonne.csv");
			fichierChargee = true;
			if(fichierChargee) {
				String[][] donneesAttendues = {
						{"Numero de Sprint"},
						{"Sprint -1"},
						{"Sprint 0"},
						{"Sprint 1"}
				};
				for(int i=0; i<arrayListRecuperee.size(); i++) {
					for(int j=0; j<arrayListRecuperee.get(i).length; j++) {
						assertEquals("Verification contenu retourné/attendu",donneesAttendues[i][j],arrayListRecuperee.get(i)[j]);
					}
				}
			}
		} catch (Exception e) {
			fichierChargee = false;
		}
		assertTrue("Chargement du fichier fichierUneColonne.csv",fichierChargee);
	}
	
	public void test_write_without_permissions() {
		boolean exception = false;
		ArrayList<String[]> dataB= new ArrayList<String []>();
		try { LibCsv.CSV_Write(dataB,"File"+randInt(100,999)+"ThatDoesntExist/fichier.csv" ); }
		catch (Exception e) { exception = true ; };
		assertTrue("Dossier inexistant ou de problème de droit d'écriture", exception);
	}
	
	public void test_write_data_conform() {
		ArrayList<String[]> dataB= new ArrayList<String []>();
		boolean contenu=true;
		String concat="";
		String[] tabStr= new String[2];
		String[][] tabSt = {	{ "prénom", "âge" },
								{ "Cécile", "18 ans"}
							};
		for(int i=0;i<tabSt.length;i++){
			dataB.add(tabSt[i]);
		}
		try { 
			LibCsv.CSV_Write(dataB,"testContenu.csv" );
			BufferedReader csvFile = new BufferedReader(new FileReader("testContenu.csv"));
			try {
				String line;
				while ((line = csvFile.readLine()) != null) {
					int i=0;
					tabStr[i]=line;
					concat+=tabStr[i];
					i++;
				}
			} 
			finally {
				csvFile.close();
			}
		} catch (Exception e) {
			System.out.println("Erreur --" + e);
		};
		String verif="prénom;âge\r\nCécile"+LibCsv.separator+"18 ans\r\n";
		if(verif.equals(concat)){
			contenu=false;
		}
		
		assertTrue("Verification contenu écrit/fichier", contenu);
	}
	
}