/**
 * 
 */
package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import Controller.Controller;
import Model.Model;

/**
 * Cette classe est un JPanel qui contient une JList. Elle nous sert à l'affichage de nos données (Etudiants, Sujets, Intervenants)
 * @author Sorény
 *
 */
public class PanelJList extends JPanel {
	
	public enum ListType{
		SUJET, ETUDIANT, GROUPE, INTERVENANT,
	}
	
	private JList<Object> list;
	private ListType listT;
	private JTextArea TextFiltre;
	private JLabel label;
	private JPanel buttonPanel;
		private JButton addBouton;
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
			this.list = new JList<>(model.getArrayEtudiant().toArray());
			break;
		case GROUPE:
			this.list = new JList<>(model.getArrayGroupe().toArray());
			break;
		case INTERVENANT:
			this.list = new JList<>(model.getArrayInterv().toArray());
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
		this.add(scrollList, BorderLayout.CENTER);
		list.setBorder(BorderFactory.createLoweredBevelBorder());
		
	}
	
	public void setAddable(boolean addable){
		if(addable){
			buttonPanel = new JPanel(new FlowLayout());
			addBouton = new JButton("Ajouter");
			addBouton.setActionCommand("add"+listT.name());
			addBouton.addActionListener(ctrl);
			buttonPanel.add(addBouton);
			this.add(buttonPanel, BorderLayout.SOUTH);
		}else
			if(buttonPanel != null)
				this.remove(buttonPanel);
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
					if(idFiltre!=0)
						this.list.setListData(model.getGroupe(idFiltre).getEtudiants().toArray());
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
					this.list.setListData(model.getArraySujets().toArray());
					break;
				case ETUDIANT:
					this.list.setListData(model.getArrayEtudiant().toArray());
					break;
				case GROUPE:
					this.list.setListData(model.getArrayGroupe().toArray());
					break;
				case INTERVENANT:
					this.list.setListData(model.getArrayInterv().toArray());
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
		return list;
	}

	public String getLabel() {
		return label.getText();
	}

	public void setLabel(String label) {
		this.label = new JLabel(label);
		this.add(this.label, BorderLayout.NORTH);
	}
	
	public void removeLabel(){
		this.remove(this.label);
	}
	
	public void setIdFiltred(int id){
		this.idFiltre = id;
	}

}
