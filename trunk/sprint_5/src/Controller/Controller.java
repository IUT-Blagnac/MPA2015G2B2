package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Csv.LibCsv;
import Model.Etudiant;
import Model.Groupe;
import Model.Intervenant;
import Model.Model;
import Model.Sujet;
import View.CreerEtu;
import View.CreerInterv;
import View.CreerSujet;
import View.Fenetre;

public class Controller implements ActionListener, MouseListener {
	
	private Model model;
	private Fenetre view;
	
	private JFileChooser dialogue = new JFileChooser();
	private ArrayList<String[]> arrayTemp = new ArrayList<String[]>();
	
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
		if(!g.getEtudiants().contains(e))
			g.addEtudiant(e);
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
	
	/**
	 * 
	 * Ouvre une fenêtre pour choisir un fichier csv à charger dans l'application
	 * 
	 */
	private void ouvrirCsv(){
		//JFileChooser dialogue = new JFileChooser();
        int  returnVal = dialogue.showOpenDialog(view);
         // ArrayList<String[]> arrayTemp = new ArrayList<String[]>();
          try {
          	if(returnVal == JFileChooser.APPROVE_OPTION) {
          		arrayTemp = LibCsv.CSV_Read(dialogue.getSelectedFile().toString());
          		model.setArraySujets(LibCsv.translateArray(arrayTemp));
          		System.out.println(model.getArraySujets());
          	}
				 
          }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void sauvegarderCsv(){
		try {
			
				arrayTemp = LibCsv.CSV_Read(dialogue.getSelectedFile().toString());
			
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
	
	public void creer_Etudiant(Etudiant e){
		//CreerEtu fenetre = new creerEtu
	}
	
	
	public static void main(String[] sapin){
		new Controller();
	}
	

}
