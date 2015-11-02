/**
 * 
 */
package Model;

/**
 * @author Sorény
 *
 */
public class Intervenant extends Entity{
	
	private String nom, prenom;

	
	public Intervenant(int id, String nom, String prenom){
		setId(id);
		this.nom = nom;
		this.prenom = prenom;
	}
	
	/**
	 * 
	 */
	public Intervenant(String nom, String prenom) {
		this(0, nom, prenom);
	}
	
	public String toString(){
		return nom.toUpperCase() + " " + prenom;
	}

}
