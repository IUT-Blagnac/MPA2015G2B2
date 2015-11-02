package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.Controller;
import Model.Model;
import Model.Sujet;

/**
 * Cette classe est une JDialog qui affiche une fenêtre permettant à l'utilisateur d'entrer les différentes informations nécessaires à la création d'un Sujet
 * @author Thomas
 *
 */
public class CreerSujet extends JDialog {
	
	private JTextField acronyme,titre, outils;
	private JTextArea contexte, besoins,correspondant;
	private int id;
	private Controller ctrl;
	private Sujet newSujet;
	private Model modele;
	private boolean mod;
	
	public CreerSujet(JFrame mere, String titre, Controller c) {
		  super(mere, titre, true);
		  this.ctrl = c;
		  this.id = 0;
		  this.newSujet = new Sujet();
	      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	      this.setContentPane(panneauContenu());
	      this.setMinimumSize(new Dimension(400, 300));
	      this.pack();
	      this.mod = false;
	}
	
	public CreerSujet(JFrame mere, String titre, Sujet s, Controller c) {
		this(mere, titre, c);
		this.id = s.getId();
		this.acronyme.setText(s.getAcronyme());
		this.titre.setText(s.getTitre());
		this.outils.setText(s.getOutils());
		this.contexte.setText(s.getContexte());
		this.besoins.setText(s.getBesoins());
		this.correspondant.setText(s.getCorrespondant());
		this.mod = true;
		
	}



	
	/**
	 * @return
	 */
	public JPanel panneauContenu() {
		
		JButton valider = new JButton("Valider");
		JButton annuler = new JButton("Annuler");
		 
		valider.setActionCommand("validerSujet");
	 	valider.addActionListener(ctrl);
	        
	 	annuler.setActionCommand("annulerSujet");
	 	annuler.addActionListener(ctrl);
	 	
		valider.addActionListener(new EcouteurButton(valider));
		valider.setActionCommand("validerSujet");
		annuler.addActionListener(new EcouteurButton(annuler));
		annuler.setActionCommand("annulerSujet");
		JPanel big = new JPanel(new BorderLayout());
			JPanel topPanel = new JPanel(new BorderLayout());
				topPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 1, 5));
			big.add(topPanel, BorderLayout.NORTH);
				JPanel acronymePanel = new JPanel(new BorderLayout());
				topPanel.add(acronymePanel, BorderLayout.WEST);
				JPanel titrePanel = new JPanel(new BorderLayout());
				topPanel.add(titrePanel, BorderLayout.CENTER);
			JPanel midPanel = new JPanel(new BorderLayout());
				midPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
				JTabbedPane onglet = new JTabbedPane();
					JPanel contextePanel = new JPanel(new BorderLayout());
					onglet.addTab("Contexte", contextePanel);
					JPanel besoinsPanel = new JPanel(new BorderLayout());
					onglet.addTab("Projet", besoinsPanel);
					JPanel correspondantPanel = new JPanel(new BorderLayout());
					onglet.addTab("Correspondant", correspondantPanel);
				midPanel.add(onglet, BorderLayout.CENTER);
				JPanel outilsPanel = new JPanel(new BorderLayout());
					outilsPanel.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
				midPanel.add(outilsPanel, BorderLayout.SOUTH);
			big.add(midPanel, BorderLayout.CENTER);
			JPanel boutonsPanel = new JPanel(new FlowLayout());
			big.add(boutonsPanel, BorderLayout.SOUTH);
		
		JLabel acronymeL = new JLabel("Acronyme");
		JLabel titreL = new JLabel("Titre");
		JLabel outilsL = new JLabel("Outils");
		JLabel contexteL = new JLabel("Contexte du projet");
		JLabel besoinsL = new JLabel("Besoins du client");
		JLabel correspondantL = new JLabel("Correspondant");
		acronyme = new JTextField(8);
		acronyme.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		titre = new JTextField(10);
		titre.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		outils = new JTextField(10);
		outils.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		contexte = new JTextArea(3,10);
		contexte.setLineWrap(true);
		contexte.setWrapStyleWord(true);
		contexte.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		besoins = new JTextArea(3,10);
		besoins.setLineWrap(true);
		besoins.setWrapStyleWord(true);
		besoins.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		correspondant = new JTextArea(3,10);
		correspondant.setLineWrap(true);
		correspondant.setWrapStyleWord(true);
		correspondant.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		acronymePanel.add(acronymeL, BorderLayout.NORTH);
		acronymePanel.add(acronyme, BorderLayout.CENTER);
		titrePanel.add(titreL, BorderLayout.NORTH);
		titrePanel.add(titre, BorderLayout.CENTER);
		outilsPanel.add(outilsL, BorderLayout.WEST);
		outilsPanel.add(outils, BorderLayout.CENTER);
		contextePanel.add(contexteL, BorderLayout.NORTH);
		contextePanel.add(contexte, BorderLayout.CENTER);
		besoinsPanel.add(besoinsL, BorderLayout.NORTH);
		besoinsPanel.add(besoins, BorderLayout.CENTER);
		correspondantPanel.add(correspondantL, BorderLayout.NORTH);
		correspondantPanel.add(correspondant, BorderLayout.CENTER);
		boutonsPanel.add(annuler);
		boutonsPanel.add(valider);
		
		
		return big;
		
		
	}
	
	public void actionBoutonAnnuler() {
		dispose();
		
	}
	
	public void actionBoutonOk() {
		
		if(mod){
			newSujet = new Sujet(id, acronyme.getText(), titre.getText(), contexte.getText(), besoins.getText(), outils.getText(), correspondant.getText());
			ctrl.modifierSujet(newSujet);
		}else{
			newSujet = new Sujet(acronyme.getText(), titre.getText(), contexte.getText(), besoins.getText(), outils.getText(), correspondant.getText());
			ctrl.creationSujet(newSujet);
		}
		dispose();
	}
	
	
	public class EcouteurButton implements ActionListener {
		
		private JButton button;
		
		public EcouteurButton(JButton b) {
			this.button = b;
		}
		
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (button.getText().equals("Annuler")) {
			actionBoutonAnnuler();
	}
		if (button.getText().equals("Valider")) {
			actionBoutonOk();
	}
	}
		

	}
}