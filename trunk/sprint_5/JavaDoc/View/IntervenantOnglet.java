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

/**
 * Cette classe définit l'onglet Intervenant contenu dans la classe Fenetre
 * @author Thomas
 *
 */
public class IntervenantOnglet extends JPanel {

	JPanel intervenant_west;
	JPanel intervenant_east;
	
	private Controller ctrl;
	private Model model;
	
	public IntervenantOnglet(Controller ctrl, Model model) {
		
		this.ctrl = ctrl;
		this.model = model;
		
			this.setLayout(new GridLayout(1,2));
			intervenant_west = new JPanel(new GridBagLayout());
			intervenant_east = new JPanel(new BorderLayout());
			JButton creerIntervenant = new JButton("Créer un intervenant");
			JButton supprimerIntervenant = new JButton("Supprimer un intervenant");
			Border bordure = BorderFactory.createLineBorder(Color.black, 9);
			GridBagConstraints c = new GridBagConstraints();
			creerIntervenant.setActionCommand("creerIntervenant");
			creerIntervenant.addActionListener(ctrl);
			c.weighty = 1.0;  
			c.anchor = GridBagConstraints.PAGE_END;
			supprimerIntervenant.setActionCommand("supprimerIntervenant");
			supprimerIntervenant.addActionListener(ctrl);
			intervenant_west.add(creerIntervenant,c);
			intervenant_west.add(supprimerIntervenant,c);
			this.add(intervenant_west,BorderLayout.WEST);
			this.add(intervenant_east,BorderLayout.EAST);
			intervenant_west.setBorder(BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK));
			
			
			
			
			
		}
		
		
		
		
	}
	
