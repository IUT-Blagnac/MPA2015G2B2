package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;
import Model.Entity;
import Model.Etudiant;
import Model.Groupe;
import Model.Model;
import View.PanelJList.ListType;


@SuppressWarnings("serial")
public class EtudiantOnglet extends JPanel {
	
	private EtudiantOnglet itself = this;
	
	private JPanel centerPanel;
		private PanelJList groupeList;
		private JPanel panelGroupeInfo;
			private JPanel panelVoeux;
				private JPanel rightPan;
				private JPanel listVoeux;
					private JScrollPane scroll;
					private GridLayout GridListV;
						private JLabel[] tSujets;
						private JTextField[] voeux;
			private PanelJList etudiantsDansGrp;
	private JPanel etudiantsEast;
		private PanelJList listEtudiants;
	@SuppressWarnings("unused")
	private JPanel etudiantInfo;
		private JPanel nameEtu;
			@SuppressWarnings("unused")
			private JPanel namePan;
			private JPanel buttonEtuPan;
				private JButton modEtu;
					@SuppressWarnings("unused")
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
		
		this.initPanelVoeux();
		
	}
	
	private void initPanelsList(){
		
		etudiantsEast = new JPanel(new BorderLayout());
		listEtudiants = new PanelJList(ctrl, model, ListType.ETUDIANT);
		panelGroupeInfo = new JPanel(new BorderLayout());
		JPanel panelGroupList = new JPanel(new BorderLayout());
		listEtudiants.setAddable(true);
		listEtudiants.setTransferable(true);
		listEtudiants.addTButtonActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listEtudiants.getList().getSelectedValue()!=null){
					if(groupeList.getList().getSelectedValue()!=null){
						ctrl.addEtudiantsInGroupe((Etudiant)listEtudiants.getList().getSelectedValue(), (Groupe)groupeList.getList().getSelectedValue());
						return;
					}
				}else
					if(groupeList.getList().getSelectedValue()==null){
						JOptionPane.showConfirmDialog(itself, "Selectionner un etudiant et un groupe", "Erreur Ajout", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}else
						JOptionPane.showConfirmDialog(itself, "Selectionner un etudiant", "Erreur Ajout", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				if(groupeList.getList().getSelectedValue()==null)
					JOptionPane.showConfirmDialog(itself, "Selectionner un groupe", "Erreur Ajout", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		});
		listEtudiants.setLabel("Liste des étudiants");
		listEtudiants.setResearch(true);
		listEtudiants.setPreferredSize(new Dimension(150, 500));
		etudiantsEast.add(listEtudiants, BorderLayout.CENTER);
		etudiantsEast.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEtchedBorder()), BorderFactory.createEmptyBorder(2, 3, 2, 4)));
		
		centerPanel = new JPanel(new BorderLayout());
		etudiantsDansGrp = new PanelJList(ctrl, model, ListType.GROUPE, 0);
		etudiantsDansGrp.setLabel("Etudiant dans le groupe");
		JPanel panelEtuInG = new JPanel(new BorderLayout());
		panelEtuInG.add(etudiantsDansGrp, BorderLayout.CENTER);
		panelGroupeInfo.add(panelEtuInG, BorderLayout.SOUTH);
		centerPanel.add(panelGroupeInfo, BorderLayout.CENTER);
		
		groupeList = new PanelJList(ctrl, model, ListType.GROUPE);
		groupeList.setLabel("Liste des Groupes");
		groupeList.setAddable(true);
		centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2, 3, 2, 3), BorderFactory.createEtchedBorder()));
		groupeList.getList().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(groupeList.getList().getSelectedValue()!=null)
					etudiantsDansGrp.setIdFiltred(((Entity)groupeList.getList().getSelectedValue()).getId());
				etudiantsDansGrp.repaint();
			}
		});
		groupeList.setPreferredSize(new Dimension(108, 0));
		panelGroupList.add(groupeList, BorderLayout.CENTER);
		JButton supprGrp = new JButton("Supprimer");
			JPanel panSupprG = new JPanel(new FlowLayout());
			panSupprG.add(supprGrp);
		panelGroupList.add(panSupprG, BorderLayout.SOUTH);
		supprGrp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(groupeList.getList().getSelectedValue() != null)
					ctrl.delGrp((Groupe) groupeList.getList().getSelectedValue());
			}
		});
		JButton supprEtuInGrp = new JButton("Supprimer");
		supprEtuInGrp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(etudiantsDansGrp.getList().getSelectedValue()!=null)
					ctrl.retireEtu((Etudiant)etudiantsDansGrp.getList().getSelectedValue());
			}
		});
		JPanel panSupprEtuIG = new JPanel(new FlowLayout());
		panSupprEtuIG.add(supprEtuInGrp);
		panelEtuInG.add(panSupprEtuIG, BorderLayout.SOUTH);
		
		centerPanel.add(panelGroupList, BorderLayout.WEST);
		//this.add(groupeList, BorderLayout.WEST);
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
	
	private void initPanelVoeux(){
		panelVoeux = new JPanel(new BorderLayout());
			rightPan = new JPanel(new BorderLayout());
			listVoeux = new JPanel();
			rightPan.add(listVoeux, BorderLayout.NORTH);
				GridListV = new GridLayout();
				scroll = new JScrollPane(rightPan);
			listVoeux.setLayout(GridListV);
			rightPan.setBorder(BorderFactory.createLoweredBevelBorder());
			groupeList.getList().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					refreshListVoeux();
				}
			});
		panelVoeux.add(scroll, BorderLayout.CENTER);
		panelVoeux.add(new JLabel("Voeux du groupe", JLabel.HORIZONTAL), BorderLayout.NORTH);
		panelVoeux.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(1, 1, 1, 1)));
		panelGroupeInfo.add(panelVoeux, BorderLayout.CENTER);
	}
	
	private void refreshListVoeux(){
		GridListV.setColumns(1);
		GridListV.setRows(model.getArraySujets().size());
		listVoeux.removeAll();
		tSujets = new JLabel[model.getArraySujets().size()];
		voeux = new JTextField[model.getArraySujets().size()];
		for(int i=0; i<model.getArraySujets().size(); i++){
			JPanel pan = new JPanel(new BorderLayout());
			JLabel label = new JLabel("["+model.getArraySujets().get(i).getAcronyme()+"] " + model.getArraySujets().get(i).getTitre());
			JPanel labPan = new JPanel(new BorderLayout());
			labPan.add(label, BorderLayout.WEST);
			pan.add(labPan, BorderLayout.CENTER);
			tSujets[i] = label;
			JTextField voeu = new JTextField(2);
			voeux[i] = voeu;
			voeu.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
				      char c = e.getKeyChar();
				      if (!((c >= '0') && (c <= '9') ||
				         (c == KeyEvent.VK_BACK_SPACE) ||
				         (c == KeyEvent.VK_DELETE))) {
				        getToolkit().beep();
				        e.consume();
				      }
				      if (c == KeyEvent.VK_TAB){
				    	  refreshListVoeux();
				      }
				    }
			});
			voeu.setName(model.getArraySujets().get(i).getId()+"");
			voeu.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e){
					JTextField f = (JTextField)e.getSource();
					if(f.getText().matches("[0-9]+") && !f.getText().equals("")){
						Groupe grp = (Groupe) groupeList.getList().getSelectedValue();
						ctrl.grpVoeu(grp, model.getSujet(Integer.parseInt(f.getName())), Integer.parseInt(f.getText()));
					}else
						if(!f.getText().equals(""))
							f.setText("");
					actVoeux();
				}
				@Override
				public void focusGained(FocusEvent e) {
					((JTextField)e.getSource()).setText("");
				}
			});
			voeu.addCaretListener(new CaretListener() {
				@Override
				public void caretUpdate(CaretEvent e) {
				}
			});
			if(groupeList.getList().getSelectedValue()!=null)
				voeu.setText(((Groupe) groupeList.getList().getSelectedValue()).getVoeux().get(model.getArraySujets().get(i))+"");
			if(voeu.getText().equals("0"))
				voeu.setText("?");
			pan.add(voeu, BorderLayout.WEST);
			
			listVoeux.add(pan);
		}
	}
	
	private void actVoeux(){
		for(int i=0; i<voeux.length; i++){
			String rng = ((Groupe)groupeList.getList().getSelectedValue()).getVoeu(model.getSujet(Integer.parseInt(voeux[i].getName())));
			voeux[i].setText(rng);
			if(rng == null || rng.equals("0"))
				voeux[i].setText("?");
		}
	}

}
