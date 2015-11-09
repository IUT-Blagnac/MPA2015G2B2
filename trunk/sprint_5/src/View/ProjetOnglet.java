/**
 * 
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;
import Model.Etudiant;
import Model.Model;
import Model.Projet;
import Model.Sujet;
import View.PanelJList.ListType;

/**
 * @author Sorény
 *
 */
public class ProjetOnglet extends JPanel {
	
	private PanelJList listeProjs;
	private JPanel panelInfo;
		private JPanel panelInterv;
			private JLabel client, supp, supervis;
		private JPanel panelGrpEtus;
			private JList<Etudiant> listEtudiant;
		private JPanel panelSujet;
			private JTextField acronyme,titre, outils;
			private JTextArea contexte, besoins,correspondant;

	private Model model;
	private Controller ctrl;
	
	public ProjetOnglet(Controller ctrl, Model model) {
		this.model = model;
		this.ctrl = ctrl;
		
		initPanel();
		
	}
	
	private void initPanel(){
		this.setLayout(new BorderLayout());
		
		listeProjs = new PanelJList(ctrl, model, ListType.PROJET);
			listeProjs.setAddable(true);
			listeProjs.getList().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					select();
				}
			});
		
		this.add(listeProjs, BorderLayout.WEST);
		
		panelInfo = new JPanel(new BorderLayout());
			JPanel panelHumans = new JPanel(new GridLayout(1, 2));
				panelInterv = new JPanel(new BorderLayout());
					JPanel panelEtiquI = new JPanel(new GridLayout(3, 1));
						panelEtiquI.add(new JLabel("Client :"));
						panelEtiquI.add(new JLabel("Superviseur :"));
						panelEtiquI.add(new JLabel("Support :"));
					JPanel panelIntNom = new JPanel(new GridLayout(3, 1));
						client = new JLabel("  -");
						supervis = new JLabel("  -");
						supp = new JLabel("  -");
						panelIntNom.add(client);
						panelIntNom.add(supervis);
						panelIntNom.add(supp);
					panelInterv.add(panelEtiquI, BorderLayout.WEST);
					panelInterv.add(panelIntNom, BorderLayout.CENTER);
					panelInterv.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Intervenants"));
				panelGrpEtus = new JPanel(new BorderLayout());
					listEtudiant = new JList<>();
					panelGrpEtus.add(listEtudiant);
					panelGrpEtus.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Groupe"));
				panelHumans.add(panelInterv);
				panelHumans.add(panelGrpEtus);
			panelSujet = initVisualiserSujet();
				panelSujet.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Sujet"));
			panelInfo.add(panelHumans, BorderLayout.NORTH);
			panelInfo.add(panelSujet, BorderLayout.CENTER);
			
				
				
		this.add(panelInfo, BorderLayout.CENTER);
		
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

	private void select(){
		Projet p = (Projet) listeProjs.getList().getSelectedValue();
		
		if(p == null){
			visualiserSujet(null);
			listEtudiant.setListData(new ArrayList<Etudiant>().toArray(new Etudiant[0]));
			client.setText("  -");
			supervis.setText("  -");
			supp.setText("  -");
			return;
		}
		visualiserSujet(p.getSujet());;
		listEtudiant.setListData(p.getGrp().getEtudiants().toArray(new Etudiant[p.getGrp().getEtudiants().size()]));
		client.setText("  " + p.getClient());
		supervis.setText("  " + p.getSuperviseur());
		supp.setText("  " + p.getSupport());
	}
	
	private void visualiserSujet(Sujet s){
		
		if(listeProjs.getList().getModel().getSize() == 0 || s == null){
			acronyme.setText("");
			titre.setText("");
			contexte.setText("");
			besoins.setText("");
			outils.setText("");
			correspondant.setText("");
			return;
		}
		
		acronyme.setText(s.getAcronyme());
		titre.setText(s.getTitre());
		contexte.setText(s.getContexte());
		besoins.setText(s.getBesoins());
		outils.setText(s.getOutils());
		correspondant.setText(s.getCorrespondant());
		
	}
		
}
