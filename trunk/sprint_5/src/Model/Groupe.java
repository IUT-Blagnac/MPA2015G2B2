package Model;

import java.util.ArrayList;
import java.util.TreeSet;

public class Groupe extends Entity{
	
	private ArrayList<Etudiant> membres;
	private int idSujet;
	private int[] voeux = new int[0];

	public Groupe(Etudiant[] etudiants, int numGroupe, int idSujet) {
		this.membres = new ArrayList<>(4);
		for(int i=0; i<etudiants.length; i++)
			this.membres.add(etudiants[i]);
		setId(numGroupe);
		this.idSujet = idSujet;
	}
	
	public Groupe(ArrayList<Etudiant> etudiants, int numGroupe, int idSujet) {
		this( (Etudiant[]) etudiants.toArray(), numGroupe, idSujet);
	}
	
	public Groupe(Etudiant[] etudiant, int numGroupe){
		this(etudiant, numGroupe, 0);
	}
	
	public Groupe(ArrayList<Etudiant> etudiant, int numGroupe){
		this(etudiant, numGroupe, 0);
	}
	
	public Groupe(int numGroupe, int idSujet){
		this(new ArrayList<Etudiant>(), numGroupe, idSujet);
	}
	
	public Groupe(int numGroupe){
		this.membres = new ArrayList<>(4);
		setId(numGroupe);
		this.idSujet = 0;
	}
	
	public boolean addEtudiant(Etudiant e){
		if(membres.add(e)){
			e.setNumGroupe(this.idSujet);
			return true;
		}
		return false;
	}
	
	public boolean removeEtudiant(Etudiant e){
		if(membres.remove(e)){
			e.setNumGroupe(0);
			return true;
		}
		return false;
	}
	
	public boolean removeEtudiant(String nom, String prenom){
		int i = this.getIdEtudiant(nom, prenom);
		if(i==-1)
			return false;
		membres.remove(i);
		return true;
	}
	
	public Etudiant getEtudiant(String nom, String prenom){
		for(int i=0; i<membres.size(); i++)
			if(membres.get(i).getNom().equals(nom) && membres.get(i).getPrenom().equals(prenom))
				return membres.get(i);
		return null;
	}
	
	public ArrayList<Etudiant> getEtudiants(){
		return new ArrayList<Etudiant>(membres);
	}

	public int getNumGroupe() {
		return getId();
	}


	public void setNumGroupe(int numG) {
		setId(numG);
		for(int i=0; i<membres.size(); i++)
			membres.get(i).setNumGroupe(numG);
	}
	
	public int getIdSujet() {
		return idSujet;
	}


	public void setIdSujet(int idSujet) {
		this.idSujet = idSujet;
	}
	
	
	private int getIdEtudiant(String nom, String prenom) {
		for(int i=0; i<membres.size(); i++)
			if(membres.get(i).getNom().equals(nom) && membres.get(i).getPrenom().equals(prenom))
				return i;
		return -1;
	}

	public int[] getVoeux() {
		return voeux;
	}

	public void setVoeux(int[] voeux) {
		this.voeux = voeux;
	}
	
	public String toString (){
		return "| " + this.membres.size() + " | " + "Groupe " + this.getId();
	}

}
