package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;
import Model.Model;
import Model.Sujet;
import View.PanelJList.ListType;

public class SujetOnglet extends JPanel {
	
	private SujetOnglet itself = this;
	
	private boolean init;
	private JPanel sujets_west;
		private PanelJList panelList;
	private JPanel sujets_east;
		private JPanel visSujet;
			private int idSujet, indexMod;
			private JTextField acronyme,titre, outils;
			private JTextArea contexte, besoins,correspondant;
			private JPanel panelButton;
				private JPanel modPanel;
			private JButton supprimerSujet;
			private JButton modSujet;
				private JButton confirmMod;
				private JButton annulMod;
	
	private Controller ctrl;
	private Model model;
	
	
	public SujetOnglet(Controller ctrl, Model model) {
		init = false;
		
		this.ctrl = ctrl;
		this.model  = model;
		
		this.setLayout(new BorderLayout());
		sujets_west = new JPanel(new BorderLayout());
			panelList = new PanelJList(ctrl, model, ListType.SUJET);
				panelList.setLabel("Liste des sujets");
				panelList.setAddable(true);
				panelList.getList().addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						visualiserSujet((Sujet)panelList.getList().getSelectedValue());
					}
				});
			sujets_west.add(panelList, BorderLayout.CENTER);
		
		sujets_east = new JPanel(new BorderLayout());
			visSujet = this.initVisualiserSujet();
				visSujet.setVisible(true);
				visSujet.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		sujets_east.add(visSujet, BorderLayout.CENTER);
		supprimerSujet = new JButton("Supprimer");
		modSujet = new JButton("Activer la modification");
		
		supprimerSujet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itself.ctrl.delSujet((Sujet)panelList.getList().getSelectedValue());
				repaint();
			}
		});
		
		modSujet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				activeModification();
			}
		});
		
		panelButton = new JPanel(new BorderLayout());
		JPanel delPanel = new JPanel(new FlowLayout());
		delPanel.add(supprimerSujet);
		modPanel = new JPanel(new FlowLayout());
		modPanel.add(modSujet);
		
		confirmMod = new JButton("Valider");
		confirmMod.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionModifierSujet();
			}
		});
		annulMod = new JButton("Annuler");
		annulMod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				finModification();
				visualiserSujet((Sujet)panelList.getList().getSelectedValue());
			}
		});
		
		panelButton.add(delPanel, BorderLayout.EAST);
		panelButton.add(modPanel, BorderLayout.CENTER);
		
		sujets_east.add(panelButton, BorderLayout.SOUTH);
		
		modSujet.setEnabled(false);
		supprimerSujet.setEnabled(false);
		
		this.add(sujets_west,BorderLayout.WEST);
		this.add(sujets_east,BorderLayout.CENTER);
		sujets_west.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(2, 3, 2, 4)));
	}
	
	public void initList(){
		this.init = true;
	}
	
	public Sujet getSelectedSujet(){
		return (Sujet) panelList.getList().getSelectedValue();
	}
	
	private JPanel initVisualiserSujet(){
		
		JPanel vSuj = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 1, 5));
		vSuj.add(topPanel, BorderLayout.NORTH);
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
		vSuj.add(midPanel, BorderLayout.CENTER);
	
		JLabel acronymeL = new JLabel("Acronyme");
		JLabel titreL = new JLabel("Titre");
		JLabel outilsL = new JLabel("Outils");
		JLabel contexteL = new JLabel("Contexte du projet");
		JLabel besoinsL = new JLabel("Besoins du client");
		JLabel correspondantL = new JLabel("Correspondant");
		acronyme = new JTextField(8);
		acronyme.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		acronyme.setEditable(false);
		titre = new JTextField(10);
		titre.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		titre.setEditable(false);
		outils = new JTextField(10);
		outils.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		outils.setEditable(false);
		contexte = new JTextArea(3,10);
		contexte.setLineWrap(true);
		contexte.setWrapStyleWord(true);
		contexte.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		contexte.setEditable(false);
		contexte.setBackground(acronyme.getBackground());
		besoins = new JTextArea(3,10);
		besoins.setLineWrap(true);
		besoins.setWrapStyleWord(true);
		besoins.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		besoins.setEditable(false);
		besoins.setBackground(acronyme.getBackground());
		correspondant = new JTextArea(3,10);
		correspondant.setLineWrap(true);
		correspondant.setWrapStyleWord(true);
		correspondant.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		correspondant.setEditable(false);
		correspondant.setBackground(acronyme.getBackground());
		
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
			
		
		return vSuj;
	}
	
	public void visualiserSujet(Sujet s){
		
		if(panelList.getList().getModel().getSize() == 0){
			modSujet.setEnabled(false);
			supprimerSujet.setEnabled(false);
			acronyme.setText("");
			titre.setText("");
			contexte.setText("");
			besoins.setText("");
			outils.setText("");
			correspondant.setText("");
		}
		if(s == null)
			return;
			
		
		acronyme.setText(s.getAcronyme());
		titre.setText(s.getTitre());
		contexte.setText(s.getContexte());
		besoins.setText(s.getBesoins());
		outils.setText(s.getOutils());
		correspondant.setText(s.getCorrespondant());
		visSujet.setVisible(true);
		modSujet.setEnabled(true);
		if(panelList.isEnabled())
			supprimerSujet.setEnabled(true);
		
	}

	public void activeModification(){
		Sujet sMod = (Sujet) panelList.getList().getSelectedValue();
		idSujet = sMod.getId();
		indexMod = panelList.getList().getSelectedIndex();
		
		panelList.setEnabled(false);
		supprimerSujet.setEnabled(false);
		
		this.acronyme.setEditable(true);
		this.titre.setEditable(true);
		this.contexte.setEditable(true);
		this.contexte.setBackground(acronyme.getBackground());
		this.besoins.setEditable(true);
		this.besoins.setBackground(acronyme.getBackground());
		this.correspondant.setEditable(true);
		this.correspondant.setBackground(acronyme.getBackground());
		this.outils.setEditable(true);
		
		modPanel.remove(this.modSujet);
		
		modPanel.add(confirmMod);
		modPanel.add(annulMod);
		repaint();
	}
	
	public void actionModifierSujet(){
		Sujet s = new Sujet(idSujet, acronyme.getText(), titre.getText(), contexte.getText(), besoins.getText(), outils.getText(), correspondant.getText());
		ctrl.modifierSujet(s);
		
		finModification();
	}
	
	public void finModification(){
		
		panelList.setEnabled(true);
		supprimerSujet.setEnabled(true);
		
		this.acronyme.setEditable(false);
		this.titre.setEditable(false);
		this.contexte.setEditable(false);
		this.contexte.setBackground(acronyme.getBackground());
		this.besoins.setEditable(false);
		this.besoins.setBackground(acronyme.getBackground());
		this.correspondant.setEditable(false);
		this.correspondant.setBackground(acronyme.getBackground());
		this.outils.setEditable(false);
		
		modPanel.remove(confirmMod);
		modPanel.remove(annulMod);
		
		modPanel.add(modSujet);
		
		repaint();
	}
	
	
}
