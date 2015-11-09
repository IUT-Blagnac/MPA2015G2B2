/**
 * 
 */
package Model;

/**
 * @author Sorény
 *
 */
public class Projet extends Entity {
	
	private Sujet sujet;
	private Groupe grp;
	private Intervenant client, superviseur, support;
	
	public Projet(int id){
		setId(id);
	}
	
	public Projet(int id, Sujet s, Groupe g, Intervenant client, Intervenant superviseur, Intervenant support){
		this(id);
		this.sujet = s;
		this.grp = g;
		this.client = client;
		this.superviseur = superviseur;
		this.support = support;
	}

	/**
	 * @return the sujet
	 */
	public Sujet getSujet() {
		return sujet;
	}

	/**
	 * @param sujet the sujet to set
	 */
	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}

	/**
	 * @return the grp
	 */
	public Groupe getGrp() {
		return grp;
	}

	/**
	 * @param grp the grp to set
	 */
	public void setGrp(Groupe grp) {
		this.grp = grp;
	}

	/**
	 * @return the client
	 */
	public Intervenant getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Intervenant client) {
		this.client = client;
	}

	/**
	 * @return the support
	 */
	public Intervenant getSupport() {
		return support;
	}

	/**
	 * @param support the support to set
	 */
	public void setSupport(Intervenant support) {
		this.support = support;
	}

	/**
	 * @return the superviseur
	 */
	public Intervenant getSuperviseur() {
		return superviseur;
	}

	/**
	 * @param superviseur the superviseur to set
	 */
	public void setSuperviseur(Intervenant superviseur) {
		this.superviseur = superviseur;
	}
	
	public String toString(){
		if(sujet!=null || grp!=null){
			String s = "";
			if(sujet!=null)
				s = sujet.getAcronyme();
			else
				s = "no_sujet";
			s+= " | ";
			if(grp!=null)
				s+=grp.getIdChar();
			else
				s+="no_groupe";
			return s;
		}
		
		return "Projet n°" + getId();
	}
	
}
