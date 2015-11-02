/**
 * 
 */
package Model;

/**
 * Entity est une classe abstraite qui définit l'attibut id commun à plusieurs classes de notre projet
 * @author Sorény
 *
 */
public abstract class Entity {
	
	private int id;

		/**
	 * Permet d'obtenir l'id de l'entité
	 * @return id
	 * 			L'id de l'entité
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * Permet de modifier l'id de l'entité  
	 * @param id
	 * 			L'id de l'entité à modifier
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	

}
