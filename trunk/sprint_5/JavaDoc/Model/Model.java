package Model;

import java.util.ArrayList;

import Controller.Controller;
import View.Fenetre;

/**
 * Cette classe contient les données brutes de notre programme. Elle est issues du Pattern MVC
 * @author Thomas
 *
 */
 
public class Model {
	
		private ArrayList<Sujet> arraySujets;
		private ArrayList<Etudiant> arrayEtudiants;
		private ArrayList<Groupe> arrayGroupes;
		private ArrayList<Intervenant> arrayInterv;
		
		private Controller ctrl;
		private Fenetre view;

			/**
		 * Constructeur paramétré :
		 * 
		 * Permet de créer un objet Model en donnant un Contoller en paramètre. Initialise les ArrayLists
		 * @param ctrl Controlleur
		 */
		public Model(Controller ctrl){
			this.ctrl = ctrl;
			
			this.arraySujets = new ArrayList<Sujet>();
			this.arrayEtudiants = new ArrayList<Etudiant>();
			this.arrayGroupes = new ArrayList<Groupe>();
			this.arrayInterv = new ArrayList<Intervenant>();
		}
		
		/**
		 * Permet de définir la vue à utiliser
		 * @param view Objet de type Fenetre
		 */
		public void setView(Fenetre view){
			this.view = view;
		}
		
		/**
		 * Permet d'obtenir un Sujet en donnant son id
		 * @param id Id du sujet
		 * @return arraySujets.get(i) ou null s'il n'a pas été trouvé.
		 */
		public Sujet getSujet(int id){
			for(int i=0; i<arraySujets.size(); i++){
				if(arraySujets.get(i).getId() == id)
					return arraySujets.get(i);
			}
			return null;
		}
		/**
		 * Permet d'obenir le nombre de sujet.
		 * @return arraySujets.size() Nombre de sujet au total
		 */
		public int getNbSujet(){
			if(arraySujets == null)
				return 0;
			return arraySujets.size();
		}
		
			/**
		 * Permet de déterminer le prochain Id à attribuer au prochain Sujet
		 * @return 1 si arraySujets est vide / nextId sinon
		 */
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
		
			/**
		 * Permet d'obtenir la liste des sujets
		 * @return ArrayList<Sujet>(arraySujets) L'ArrayList de Sujet
		 */
		public ArrayList<Sujet> getArraySujets() {
			return new ArrayList<Sujet>(arraySujets);
		}
		
			/**
		 * Permet de modifier la liste des sujets
		 * @param arraySujets La nouvelle ArrayList de Sujet
		 */
		public void setArraySujets(ArrayList<Sujet> arraySujets) {
			this.arraySujets = arraySujets;
		}
		
			/**
		 * Permet d'ajouter un Sujet
		 * @param s Sujet à ajouter
		 */
		public void addSujet(Sujet s){
			arraySujets.add(s);
		}
		
		/**
		 * Permet de modifier un Sujet
		 * @param s Sujet à modifier
		 */
		public void modifSujet(Sujet s){
			for(int i=0; i<arraySujets.size(); i++){
				if(arraySujets.get(i).getId() == s.getId())
					arraySujets.set(i, s);
			}
		}
			/**
		 * Permet de supprimer un Sujet en donnant son id
		 * @param id Id du Sujet à supprimer
		 * @return true si la suppression à réussie, false sinon
		 */
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
		
		
		/** 
		* Permet d'obtenir un Groupe en donnant son id
		* @param id Id du groupe
		* @return arrayGroupes.get(i) L'ArrayList de groupe ou null s'il n'a pas été trouvé
		*/
		public Groupe getGroupe(int id){
			for(int i=0; i<arrayGroupes.size(); i++)
				if(arrayGroupes.get(i).getId() == id)
					return arrayGroupes.get(i);
			return null;
		}
		
			/**
		 * Permet d'obtenir la liste des groupes
		 * @return ArrayList<Groupe>(arrayGroupes) L'ArrayList de groupe
		 */
		public ArrayList<Groupe> getArrayGroupe() {
			return new ArrayList<Groupe>(arrayGroupes);
		}
		
		/**
		 * Permet de définir la liste des groupes
		 * @param arrayGroupe La nouvelle ArrayList de groupe
		 */
		public void setArrayGroupe(ArrayList<Groupe> arrayGroupe) {
			this.arrayGroupes = arrayGroupe;
		}
		
