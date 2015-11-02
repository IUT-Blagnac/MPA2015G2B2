package Model;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Cette classe permet de créer, modifier et supprimer un Groupe.
 * Un groupe est composé d'une liste d'objets Etudiant, d'un numéro de groupe, d'un Id de sujet et d'une liste de voeux
 * @author Thomas
 *
 */
public class Groupe extends Entity{
	
	private ArrayList<Etudiant> membres;
	private int idSujet;
	private int[] voeux = new int[0];

	/**
	 * Constructeur paramétré : <br /> <br />
	 * Permet de créer un groupe en donnant un tableau d'étudiant, un numéro de groupe et l'id d'un sujet
	 * 
	 * @param etudiants Tableau d'étudiant
	 * @param numGroupe Numéro du groupe
	 * @param idSujet Id du sujet
	 */
	public Groupe(Etudiant[] etudiants, int numGroupe, int idSujet) {
		this.membres = new ArrayList<>(4);
		for(int i=0; i<etudiants.length; i++)
			this.membres.add(etudiants[i]);
		setId(numGroupe);
		this.idSujet = idSujet;
	}
	
		/**
	 * Constructeur paramétré : <br /> <br />
	 * Permet de créer un groupe en donnant une ArrayList d'étudiant, un numéro de groupe et l'id d'un sujet
	 * 
	 * @param etudiants ArrayList d'étudiant
	 * @param numGroupe Numéro du groupe
	 * @param idSujet Id du sujet
	 */
	public Groupe(ArrayList<Etudiant> etudiants, int numGroupe, int idSujet) {
		this( (Etudiant[]) etudiants.toArray(), numGroupe, idSujet);
	}
	
		/**
	 * Constructeur paramétré : <br /> <br />
	 * Permet de créer un groupe en donnant un tableau d'étudiant et un numéro de groupe
	 * 
	 * @param etudiants Tableau d'étudiant
	 * @param numGroupe Numéro du groupe
	 */
	public Groupe(Etudiant[] etudiant, int numGroupe){
		this(etudiant, numGroupe, 0);
	}
	
		/**
	 * Constructeur paramétré : <br /> <br />
	 * Permet de créer un groupe en donnant une ArrayList d'étudiant et un numéro de groupe
	 * 
	 * @param etudiants ArrayList d'étudiant
	 * @param numGroupe Numéro du groupe
	 */
	public Groupe(ArrayList<Etudiant> etudiant, int numGroupe){
		this(etudiant, numGroupe, 0);
	}
	
		/**
	 * Constructeur paramétré : <br /> <br />
	 * Permet de créer un groupe en donnant un numéro de groupe et l'id d'un sujet
	 * 
	 * @param numGroupe Numéro du groupe
	 * @param idSujet Id du sujet
	 */
	public Groupe(int numGroupe, int idSujet){
		this(new ArrayList<Etudiant>(), numGroupe, idSujet);
	}
	
		/**
	 * Constructeur paramétré : <br /> <br />
	 * Permet de créer un groupe en donnant un numéro de groupe
	 * 
	 * @param numGroupe Numéro du groupe
	 */
	public Groupe(int numGroupe){
		this.membres = new ArrayList<>(4);
		membres.add(new Etudiant("sapin", "sapin"+numGroupe));
		setId(numGroupe);
		this.idSujet = 0;
	}
	
		/**
	 * Permet d'ajouter un étudiant dans un goupe
	 * @param e
	 * 			Etudiant à ajouter
	 * @return true si l'ajout s'est bien passé, false sinon
	 */
	public boolean addEtudiant(Etudiant e){
		if(membres.add(e)){
			e.setNumGroupe(this.idSujet);
			return true;
		}
		return false;
	}
	
	/**
	 * Permet de supprimer un étudiant dans un groupe
	 * @param e Etudiant à supprimer
	 * @return true si la suppression s'est bien passée, false sinon
	 */
	public boolean removeEtudiant(Etudiant e){
		if(membres.remove(e)){
			e.setNumGroupe(0);
			return true;
		}
		return false;
	}
	
	/**
	 * Permet de supprimer un étudiant dans un groupe en donnant son nom et sont prénom
	 * @param nom Nom de l'étudiant à supprimer
	 * @param prenom Prénom de l'étudiant à supprimer
	 * @return true si la suppression s'est bien passée, false sinon
	 */
	public boolean removeEtudiant(String nom, String prenom){
		int i = this.getIdEtudiant(nom, prenom);
		if(i==-1)
			return false;
		membres.remove(i);
		return true;
	}
	
		/**
	 * Permet d'obtenir un Etudiant en donnant son nom et son prénom
	 * @param nom Nom de l'étudiant
	 * @param prenom Prénom de l'étudiant
	 * @return membres.get(i) L'Etudiant voulu ou null s'il n'a pas été trouvé.
	 */
	public Etudiant getEtudiant(String nom, String prenom){
		for(int i=0; i<membres.size(); i++)
			if(membres.get(i).getNom().equals(nom) && membres.get(i).getPrenom().equals(prenom))
				return membres.get(i);
		return null;
	}
	
	/**
	 * Permet d'obtenir la liste des Etudiants
	 * @return ArrayList<Etudiant>(membres) Une ArrayList d'étudiant
	 */
	public ArrayList<Etudiant> getEtudiants(){
		return new ArrayList<Etudiant>(membres);
	}

		/**
	 * Permet d'obtenir le numéro du groupe
	 * @return numG Le numéro du groupe
	 */
	public int getNumGroupe() {
		return getId();
	}

/**
	 * Permet de modifier le numéro du groupe
	 * @param numG Le nouveau numéro du groupe 
	 */
	public void setNumGroupe(int numG) {
		setId(numG);
		for(int i=0; i<membres.size(); i++)
			membres.get(i).setNumGroupe(numG);
	}
	
	/**
	 * Permet d'obtenir l'id du sujet
	 * @return idSujet L'id du sujet
	 */
	public int getIdSujet() {
		return idSujet;
	}

		/**
	 * Permet de modifier l'id du sujet
	 * @param idSujet Le nouvel id du sujet 
	 */
	public void setIdSujet(int idSujet) {
		this.idSujet = idSujet;
	}
	
		/**
	 * Permet d'obtenir l'id d'un Etudiant en donnant son nom et son prénom
	 * @param nom Nom de l'Etudiant
	 * @param prenom Prénom de l'Etudiant
	 * @return i L'id de l'étudiant ou -1 s'il n'a pas été trouvé
	 */
	private int getIdEtudiant(String nom, String prenom) {
		for(int i=0; i<membres.size(); i++)
			if(membres.get(i).getNom().equals(nom) && membres.get(i).getPrenom().equals(prenom))
				return i;
		return -1;
	}

		/**
	 * Permet d'obtenir la liste des voeux
	 * @return voeux Un tableau de voeux
	 */
	public int[] getVoeux() {
		return voeux;
	}

		/**
	 * Permet de modifier le tableau de voeux
	 * @param voeux Le nouveau tableau de voeux
	 */
	public void setVoeux(int[] voeux) {
		this.voeux = voeux;
	}
	/**
	* Permet l'affichage d'un Objet Groupe
	*
	*/
	public String toString (){
		return "| " + this.membres.size() + " | " + "Groupe " + this.getId();
	}

}
