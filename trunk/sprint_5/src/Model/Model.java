package Model;

import java.util.ArrayList;
import java.util.HashMap;

import Controller.Controller;
import View.Fenetre;

public class Model {
	
		private ArrayList<Sujet> arraySujets;
		private ArrayList<Etudiant> arrayEtudiants;
		private ArrayList<Groupe> arrayGroupes;
		private ArrayList<Intervenant> arrayInterv;
		private ArrayList<Projet> arrayProjets;
		
		private Controller ctrl;
		private Fenetre view;

		public Model(Controller ctrl){
			this.ctrl = ctrl;
			
			this.arraySujets = new ArrayList<Sujet>();
			this.arrayEtudiants = new ArrayList<Etudiant>();
			this.arrayGroupes = new ArrayList<Groupe>();
			this.arrayInterv = new ArrayList<Intervenant>();
			this.arrayProjets = new ArrayList<>();
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
			for(int i=0; i<arrayGroupes.size(); i++)
				arrayGroupes.get(i).addSujetInList(s);
		}
		
		public void modifSujet(Sujet s){
			for(int i=0; i<arraySujets.size(); i++){
				if(arraySujets.get(i).getId() == s.getId()){
					Sujet p = arraySujets.get(i);
					p.setAcronyme(s.getAcronyme());
					p.setBesoins(s.getBesoins());
					p.setContexte(s.getContexte());
					p.setCorrespondant(s.getCorrespondant());
					p.setOutils(s.getOutils());
					p.setTitre(s.getTitre());
				}
			}
		}
		
		public void removeSujet(int id){
			Sujet s = getSujet(id);
			if(s == null)
				return;
			arraySujets.remove(s);
			for(int i=0; i<arrayProjets.size(); i++){
				if(arrayProjets.get(i).getSujet()==s){
					arrayProjets.get(i).setSujet(null);
				}
				arrayGroupes.get(i).removeSujetInList(s);
			}
		}
		
		public Groupe getGroupe(int id){
			for(int i=0; i<arrayGroupes.size(); i++)
				if(arrayGroupes.get(i).getId() == id)
					return arrayGroupes.get(i);
			return null;
		}
		
		public Groupe getGroupe(char id){
			int idI = (int) id;
			idI-=64;
			return getGroupe(idI);
		}
		
		public ArrayList<Groupe> getArrayGroupe() {
			return new ArrayList<Groupe>(arrayGroupes);
		}

		public void setArrayGroupe(ArrayList<Groupe> arrayGroupe) {
			this.arrayGroupes = arrayGroupe;
			for(int i=0; i<arrayGroupe.size(); i++)
				for(int j=0; j<arraySujets.size(); j++)
					this.arrayGroupes.get(i).addSujetInList(arraySujets.get(j));
		}
		
		public void createGroupe(){
			arrayGroupes.add(new Groupe(arrayGroupes.size()+1));
			for(int i=0; i<arraySujets.size(); i++){
				arrayGroupes.get(arrayGroupes.size()-1).addSujetInList(arraySujets.get(i));
			}
		}
		
		public void createGroupe(Etudiant[] etudiants){
			for(int i=0; i< etudiants.length; i++)
				if(etudiants[i].getNumGroupe() != 0)
					arrayGroupes.get(etudiants[i].getNumGroupe()).removeEtudiant(etudiants[i]);
			arrayGroupes.add(new Groupe(etudiants, arrayGroupes.size()+1));
		}
		
		public void removeGroupe(Groupe g){
			ArrayList<Etudiant> etus = g.getEtudiants();
			
			for(int i=0; i<etus.size(); i++){
				etus.get(i).setNumGroupe(-1);
			}
			arrayGroupes.remove(g);
			
			for(int i=0; i<arrayGroupes.size(); i++){
				arrayGroupes.get(i).setNumGroupe(i+1);
			}
			
			for(int i=0; i<arrayProjets.size(); i++){
				if(arrayProjets.get(i).getGrp() == g)
					arrayProjets.get(i).setGrp(null);
			}
		}
		
		public void setArrayEtudiants(ArrayList<Etudiant> array){
			this.arrayEtudiants = array;
		}
		
		public void addEtudiant(Etudiant e){
			arrayEtudiants.add(e);
		}
		
		public void addEtudiant(Etudiant e, int numGroupe){
			this.addEtudiant(e);
			this.arrayGroupes.get(numGroupe-1).addEtudiant(e);
		}
		
		public int nbEtudiant(){
			return arrayEtudiants.size();
		}
		
		public Groupe etuIsGrouped(Etudiant e){
			
			for(int i=0; i<arrayGroupes.size(); i++){
				if(arrayGroupes.get(i).getEtudiants().contains(e))
					return arrayGroupes.get(i);
			}
			
			return null;
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
		
		public Intervenant getInterv(int id){
			for(int i=0; i<arrayInterv.size(); i++){
				if(arrayInterv.get(i).getId() == id)
					return arrayInterv.get(i);
			}
			return null;
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

		/**
		 * @return the arrayProjets
		 */
		public ArrayList<Projet> getArrayProjets() {
			return new ArrayList<Projet>(this.arrayProjets);
		}

		/**
		 * @param arrayProjets the arrayProjets to set
		 */
		public void setArrayProjets(ArrayList<Projet> arrayProjets) {
			this.arrayProjets = arrayProjets;
		}
		
		public void addProjet(Projet p){
			this.arrayProjets.add(p);
		}
		
		public int getNextIdProjet(){
			int nextId = 0;
			if(arrayProjets.isEmpty())
				return 1;
			for(int i=0; i<arrayProjets.size(); i++){
				if(arrayInterv.get(i).getId()!=++nextId)
					return nextId;
			}
			
			return ++nextId;
		}
	
	
	
	
	
	
	
	

}
