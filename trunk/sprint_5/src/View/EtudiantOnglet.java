package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;
import Model.Entity;
import Model.Etudiant;
import Model.Groupe;
import Model.Model;
import View.PanelJList.ListType;

public class EtudiantOnglet extends JPanel {
	
	private PanelJList groupeList;
	private JPanel centerPanel;
		private JPanel panelGroupeInfo;
			private PanelJList etudiantsDansGrp;
	private JPanel etudiantsEast;
		private PanelJList listEtudiants;
	private JPanel etudiantInfo;
		private JPanel nameEtu;
			private JPanel namePan;
			private JPanel buttonEtuPan;
				private JButton modEtu;
					private JButton valModEtu, annulModEtu;
				private JButton supprEtu;
	
	private Model model;
	private Controller ctrl;
	
	public EtudiantOnglet(Controller ctrl, Model model) {
		
		this.ctrl = ctrl;
		this.model = model;
		
		this.setLayout(new BorderLayout());
		
		this.initPanelsList();
		
		this.initPanelInfo();
		
	}
	
	private void initPanelsList(){
		
		etudiantsEast = new JPanel(new BorderLayout());
		listEtudiants = new PanelJList(ctrl, model, ListType.ETUDIANT);
		listEtudiants.setAddable(true);
		listEtudiants.setTransferable(true);
		listEtudiants.addTButtonActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listEtudiants.getList().getSelectedValue()!=null && groupeList.getList().getSelectedValue()!=null)
					ctrl.addEtudiantsInGroupe((Etudiant)listEtudiants.getList().getSelectedValue(), (Groupe)groupeList.getList().getSelectedValue());
				
			}
		});
		listEtudiants.setLabel("Liste des étudiants");
		etudiantsEast.add(listEtudiants, BorderLayout.CENTER);
		etudiantsEast.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(2, 3, 2, 4)));
		
		centerPanel = new JPanel(new BorderLayout());
		etudiantsDansGrp = new PanelJList(ctrl, model, ListType.GROUPE, 0);
		etudiantsDansGrp.setLabel("Etudiant dans le groupe");
		etudiantsDansGrp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.gray), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
		centerPanel.add(etudiantsDansGrp, BorderLayout.NORTH);
		
		groupeList = new PanelJList(ctrl, model, ListType.GROUPE);
		groupeList.setLabel("Liste des Groupes");
		groupeList.setAddable(true);
		groupeList.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 3, 2, 0), BorderFactory.createEtchedBorder()));
		groupeList.getList().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(groupeList.getList().getSelectedValue()!=null)
					etudiantsDansGrp.setIdFiltred(((Entity)groupeList.getList().getSelectedValue()).getId());
				etudiantsDansGrp.repaint();
			}
		});
		
		this.add(groupeList, BorderLayout.WEST);
		this.add(etudiantsEast, BorderLayout.EAST);
		this.add(centerPanel, BorderLayout.CENTER);
	}
	
	private void initPanelInfo(){
		nameEtu = new JPanel(new BorderLayout());
		
		buttonEtuPan = new JPanel(new FlowLayout());
		modEtu = new JButton("modifier");
		modEtu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listEtudiants.getList().getSelectedValue()!=null)
					ctrl.modifier_etudiant((Etudiant)listEtudiants.getList().getSelectedValue());
			}
		});
		supprEtu = new JButton("supprimer");
		supprEtu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listEtudiants.getList().getSelectedValue()!=null)
					ctrl.supprimerEtudiant((Etudiant)listEtudiants.getList().getSelectedValue());
			}
		});
		buttonEtuPan.add(modEtu);
		buttonEtuPan.add(supprEtu);
		
		nameEtu.add(buttonEtuPan, BorderLayout.SOUTH);
		
		etudiantsEast.add(nameEtu, BorderLayout.SOUTH);
		
		
	}
	
	private void actualiseInfoEtu(){
	}

}
