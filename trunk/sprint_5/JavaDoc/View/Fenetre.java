package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

import Controller.Controller;
import Csv.LibCsv;
import Model.Model;
import Model.Sujet;


/**
 * Cette classe est un JFrame, c'est la page principale de notre application.
 * @author Thomas
 *
 */
public class Fenetre extends JFrame{
	
	//JPanel sujets = new JPanel(new GridLayout(1,2));
//	JPanel sujets_west = new JPanel(new GridBagLayout());
	//GridBagConstraints c = new GridBagConstraints();
	//JPanel sujets_east = new JPanel(new BorderLayout());
	private JPanel etudiants = new JPanel(new BorderLayout());
	private JPanel etudiants_west = new JPanel(new BorderLayout());
	private JPanel etudiants_east = new JPanel(new BorderLayout());
	private JPanel intervenants = new JPanel(new BorderLayout());
	private JPanel intervenants_west = new JPanel(new BorderLayout());
	private JPanel intervenants_east = new JPanel(new BorderLayout());
	private SujetOnglet sujetO;
	private EtudiantOnglet etudiantO;
	private IntervenantOnglet intervenantO;
	private Controller controller;
	private Model model;
	
	public Fenetre(String titre, Controller ctrl, Model model) {
		
		super(titre);
		
		this.controller = ctrl;
		this.model = model;
		this.sujetO = new SujetOnglet(controller, model);
		this.etudiantO = new EtudiantOnglet(controller, model);
		this.intervenantO = new IntervenantOnglet(controller, model);
		//	c.fill = GridBagConstraints.HORIZONTAL;
	//	c.ipady = 0;  
		
		//c.insets = new Insets(10,260,10,10);  

	
		
		etudiants.add(etudiants_west,BorderLayout.WEST);
		etudiants.add(etudiants_east,BorderLayout.EAST);
		etudiants_west.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
		
		intervenants.add(intervenants_west,BorderLayout.WEST);
		intervenants.add(intervenants_east,BorderLayout.EAST);
		intervenants_west.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		this.setJMenuBar(barreDeMenus());
		this.add(onglets());
		
		}
	
	public JTabbedPane onglets() {
		
		JTabbedPane panel = new JTabbedPane();
		//sujets.setPreferredSize(new Dimension(10, this.getHeight()));
		panel.addTab("Sujets", sujetO);
		panel.addTab("Etudiants", etudiantO);
		panel.addTab("Intervenants", intervenantO);
		
		return panel;
			
	}
	
	public Sujet getSelectedSujet(){
		return sujetO.getSelectedSujet();
	}
	
	public void sujetAVisualiser(Sujet s){
		sujetO.visualiserSujet(s);
	}
	
	public JMenuBar barreDeMenus() {
        JMenuBar menu = new JMenuBar();
        JMenu fichier = new JMenu("Fichier");
        JMenuItem ouvrir = new JMenuItem("Ouvrir Csv");
        fichier.add(ouvrir);
		JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
        fichier.add(sauvegarder);
        JMenuItem about = new JMenuItem("A propos");
        fichier.add(about);
        JMenuItem quit = new JMenuItem("Quitter");
        fichier.add(quit);

        ouvrir.setActionCommand("ouvrirCsv");
        ouvrir.addActionListener(controller);
		
		sauvegarder.setActionCommand("sauvegarder");
        sauvegarder.addActionListener(controller);
        
        about.setActionCommand("about");
        about.addActionListener(controller);
        
        quit.setActionCommand("quit");
        quit.addActionListener(controller);
        
        menu.add(fichier);

        return menu;
    }
  
    public void quitter() {
    	int confirmation;
    	confirmation = JOptionPane.showConfirmDialog(
    		this, "Voulez-vous réellement quitter cette application ?", "Quitter ?",
    		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if ( confirmation==JOptionPane.YES_OPTION ) {
    		System.exit(0);
    	}
    }
    
    public void about() {
    	
    	JOptionPane.showMessageDialog(this, "Application de gestion de projets tutorés (nom : OPTI)\n\nUniversité Toulouse 2 - IUT de Blagnac - DUT Info S3/Module MPA \n\nListe des membres de l'équipe (Groupe 2B2) : \n"
    			+ " - Jordan BROCARIO \n"
    			+ " - Léo CALVIS \n"
    			+ " - François-Marie D'ABOVILLE \n"
    			+ " - Thomas BONFILL \n"
    			+ " - Alexandre ERB \n"
    			+ " - Tim DAZAYOUS\n\n"
    			+ "Version : SPRINT 2");    	
 
    	
    }
    
	
	

}
