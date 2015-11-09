package Model;

public class Etudiant extends Entity{
	
	private String nom, prenom;
	private char tp;
	private byte td;
	private int numGroupe;
		
		
		/**
	 * Constructeur param�tr� <br/><br/>
	 * 
	 * Permet de cr�er un Etudiant en donnant sont nom, son pr�nom, son groupe de td, de tp et son num�ro de groupe
	 *
	 * @param id L'id de l'�tudiant
	 * @param nom Nom de l'�tudiant
	 * @param prenom Pr�nom de l'�tudiant
	 * @param tp Groupe de TP de l'�tudiant
	 * @param td Groupe de TD de l'�tudiant
	 * @param numG Num�ro du groupe  de l'�tudiant
	 */
	public Etudiant(int id, String nom, String prenom,int numG){
		this.setId(id);
		this.prenom = prenom;
		this.nom = nom;
		this.numGroupe = numG;
	}
	
	/**
	 * Constructeur param�tr� <br/><br/>
	 * 
	 * Permet de cr�er un Etudiant en donnant sont nom, son pr�nom, son groupe de td et son groupee de tp.
	 * 
	 * @param id L'id de l'�tudiant
	 * @param nom Nom de l'�tudiant
	 * @param prenom Pr�nom de l'�tudiant
	 * @param tp Groupe de TP de l'�tudiant
	 * @param td Groupe de TD de l'�tudiant
	 */
	public Etudiant(int id, String nom, String prenom) {
		this(id, nom, prenom, -1);
	}
	
	/**
	 * Constructeur param�tr� <br/><br/>
	 * 
	 * Permet de cr�er un Etudiant en donnant sont nom, son pr�nom, son groupe de td et son groupee de tp.
	 * 
	 * @param nom Nom de l'�tudiant
	 * @param prenom Pr�nom de l'�tudiant
	 * @param tp Groupe de TP de l'�tudiant
	 * @param td Groupe de TD de l'�tudiant
	 */
	public Etudiant(String nom, String prenom) {
		this(0, nom, prenom, -1);
	}

	/**
	 * Permet d'obtenir le nom de l'�tudiant 
	 * @return nom
	 * 				le nom de l'�tudiant
	 */
	public String getNom() {
		return nom;
	}

		/**
	 * Permet de modifier le nom de l'�tudiant 
	 * @param nom
	 * 				le nom de l'�tudiant
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

		/**
	 * Permet d'obtenir le pr�nom de l'�tudiant 
	 * @return prenom
	 * 				le pr�nom de l'�tudiant
	 */
	public String getPrenom() {
		return prenom;
	}

		/**
	 * Permet de modifier le pr�nom de l'�tudiant 
	 * @param prenom
	 * 				le pr�nom de l'�tudiant
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
		/**
	 * 
	 *Permet d'obtenir le num�ro de TP de l'�tudiant
	 * @return tp
	 * 			Le num�ro de tp de l'�tudiant
	 */
	public char getTp() {
		return tp;
	}

		/**
	 * 
	 *Permet de modifier le num�ro de TP de l'�tudiant
	 * @param tp
	 * 			Le num�ro de tp de l'�tudiant(int)
	 */
	public void setTp(char tp) {
		this.tp = tp;
	}
		/**
	 * 
	 *Permet de modifier le num�ro de TP de l'�tudiant
	 * @param tp
	 * 			Le num�ro de tp de l'�tudiant(String)
	 */
	public void setTp(String tp) {
		this.tp = tp.charAt(0);
	}

		/**
	 * 
	 * Permet d'obtenir le num�ro de TD de l'�tudiant
	 * @return td
	 * 				Le num�ro de TD de l'�tudiant
	 */
	public int getTd() {
		return td;
	}

		/**
	 * 
	 * Permet de modifier le num�ro de TD de l'�tudiant
	 * @param td
	 * 				Le num�ro de TD de l'�tudiant
	 */
	public void setTd(int td) {
		this.td = (byte) td;
	}

	/**
	 * Permet d'obtenir le num�ro du groupe de l'�tudiant
	 * @return numGroupe
	 * 					le num�ro du groupe de l'�tudiant
	 */
	public int getNumGroupe() {
		return numGroupe;
	}

	/**
	 * Permet de modifier le num�ro du groupe de l'�tudiant
	 * @param numGroupe
	 * 					le num�ro du groupe de l'�tudiant
	 */
	public void setNumGroupe(int numGroupe) {
		this.numGroupe = numGroupe;
	}
	
	public String toString(){
		return nom.toUpperCase() + " " + prenom;
	}

}
