package Model;

public class Sujet extends Entity{
	
	private String acronyme;
	private String titre;
	private String contexte;
	private String besoins;
	private String outils;
	private String correspondant;
	
	public Sujet() {
		
		acronyme = "Indéfini";
		titre = "Indéfini";
		contexte  = "Indéfini";
		besoins = "Indéfini";
		outils = "Indéfini";
		correspondant = "Indéfini";
	
		
	}
	
	public Sujet(String pAcronyme, String pTitre, String pContexte, String pBesoins, String pOutils, String pCorrespondant ) {
		this(0, pAcronyme, pTitre, pContexte, pBesoins, pOutils, pCorrespondant);
	}
	
	public Sujet(int pId, String pAcronyme, String pTitre, String pContexte, String pBesoins, String pOutils, String pCorrespondant ) {
		
		this.setId(pId);
		acronyme = pAcronyme;
		titre = pTitre;
		contexte  = pContexte;
		besoins = pBesoins;
		outils = pOutils;
		correspondant = pCorrespondant;
	
		
	}

	public String getAcronyme() {
		return acronyme;
	}



	public void setAcronyme(String acronyme) {
		this.acronyme = acronyme;
	}



	public String getContexte() {
		return contexte;
	}



	public void setContexte(String contexte) {
		this.contexte = contexte;
	}



	public String getBesoins() {
		return besoins;
	}



	public void setBesoins(String besoins) {
		this.besoins = besoins;
	}



	public String getOutils() {
		return outils;
	}



	public void setOutils(String outils) {
		this.outils = outils;
	}



	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}



	public String getCorrespondant() {
		return correspondant;
	}



	public void setCorrespondant(String correspondant) {
		this.correspondant = correspondant;
	}
	
	public String toString(){
		
		return this.acronyme;
		
	}
	
	

}
