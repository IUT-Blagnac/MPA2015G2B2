package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Csv.LibCsv;
import Model.Entity;
import Model.Etudiant;
import Model.Groupe;
import Model.Intervenant;
import Model.Model;
import Model.Projet;
import Model.Sujet;
import View.CreerEtu;
import View.CreerInterv;
import View.CreerSujet;
import View.Fenetre;

public class Controller implements ActionListener, MouseListener {
	
	private Model model;
	private Fenetre view;
	
	public Controller() {
		this.model = new Model(this);
		this.view = new Fenetre("PTUT", this, model);
		this.model.setView(view);
		this.view.pack();
		this.view.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		
		switch(cmd){
		case "addSUJET":
			this.creation_sujet();
			break;
		case "addETUDIANT":
			this.creation_etudiant();
			break;
		case "addGROUPE":
			this.model.createGroupe();
			break;
		case "addINTERVENANT":
			this.creation_intervenant();
			break;
		case "addPROJET":
			this.model.addProjet(new Projet(model.getNextIdProjet()));
			break;
		case "about":
			view.about();
			break;
		case "ouvrirCsv":
			this.ouvrirCsv();
			break;
		case "sauvegarder":
			this.sauvegarderCsv();
			break;
		case "quit":
			view.quitter();
			break;
		}
		view.repaint();
	}
	
