package Model;

/**
 * Cette classe permet de créer, modifier et supprimer un Sujet.
 * Un sujet est composé d'un acronyme, d'un titre, d'un contexte, de besoins, d'outils et de correspondant.
 * @author Thomas
 *
 */
public class Sujet extends Entity{
	
	private String acronyme;
	private String titre;
	private String contexte;
	private String besoins;
	private String outils;
	private String correspondant;
	
		/**
	 * Constructeur par défaut : <br /> <br />
	 * Permet de créer un sujet. Initialise chacun des sujets par une chaine "indéfini"
	 */
	public Sujet() {
		
		acronyme = "Indéfini";
		titre = "Indéfini";
		contexte  = "Indéfini";
		besoins = "Indéfini";
		outils = "Indéfini";
		correspondant = "Indéfini";
	
		
	}
	
		/**
	 * Constructeur paramétré : <br /> <br />
	 * Permet de créer un sujet en donnant un acronyme, un titre, un contexte, un besoin, un outil, un correspondant.
	 * @param pAcronyme L'acronyme
	 * @param pTitre Le Titre 
	 * @param pContexte Le Contexte
	 * @param pBesoins Les besoins
	 * @param pOutils Les outils
	 * @param pCorrespondant Les correspondants
	 */
	public Sujet(String pAcronyme, String pTitre, String pContexte, String pBesoins, String pOutils, String pCorrespondant ) {
		this(0, pAcronyme, pTitre, pContexte, pBesoins, pOutils, pCorrespondant);
	}
	
		/**
	 * Constructeur paramétré : <br /> <br />
	 * Permet de créer un sujet en donnant un id, un acronyme, un titre, un contexte, un besoin, un outil, un correspondant.
	 * @param pId L'id du sujet
	 * @param pAcronyme L'acronyme
	 * @param pTitre Le Titre 
	 * @param pContexte Le Contexte
	 * @param pBesoins Les besoins
	 * @param pOutils Les outils
	 * @param pCorrespondant Les correspondants
	 */
	public Sujet(int pId, String pAcronyme, String pTitre, String pContexte, String pBesoins, String pOutils, String pCorrespondant ) {
		
		this.setId(pId);
		acronyme = pAcronyme;
		titre = pTitre;
		contexte  = pContexte;
		besoins = pBesoins;
		outils = pOutils;
		correspondant = pCorrespondant;
	
		
	}

	/**
	 * Permet d'obtenir l'acronyme du Sujet
	 * @return acronyme L'acronyme
	 */
	public String getAcronyme() {
		return acronyme;
	}


	/**
	 * Permet de définir l'acronyme du Sujet
	 * @param acronyme Le nouvel acronyme
	 */
	public void setAcronyme(String acronyme) {
		this.acronyme = acronyme;
	}


	/**
	 * Permet d'obtenir le contexte
	 * @return contexte Le contexte
	 */
	public String getContexte() {
		return contexte;
	}


	/**
	 * Permet de définir un contexte
	 * @param contexte Le nouveau contexte
	 */
	public void setContexte(String contexte) {
		this.contexte = contexte;
	}


	/**
	 * Permet d'obtenir les besoins
	 * @return besoins Les besoins
	 */ 
	public String getBesoins() {
		return besoins;
	}


	/**
	 * Permet de définir un besoin
	 * @param besoins Le nouveau besoin
	 */
	public void setBesoins(String besoins) {
		this.besoins = besoins;
	}


/**
	 * Permet d'obtenir les outils
	 * @return outils Les outils
	 */
	public String getOutils() {
		return outils;
	}


	/**
	 * Permet de définir les outils
	 * @param outils Nouveaux outils
	 */
	public void setOutils(String outils) {
		this.outils = outils;
	}


	/**
	 * Permet d'obtenir le titre du Sujet
	 * 
	 * @return titre Titre
	 */
	public String getTitre() {
		return titre;
	}


/**
	 * Permet de définir un titre
	 * @param titre Le nouveau titre
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}


/**
	 * Permet d'obtenir les correspondants
	 * @return correspondant Les correspondants
	 */
	public String getCorrespondant() {
		return correspondant;
	}


	/**
	 * Permet de définir un correspondant
	 * @param correspondant Les nouveaux correspondants
	 */
	public void setCorrespondant(String correspondant) {
		this.correspondant = correspondant;
	}
	
		/**
	 * Permet l'affichage d'un objet Sujet
	 */
	public String toString(){
		
		return this.acronyme;
		
	}
	
	

}
