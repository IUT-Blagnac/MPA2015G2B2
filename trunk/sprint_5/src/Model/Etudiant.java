package Model;

public class Etudiant extends Entity{
	
	private String nom, prenom;
	private char tp;
	private byte td;
	private int numGroupe;
		
		
		/**
	 * Constructeur paramétré <br/><br/>
	 * 
	 * Permet de créer un Etudiant en donnant sont nom, son prénom, son groupe de td, de tp et son numéro de groupe
	 *
	 * @param id L'id de l'�tudiant
	 * @param nom Nom de l'étudiant
	 * @param prenom Prénom de l'étudiant
	 * @param tp Groupe de TP de l'étudiant
	 * @param td Groupe de TD de l'étudiant
	 * @param numG Numéro du groupe  de l'étudiant
	 */
	public Etudiant(int id, String nom, String prenom,int numG){
		this.setId(id);
		this.prenom = prenom;
		this.nom = nom;
		this.numGroupe = numG;
	}
	
	/**
	 * Constructeur paramétré <br/><br/>
	 * 
	 * Permet de créer un Etudiant en donnant sont nom, son prénom, son groupe de td et son groupee de tp.
	 * 
	 * @param id L'id de l'�tudiant
	 * @param nom Nom de l'étudiant
	 * @param prenom Prénom de l'étudiant
	 * @param tp Groupe de TP de l'étudiant
	 * @param td Groupe de TD de l'étudiant
	 */
	public Etudiant(int id, String nom, String prenom) {
		this(id, nom, prenom, 0);
	}
	
	/**
	 * Constructeur paramétré <br/><br/>
	 * 
	 * Permet de créer un Etudiant en donnant sont nom, son prénom, son groupe de td et son groupee de tp.
	 * 
	 * @param nom Nom de l'étudiant
	 * @param prenom Prénom de l'étudiant
	 * @param tp Groupe de TP de l'étudiant
	 * @param td Groupe de TD de l'étudiant
	 */
	public Etudiant(String nom, String prenom) {
		this(0, nom, prenom, -1);
	}

	/**
	 * Permet d'obtenir le nom de l'étudiant 
	 * @return nom
	 * 				le nom de l'étudiant
	 */
	public String getNom() {
		return nom;
	}

		/**
	 * Permet de modifier le nom de l'étudiant 
	 * @param nom
	 * 				le nom de l'étudiant
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

		/**
	 * Permet d'obtenir le prénom de l'étudiant 
	 * @return prenom
	 * 				le prénom de l'étudiant
	 */
	public String getPrenom() {
		return prenom;
	}

		/**
	 * Permet de modifier le prénom de l'étudiant 
	 * @param prenom
	 * 				le prénom de l'étudiant
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
		/**
	 * 
	 *Permet d'obtenir le numéro de TP de l'étudiant
	 * @return tp
	 * 			Le numéro de tp de l'étudiant
	 */
	public char getTp() {
		return tp;
	}

		/**
	 * 
	 *Permet de modifier le numéro de TP de l'étudiant
	 * @param tp
	 * 			Le numéro de tp de l'étudiant(int)
	 */
	public void setTp(char tp) {
		this.tp = tp;
	}
		/**
	 * 
	 *Permet de modifier le numéro de TP de l'étudiant
	 * @param tp
	 * 			Le numéro de tp de l'étudiant(String)
	 */
	public void setTp(String tp) {
		this.tp = tp.charAt(0);
	}

		/**
	 * 
	 * Permet d'obtenir le numéro de TD de l'étudiant
	 * @return td
	 * 				Le numéro de TD de l'étudiant
	 */
	public int getTd() {
		return td;
	}

		/**
	 * 
	 * Permet de modifier le numéro de TD de l'étudiant
	 * @param td
	 * 				Le numéro de TD de l'étudiant
	 */
	public void setTd(int td) {
		this.td = (byte) td;
	}

	/**
	 * Permet d'obtenir le numéro du groupe de l'étudiant
	 * @return numGroupe
	 * 					le numéro du groupe de l'étudiant
	 */
	public int getNumGroupe() {
		return numGroupe;
	}

	/**
	 * Permet de modifier le numéro du groupe de l'étudiant
	 * @param numGroupe
	 * 					le numéro du groupe de l'étudiant
	 */
	public void setNumGroupe(int numGroupe) {
		this.numGroupe = numGroupe;
	}
	
	public String toString(){
		return nom.toUpperCase() + " " + prenom;
	}

}