	public void addEtudiantsInGroupe(Etudiant e, Groupe g){
		if(!g.getEtudiants().contains(e)){
			Groupe grp = model.etuIsGrouped(e);
			if(grp!=null){
				int rep = JOptionPane.showConfirmDialog(view, "L'étudiant est déja assigné à un groupe voulez-vous le changer de groupe ?", "Etudiant déjà groupé", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(rep == 0){
					grp.removeEtudiant(e);
					g.addEtudiant(e);
				}
				
			}else
				g.addEtudiant(e);
		}else
			JOptionPane.showConfirmDialog(view, "Etudiant déjà présent dans le groupe", "Déjà present", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
		view.repaint();
	}
	
	private void creation_etudiant() {
		CreerEtu fenetre = new CreerEtu(view,"Ajout d'un étudiant", this);
		fenetre.setLocationRelativeTo(view);
		fenetre.setVisible(true);
		fenetre.pack();
		
	}
	
	public void creationEtudiant(Etudiant e){
		e.setId(model.getNextIdEtudiant());
		model.addEtudiant(e);
		view.repaint();
	}
	
	public void modifier_etudiant(Etudiant e){
		CreerEtu fenetre = new CreerEtu(view,"Modification d'un étudiant", this, e);
		fenetre.setLocationRelativeTo(view);
		fenetre.setVisible(true);
		fenetre.pack();
	}
	
	public void modifierEtudiant(Etudiant e){
		model.removeEtudiant(e.getId());
		model.addEtudiant(e);
		view.repaint();
	}
	
	public void supprimerEtudiant(Etudiant e){
		model.removeEtudiant(e.getId());
		view.repaint();
	}
	
	private void creation_intervenant() {
		CreerInterv fenetre = new CreerInterv(view ,"Ajout d'un Intervenant", this);
		fenetre.setLocationRelativeTo(view);
		fenetre.setVisible(true);
		fenetre.pack();
		
	}
	
	public void creationInterv(Intervenant i){
		i.setId(model.getNextIdInterv());
		model.addInterv(i);
	}
	
	public void modifierInterv(Intervenant i){
		//model.removeIntervenant(i.getId());
		model.addInterv(i);
		view.repaint();
	}
	
	/**
	 * Supprime le sujet selectionné de l'ArrayList ArraySujets
	 * 
	 */
	public void delSujet(Sujet s) {
		
		model.removeSujet(s.getId());
				
	}
	
	public void delGrp(Groupe g){
		model.removeGroupe(g);
		view.revalidate();
		view.repaint();
	}
	
	public void retireEtu(Etudiant e){
		model.getGroupe(e.getNumGroupe()).removeEtudiant(e);
		view.revalidate();
		view.repaint();
	}
	
	/**
	 * 
	 * Ouvre une fenêtre pour choisir un fichier csv à charger dans l'application
	 * 
	 */
	private void ouvrirCsv(){
		JFileChooser dialogue = new JFileChooser();
        int  returnVal = dialogue.showOpenDialog(view);
          try {
          	if(returnVal == JFileChooser.APPROVE_OPTION) {
          		ArrayList<String[]> arrayTemp = LibCsv.CSV_Read(dialogue.getSelectedFile().toString());
          		model.setArraySujets(LibCsv.translateArraySujet(arrayTemp));
          	}
				 
          }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void ouvrirProjetCsv(String path){
		try {
			ArrayList<String[]> arrayString = LibCsv.CSV_Read(path);
			ArrayList<Projet> projs = new ArrayList<>();
			
			for(int i=1; i<arrayString.size(); i++){
				int size = arrayString.get(i).length;
				Groupe g = null;
				Sujet s = null;
				Intervenant client = null;
				Intervenant supervis = null;
				Intervenant support = null;
				
				if(size>1)
					g = model.getGroupe(arrayString.get(i)[1].charAt(0));
				if(size>2)
					s = model.getSujet(Integer.parseInt(arrayString.get(i)[2]));
				if(size>3)
					client = model.getInterv(Integer.parseInt(arrayString.get(i)[3]));
				if(size>4)
					supervis = model.getInterv(Integer.parseInt(arrayString.get(i)[4]));
				if(size>5)
					support = model.getInterv(Integer.parseInt(arrayString.get(i)[5]));
				projs.add(new Projet(Integer.parseInt(arrayString.get(i)[0]), s, g, client, supervis, support));
			}
			
			model.setArrayProjets(projs);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void initData(){
		File f = new File("sujets2014_2015.csv");
		if(f.exists())
		try {
			ArrayList<String[]> arrayTemp = LibCsv.CSV_Read("sujets2014_2015.csv");
			model.setArraySujets(LibCsv.translateArraySujet(arrayTemp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f = new File("etudiants2014_2015.csv");
		if(f.exists())
		try {
			ArrayList<String[]> arrayTemp = LibCsv.CSV_Read("etudiants2014_2015.csv");
			ArrayList<ArrayList<? extends Entity>> array = LibCsv.translateArrayEtudiant((arrayTemp));
			@SuppressWarnings("unchecked")
			ArrayList<Etudiant> etus = (ArrayList<Etudiant>)array.get(0);
			@SuppressWarnings("unchecked")
			ArrayList<Groupe> grps = (ArrayList<Groupe>)array.get(1);
			model.setArrayEtudiants(etus);
			model.setArrayGroupe(grps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f = new File("intervenants2014_2015.csv");
		if(f.exists())
		try {
			ArrayList<String[]> arrayTemp = LibCsv.CSV_Read("intervenants2014_2015.csv");
			model.setArrayInterv(LibCsv.translateArrayInterv(arrayTemp));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		f = new File("projets2014_2015.csv");
		if(f.exists())
		ouvrirProjetCsv("projets2014_2015.csv");
  		
	}
	
	private void sauvegarderCsv(){
		try {
			JFileChooser dialogue = new JFileChooser();
			@SuppressWarnings("unused")
			ArrayList<String[]> arrayTemp = LibCsv.CSV_Read(dialogue.getSelectedFile().toString());
			
				LibCsv.CSV_Write(LibCsv.translateArraySujetToString(model.getArraySujets()), "test.csv");
			System.out.println("test");
		} catch (Exception e) {
			System.err.println("MauriceException ");
		}
	}
	
	/**
	 * 
	 * Créée une instance de la classe CreerSujet
	 * 
	 */
	public void creation_sujet() {
		CreerSujet fenetre = new CreerSujet(view,"Création sujet", this);
		fenetre.setLocationRelativeTo(view);
		fenetre.setVisible(true);
		fenetre.pack();
	}
	
	/**
	 * Créée une instance de la classe CreerSujet
	 * @param s Le sujet à modifier
	 * 
	 */
	public void modifier_sujet(Sujet s) {
		CreerSujet fenetre = new CreerSujet(view,"Modification sujet", s, this);
		fenetre.setLocationRelativeTo(view);
		fenetre.setVisible(true);
		fenetre.pack();
	}
	
	/**
	 * 
	 * @param s Sujet à modifier
	 * 			
	 */
	public void creationSujet(Sujet S) {
		S.setId(model.getNextIdSujet());
		model.addSujet(S);
		view.repaint();
	}
	
	public void modifierSujet(Sujet s){
		model.modifSujet(s);
		view.repaint();
	}
	
	public void grpVoeu(Groupe g, Sujet s, int v){
		if(g.getSujet(v) != null)
			g.setVoeu(g.getSujet(v), 0);	
		g.setVoeu(s, v);
			
	}
	
	
	public static void main(String[] sapin){
		Controller c = new Controller();
		c.initData();
	}
	

}