		/**
		 * Permet de créer un groupe
		 */
		public void createGroupe(){
			arrayGroupes.add(new Groupe(arrayGroupes.size()+1));
		}
		
		/**
		 * Permet de créer un groupe en donnant un liste d'Etudiant
		 * @param etudiants Tableau d'Etudiant
		 */
		public void createGroupe(Etudiant[] etudiants){
			for(int i=0; i< etudiants.length; i++)
				if(etudiants[i].getNumGroupe() != 0)
					arrayGroupes.get(etudiants[i].getNumGroupe()).removeEtudiant(etudiants[i]);
			arrayGroupes.add(new Groupe(etudiants, arrayGroupes.size()+1));
		}
		
		/**
		 * Permet de supprimer un groupe en donnant le numéro du groupe
		 * @param numGroupe Numéro du groupe à supprimer
		 */
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

		/**
		 * Permet d'obtenir un groupe en donnant un Sujet
		 * @param s Sujet
		 * @return arrayGroupes.get(i) ou null s'il n'a pas été trouvé
		 */
		public Groupe getGroupe(Sujet s){
			for(int i=0; i<arrayGroupes.size(); i++)
				if(arrayGroupes.get(i).getIdSujet() == s.getId()){
					return arrayGroupes.get(i);
				}
			return null;
		}
		
		/**
		 * Permet d'ajouter un Etudiant
		 * @param e Etudiant à ajouter
		 */
		public void addEtudiant(Etudiant e){
			arrayEtudiants.add(e);
		}
		
		/**
		 * Permet d'ajouter un Etudiant en donnant l'Etudiant à ajouter et le Sujet choisi.
		 * @param e Etudiant à ajouter
		 * @param s Sujet choisi
		 */
		public void addEtudiant(Etudiant e, Sujet s){
			this.addEtudiant(e);
			this.getGroupe(s).addEtudiant(e);
		}
		
			/**
		 * Permet d'ajouter un Etudiant dans la liste des étudiants et dans le groupe de numéro numGroupe
		 * @param e Etudiant à ajouter
		 * @param numGroupe Numéro de groupe
		 */
		public void addEtudiant(Etudiant e, int numGroupe){
			this.addEtudiant(e);
			this.arrayGroupes.get(numGroupe-1).addEtudiant(e);
		}
		
			/**
		 * Permet d'obtenir le nombre d'Etudiant
		 * @return arrayEtudiants.size() Le nombre d'Etudiant
		 */
		public int nbEtudiant(){
			return arrayEtudiants.size();
		}
		
			/**
		 * Permet d'obtenir l'Etudiant en donnant son id
		 * @param id Id de l'Etudiant
		 * @return arrayEtudiants.get(i) L'Etudiant ou null s'il n'a pas été trouvé
		 */
		public Etudiant getEtudiant(int id){
			for(int i=0; i<arrayEtudiants.size(); i++){
				if(arrayEtudiants.get(i).getId() == id)
					return arrayEtudiants.get(i);
			}
			return null;
		}
			/**
		 * Permet de supprimer l'Etudiant en donnant son id
		 * @param id Id de l'Etudiant à supprimer
		 */
		public void removeEtudiant(int id){
			for(int i=0; i<arrayEtudiants.size(); i++){
				if(arrayEtudiants.get(i).getId() == id)
					arrayEtudiants.remove(i);
			}
		}
		
			/**
		 * Permet d'obtenir la liste des Etudiants
		 * @return ArrayList<>(arrayEtudiants) L'ArrayLisrt d'Etudiant
		 */
		public ArrayList<Etudiant> getArrayEtudiant(){
			return new ArrayList<>(arrayEtudiants);
		}
		
		/**
		* Permet de déterminer le prochain Id à attribuer au prochain Etudiant
		 * @return 1 si arrayEtudiants est vide / nextId sinon
		 */
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
		
		/**
		 * Permet d'obtenir la liste des intervenants
		 * @return ArrayList<Intervenant>(arrayInterv) L'ArrayList des intervenants
		 */
		public ArrayList<Intervenant> getArrayInterv() {
			return new ArrayList<Intervenant>(arrayInterv);
		}

			/**
		 * Permet de définir la liste des intervenants
		 * @param arrayInterv la nouvelle ArrayList des intervenants
		 */
		public void setArrayInterv(ArrayList<Intervenant> arrayInterv) {
			this.arrayInterv = arrayInterv;
		}
	
	
	
	
	
	
	
	

}
