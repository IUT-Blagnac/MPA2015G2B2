/**
 * 
 */
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Controller;
import Model.Etudiant;
import Model.Model;
import Model.Sujet;

/**
 * @author Sorény
 *
 */
public class PanelJList extends JPanel {
	
	public enum ListType{
		SUJET, ETUDIANT, GROUPE, INTERVENANT, PROJET
	}
	
	private PanelJList itself = this;
	
	private JTabbedPane onglet;
	private JList<Object> list, listNoAff;
		private boolean add;
	private ListType listT;
	private JPanel topPanel;
		private JTextField textFiltre;
		private JLabel label;
	private JPanel buttonPanel;
		private JButton addBouton, transfButton;
	private boolean isFiltred;
		private int idFiltre;
	
	private Controller ctrl;
	private Model model;

	/**
	 * Constructeur parametre
	 * 
	 * @param ctrl Le controller
	 * @param model Le modele
	 * @param lt Le type de donnée que contient la liste (ListType)
	 */
	public PanelJList(Controller ctrl, Model model, ListType lt) {
		super();
		
		this.listT = lt;
		
		this.ctrl = ctrl;
		this.model = model;
		this.isFiltred = false;
		this.add = false;
		this.initList();
		this.initPanel();
		
		this.repaint();
	}
	
	public PanelJList(Controller ctrl, Model model, ListType lt, int id){
		super();
		
		this.listT = lt;
		
		this.ctrl = ctrl;
		this.model = model;
		this.isFiltred = true;
		this.add = false;
		this.initList();
		this.initPanel();
		
		
		this.repaint();
		this.idFiltre = id;
	}
	
	/**
	 * Initialise la liste
	 */
	private void initList(){
		
		
		if(isFiltred){
			switch (listT){
			case SUJET:
				this.list = new JList<>(model.getArraySujets().toArray());
				break;
			case ETUDIANT:
				this.list = new JList<>(model.getArrayEtudiant().toArray());
				break;
			case GROUPE:
				if(idFiltre!=0)
					this.list = new JList<>(model.getArrayGroupe().get(idFiltre).getEtudiants().toArray());
				else
					this.list = new JList<>(new ArrayList<Object>().toArray());
				break;
			case INTERVENANT:
				this.list = new JList<>(model.getArrayInterv().toArray());
				break;
			}
			return;
		}
		switch (listT){
		case SUJET:
			this.list = new JList<>(model.getArraySujets().toArray());
			break;
		case ETUDIANT:
			ArrayList<Etudiant> etuNoAf = new ArrayList<>();
			if(textFiltre != null && !textFiltre.getText().equals("recherche...")){
				
				ArrayList<Etudiant> allEtus = model.getArrayEtudiant();
				ArrayList<Etudiant> etuFil = new ArrayList<>();
				
				for(int i=0; i<allEtus.size(); i++){
					String name = allEtus.get(i).getNom().toLowerCase() + allEtus.get(i).getPrenom().toLowerCase();
					String nameRevers = allEtus.get(i).getPrenom().toLowerCase() + allEtus.get(i).getNom().toLowerCase();
					if(name.contains(textFiltre.getText().trim().toLowerCase()) || nameRevers.contains(textFiltre.getText().trim().toLowerCase()))
						etuFil.add(allEtus.get(i));
					if(allEtus.get(i).getNumGroupe()==0)
						etuNoAf.add(allEtus.get(i));
				}
				
				this.list = new JList<>(etuFil.toArray());
				
			}else{
				this.list = new JList<>(model.getArrayEtudiant().toArray());
				for(int i=0; i<model.getArrayEtudiant().size(); i++){
					if(model.getArrayEtudiant().get(i).getNumGroupe()==-1)
						etuNoAf.add(model.getArrayEtudiant().get(i));
				}
			}
			this.listNoAff = new JList<>(etuNoAf.toArray());
			break;
		case GROUPE:
			this.list = new JList<>(model.getArrayGroupe().toArray());
			break;
		case INTERVENANT:
			this.list = new JList<>(model.getArrayInterv().toArray());
			break;
		case PROJET:
			this.list = new JList<>(model.getArrayProjets().toArray());
			break;
		}

		this.setSize(getPreferredSize());
		list.setVisible(true);
	}
	
