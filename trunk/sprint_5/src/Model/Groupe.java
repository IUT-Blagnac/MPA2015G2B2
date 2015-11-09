package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class Groupe extends Entity{
	
	private ArrayList<Etudiant> membres;
	private int idSujet;
	private HashMap<Sujet, Integer> voeux;

	public Groupe(Etudiant[] etudiants, int numGroupe, int idSujet) {
		this.membres = new ArrayList<>(4);
		for(int i=0; i<etudiants.length; i++)
			this.membres.add(etudiants[i]);
		setId(numGroupe);
		this.idSujet = idSujet;
		voeux = new HashMap<>();
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
		voeux = new HashMap<>();
	}
	
	public boolean addEtudiant(Etudiant e){
		if(membres.add(e)){
			e.setNumGroupe(this.getId());
			return true;
		}
		return false;
	}
	
	public boolean removeEtudiant(Etudiant e){
		if(membres.remove(e)){
			e.setNumGroupe(-1);
			return true;
		}
		return false;
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
	
	
	private int getIdEtudiant(String nom, String prenom) {
		for(int i=0; i<membres.size(); i++)
			if(membres.get(i).getNom().equals(nom) && membres.get(i).getPrenom().equals(prenom))
				return i;
		return -1;
	}

	public HashMap<Sujet, Integer> getVoeux() {
		return voeux;
	}

	public void setVoeux(HashMap<Sujet, Integer> voeux) {
		this.voeux = voeux;
	}
	
	public void setVoeu(Sujet s, int v){
		voeux.remove(s);
		voeux.put(s, v);
	}
	
	public Sujet getSujet(int v){
		Sujet[] s = voeux.keySet().toArray(new Sujet[voeux.size()]);
		for(int i=0; i< voeux.size(); i++){
			if(voeux.get(s[i]) == v)
				return s[i];
		}
		return null;
	}
	
	public String getVoeu(Sujet s){
		return voeux.get(s)+"";
	}
	
	public void addSujetInList(Sujet s){
		this.voeux.put(s, 0);
	}
	
	public void removeSujetInList(Sujet s){
		this.voeux.remove(s);
	}
	
	public String getIdChar(){
		String s = "";
		
		s += (char)(64 + getId());
		
		return s;
	}
	
	public String toString (){
		return "| " + this.membres.size() + " | " + "Groupe " + this.getIdChar();
	}

}
