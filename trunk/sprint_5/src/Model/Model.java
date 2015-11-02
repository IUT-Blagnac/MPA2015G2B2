package Model;

import java.util.ArrayList;

import Controller.Controller;
import View.Fenetre;

public class Model {
	
		private ArrayList<Sujet> arraySujets;
		private ArrayList<Etudiant> arrayEtudiants;
		private ArrayList<Groupe> arrayGroupes;
		private ArrayList<Intervenant> arrayInterv;
		
		private Controller ctrl;
		private Fenetre view;

		public Model(Controller ctrl){
			this.ctrl = ctrl;
			
			this.arraySujets = new ArrayList<Sujet>();
			this.arrayEtudiants = new ArrayList<Etudiant>();
			this.arrayGroupes = new ArrayList<Groupe>();
			this.arrayInterv = new ArrayList<Intervenant>();
		}
		
		public void setView(Fenetre view){
			this.view = view;
		}
		
		public Sujet getSujet(int id){
			for(int i=0; i<arraySujets.size(); i++){
				if(arraySujets.get(i).getId() == id)
					return arraySujets.get(i);
			}
			return null;
		}
		
		public int getNbSujet(){
			if(arraySujets == null)
				return 0;
			return arraySujets.size();
		}
		
		public int getNextIdSujet(){
			int nextId = 0;
			if(arraySujets.isEmpty())
				return 1;
			for(int i=0; i<arraySujets.size(); i++){
				if(arraySujets.get(i).getId()!=++nextId)
					return nextId;
			}
			
			return ++nextId;
		}
		
		public ArrayList<Sujet> getArraySujets() {
			return new ArrayList<Sujet>(arraySujets);
		}

		public void setArraySujets(ArrayList<Sujet> arraySujets) {
			this.arraySujets = arraySujets;
		}
		
		public void addSujet(Sujet s){
			arraySujets.add(s);
		}
		
		public void modifSujet(Sujet s){
			for(int i=0; i<arraySujets.size(); i++){
				if(arraySujets.get(i).getId() == s.getId())
					arraySujets.set(i, s);
			}
		}
		
		public boolean removeSujet(int id){
			Sujet s = getSujet(id);
			System.out.println("trysuppres " + id);
			if(s == null)
				return false;
			System.out.println("suppres " + id);
			arraySujets.remove(s);
			for(int i=0; i<arrayGroupes.size(); i++)
				if(arrayGroupes.get(i).getIdSujet() == s.getId()){
					arrayGroupes.get(i).setIdSujet(0);
					return true;
				}
			return false;
		}
		
		public Groupe getGroupe(int id){
			for(int i=0; i<arrayGroupes.size(); i++)
				if(arrayGroupes.get(i).getId() == id)
					return arrayGroupes.get(i);
			return null;
		}
		
		public ArrayList<Groupe> getArrayGroupe() {
			return new ArrayList<Groupe>(arrayGroupes);
		}

		public void setArrayGroupe(ArrayList<Groupe> arrayGroupe) {
			this.arrayGroupes = arrayGroupe;
		}
		
		public void createGroupe(){
			arrayGroupes.add(new Groupe(arrayGroupes.size()+1));
		}
		
		public void createGroupe(Etudiant[] etudiants){
			for(int i=0; i< etudiants.length; i++)
				if(etudiants[i].getNumGroupe() != 0)
					arrayGroupes.get(etudiants[i].getNumGroupe()).removeEtudiant(etudiants[i]);
			arrayGroupes.add(new Groupe(etudiants, arrayGroupes.size()+1));
		}
		
		public void removeGroupe(int numGroupe){
			ArrayList<Etudiant> etus = arrayGroupes.get(numGroupe).getEtudiants();
			
			for(int i=0; i<etus.size(); i++){
				etus.get(i).setNumGroupe(0);
			}
			arrayGroupes.remove(numGroupe);
			
			for(int i=numGroupe; i<arrayGroupes.size(); i++){
				arrayGroupes.get(i).setNumGroupe(i);
			}
		}

		public Groupe getGroupe(Sujet s){
			for(int i=0; i<arrayGroupes.size(); i++)
				if(arrayGroupes.get(i).getIdSujet() == s.getId()){
					return arrayGroupes.get(i);
				}
			return null;
		}
		
		public void addEtudiant(Etudiant e){
			arrayEtudiants.add(e);
		}
		
		public void addEtudiant(Etudiant e, Sujet s){
			this.addEtudiant(e);
			this.getGroupe(s).addEtudiant(e);
		}
		
		public void addEtudiant(Etudiant e, int numGroupe){
			this.addEtudiant(e);
			this.arrayGroupes.get(numGroupe-1).addEtudiant(e);
		}
		
		public int nbEtudiant(){
			return arrayEtudiants.size();
		}
		
		public Etudiant getEtudiant(int id){
			for(int i=0; i<arrayEtudiants.size(); i++){
				if(arrayEtudiants.get(i).getId() == id)
					return arrayEtudiants.get(i);
			}
			return null;
		}
		
		public void removeEtudiant(int id){
			for(int i=0; i<arrayEtudiants.size(); i++){
				if(arrayEtudiants.get(i).getId() == id)
					arrayEtudiants.remove(i);
			}
		}
		
		public ArrayList<Etudiant> getArrayEtudiant(){
			return new ArrayList<>(arrayEtudiants);
		}
		
		public int getNextIdEtudiant(){
			int nextId = 0;
			if(arrayEtudiants.isEmpty())
				return 1;
			for(int i=0; i<arrayEtudiants.size(); i++){
				if(arrayEtudiants.get(i).getId()!=++nextId)
					return nextId;
			}
			
			return ++nextId;
		}
		
		public void addInterv(Intervenant i){
			arrayInterv.add(i);
		}
		
		public ArrayList<Intervenant> getArrayInterv() {
			return new ArrayList<Intervenant>(arrayInterv);
		}

		public void setArrayInterv(ArrayList<Intervenant> arrayInterv) {
			this.arrayInterv = arrayInterv;
		}
		
		public int getNextIdInterv(){
			int nextId = 0;
			if(arrayInterv.isEmpty())
				return 1;
			for(int i=0; i<arrayInterv.size(); i++){
				if(arrayInterv.get(i).getId()!=++nextId)
					return nextId;
			}
			
			return ++nextId;
		}
	
	
	
	
	
	
	
	

}