	/**
	 * Initialise le panneau
	 */
	private void initPanel(){
		
		this.setLayout(new BorderLayout());
		JScrollPane scrollList = new JScrollPane(list);
		if(listNoAff!=null){
			JScrollPane scrollListN = new JScrollPane(listNoAff);
			listNoAff.setBorder(BorderFactory.createLoweredBevelBorder());
			onglet = new JTabbedPane();
			onglet.add("Tous", scrollList);
			onglet.add("Non-affecté", scrollListN);
			this.add(onglet, BorderLayout.CENTER);
		}else
			this.add(scrollList, BorderLayout.CENTER);
		list.setBorder(BorderFactory.createLoweredBevelBorder());
		buttonPanel = new JPanel(new BorderLayout());
		topPanel = new JPanel(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void setAddable(boolean addable){
		if(addable){
			JPanel addBPanel = new JPanel(new FlowLayout());
			addBouton = new JButton("Creer");
			addBPanel.add(addBouton);
			addBouton.setActionCommand("add"+listT.name());
			addBouton.addActionListener(ctrl);
			addBouton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					add = true;
				}
			});
			if(listT == ListType.ETUDIANT)
				buttonPanel.add(addBPanel, BorderLayout.EAST);
			else
				buttonPanel.add(addBPanel, BorderLayout.CENTER);
		}else
			if(addBouton != null)
				buttonPanel.remove(addBouton.getParent());
	}
	
	public void setTransferable(boolean enabled){
		if(enabled){
			JPanel tBPanel = new JPanel(new FlowLayout());
			transfButton = new JButton("<< Ajouter");
			tBPanel.add(transfButton);
			buttonPanel.add(tBPanel, BorderLayout.WEST);
		}else
			if(transfButton != null)
				buttonPanel.remove(transfButton);
		repaint();
	}
	
	public void setResearch(boolean enabled){
		if(enabled){
			textFiltre = new JTextField();
			textFiltre.addCaretListener(new CaretListener() {
				
				@Override
				public void caretUpdate(CaretEvent e) {
					itself.repaint();	
				}
			});
			textFiltre.setForeground(Color.GRAY);
			textFiltre.setText("recherche...");
			textFiltre.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					if(textFiltre.getText()==null || textFiltre.getText().equals("")){
						textFiltre.setForeground(Color.GRAY);
						textFiltre.setText("recherche...");
					}
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					if(textFiltre.getForeground()==Color.GRAY){
						textFiltre.setText("");
						textFiltre.setForeground(Color.BLACK);
					}
				}
			});
			topPanel.add(textFiltre, BorderLayout.SOUTH);
		}else
			if(textFiltre != null){
				topPanel.remove(textFiltre);
				textFiltre = null;
			}
				
	}
	
	public void addTButtonActionListener(ActionListener al){
		transfButton.addActionListener(al);
	}
	
	public void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		list.setEnabled(enabled);
		if(addBouton!=null){
			addBouton.setEnabled(enabled);
		}
	}
	
	public void paint(Graphics g){
		if(this.list!=null){
			int index = list.getSelectedIndex();
			super.paint(g);
			if(isFiltred){
				switch (listT){
				case SUJET:
					this.list.setListData(model.getArraySujets().toArray());
					break;
				case ETUDIANT:
					this.list.setListData(model.getArrayEtudiant().toArray());
					break;
				case GROUPE:
					if(idFiltre!=0 && model.getArrayGroupe().size()>0){
						this.list.setListData(model.getGroupe(idFiltre).getEtudiants().toArray());
					}
					else
						this.list.setListData(new ArrayList<Object>().toArray());
					break;
				case INTERVENANT:
					this.list.setListData(model.getArrayInterv().toArray());
					break;
				}
				revalidate();
				super.paint(g);
			}
			if(!isFiltred){
				switch (listT){
				case SUJET:
					if(textFiltre != null && textFiltre.getForeground()!=Color.GRAY){
						
						ArrayList<Sujet> allsujs = model.getArraySujets();
						ArrayList<Sujet> sujFil = new ArrayList<>();
						
						for(int i=0; i<allsujs.size(); i++){
							String titre = allsujs.get(i).getTitre().toLowerCase();
							String acro = allsujs.get(i).getAcronyme().toLowerCase();
							if(titre.contains(textFiltre.getText().trim().toLowerCase()) || acro.contains(textFiltre.getText().trim().toLowerCase())){
								sujFil.add(allsujs.get(i));
							}
								
						}
						
						this.list.setListData(sujFil.toArray());
						
					}else
						this.list.setListData(model.getArraySujets().toArray());
					break;
				case ETUDIANT:
					ArrayList<Etudiant> etuNoAf = new ArrayList<>();
					
					if(textFiltre != null && textFiltre.getForeground()!=Color.GRAY){
						
						ArrayList<Etudiant> allEtus = model.getArrayEtudiant();
						ArrayList<Etudiant> etuFil = new ArrayList<>();
						
						for(int i=0; i<allEtus.size(); i++){
							String name = allEtus.get(i).getNom().toLowerCase() +  allEtus.get(i).getPrenom().toLowerCase();
							String nameRevers = allEtus.get(i).getPrenom().toLowerCase() + allEtus.get(i).getNom().toLowerCase();
							if(name.contains(textFiltre.getText().trim().toLowerCase().replaceAll("\\s", "")) || nameRevers.contains(textFiltre.getText().trim().toLowerCase().replaceAll("\\s", ""))){
								etuFil.add(allEtus.get(i));
								if(allEtus.get(i).getNumGroupe()==-1)
									etuNoAf.add(allEtus.get(i));
							}
								
						}
						
						this.list.setListData(etuFil.toArray());
					}else{
						this.list.setListData(model.getArrayEtudiant().toArray());
						for(int i=0; i<model.getArrayEtudiant().size(); i++){
							if(model.getArrayEtudiant().get(i).getNumGroupe()==-1)
								etuNoAf.add(model.getArrayEtudiant().get(i));
						}
					}
					this.listNoAff.setListData(etuNoAf.toArray());
					break;
				case GROUPE:
					this.list.setListData(model.getArrayGroupe().toArray());
					if(add){
						list.setSelectedValue(list.getModel().getElementAt(list.getModel().getSize()-1), true);
						add = false;
					}
					break;
				case INTERVENANT:
					this.list.setListData(model.getArrayInterv().toArray());
					break;
				case PROJET:
					this.list.setListData(model.getArrayProjets().toArray());
					break;
				}
				if(index == list.getModel().getSize())
					index--;
				revalidate();
				super.paint(g);
			}
			list.setSelectedIndex(index);
		}
		else
			super.paint(g);
	}
	
	/**
	 * getter de la liste
	 * 
	 * @return la liste du panneau
	 */
	public JList<Object> getList(){
		if(onglet != null){
			if(onglet.getSelectedIndex()==0)
				return list;
			else
				return listNoAff;
		}else
		return list;
	}

	public String getLabel() {
		return label.getText();
	}
	
	public void setLabel(String label) {
		this.label = new JLabel(label);
		topPanel.add(this.label, BorderLayout.NORTH);
	}
	
	public void removeLabel(){
		this.remove(this.label);
	}
	
	public void setIdFiltred(int id){
		this.idFiltre = id;
	}

}
