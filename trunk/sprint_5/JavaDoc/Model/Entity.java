/**
 * 
 */
package Model;

/**
 * Entity est une classe abstraite qui d�finit l'attibut id commun � plusieurs classes de notre projet
 * @author Sor�ny
 *
 */
public abstract class Entity {
	
	private int id;

		/**
	 * Permet d'obtenir l'id de l'entit�
	 * @return id
	 * 			L'id de l'entit�
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * Permet de modifier l'id de l'entit�  
	 * @param id
	 * 			L'id de l'entit� � modifier
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	

}
