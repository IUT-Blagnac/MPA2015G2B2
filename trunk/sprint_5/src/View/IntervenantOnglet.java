package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Controller.Controller;
import Model.Model;
import View.PanelJList.ListType;

public class IntervenantOnglet extends JPanel {

	JPanel intervenant_west;
		PanelJList listInterv;
	JPanel intervenant_east;
	
	private Controller ctrl;
	private Model model;
	
	public IntervenantOnglet(Controller ctrl, Model model) {
		
		this.ctrl = ctrl;
		this.model = model;
		
			this.setLayout(new BorderLayout());
			intervenant_west = new JPanel(new BorderLayout());
			intervenant_east = new JPanel(new BorderLayout());
			JButton creerIntervenant = new JButton("Créer un intervenant");
			JButton supprimerIntervenant = new JButton("Supprimer un intervenant");
			creerIntervenant.setActionCommand("creerIntervenant");
			creerIntervenant.addActionListener(ctrl);
			listInterv = new PanelJList(ctrl, model, ListType.INTERVENANT);
			listInterv.setAddable(true);
			listInterv.setLabel("Liste des Intervenants");
			intervenant_west.add(listInterv);
			supprimerIntervenant.setActionCommand("supprimerIntervenant");
			supprimerIntervenant.addActionListener(ctrl);
			this.add(intervenant_west,BorderLayout.WEST);
			this.add(intervenant_east,BorderLayout.EAST);
			intervenant_west.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
			
			
			
			
			
		}
		
		
	}
	
