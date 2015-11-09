package View;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import Controller.Controller;
import Model.Sujet;

public class SupprimerSujet extends JDialog {
	
	private static final String testFilesPath = "./";
	private JLabel label = new JLabel("Id du sujet à supprimer : ");
	private JButton annuler = new JButton("Annuler");
	private JButton valider = new JButton("Valider");
	private String[] tabSujets;
	private Controller controller;
	
	public SupprimerSujet(JFrame mere, String titre, Controller ctrl, JList<Sujet> sujets) {
		super(mere, titre, true);
		this.controller = ctrl;
	    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    
	    
	    
	    this.setContentPane(panneauContenu());
	    this.pack();
	}



	JPanel panneauContenu() {
		
		JPanel principalPane = new JPanel(new GridLayout(5,1));
		
		principalPane.setLayout(new GridLayout(2,2));
		
		
		JComboBox<String> selection = new JComboBox<>(tabSujets);
		
		
		principalPane.add(label);
		principalPane.add(selection);
		principalPane.add(annuler);
		principalPane.add(valider);
		
		
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	dispose();
            }
        });
		String select = (String) selection.getSelectedItem();
		System.out.println(select);
		
		valider.setActionCommand("deleteSujet " + select.split("0")[0]);
		valider.addActionListener(controller);

		this.pack();
		return principalPane;
		
		
		
	}
}

