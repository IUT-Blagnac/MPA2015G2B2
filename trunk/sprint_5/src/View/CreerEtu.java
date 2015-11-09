/**
 * 
 */
package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Model.Etudiant;
import Model.Sujet;

/**
 * @author Sorény
 *
 */
public class CreerEtu extends JDialog{
	private JTextField nom, prenom;
	private Controller ctrl;
	
	private int idEtuM;
	private boolean mod;
	
	
	public CreerEtu(JFrame mere, String titre, Controller c, Etudiant e){
		this(mere, titre, c);
		this.mod = true;
		
		this.idEtuM = e.getId();
		this.nom.setText(e.getNom());
		this.prenom.setText(e.getPrenom());
	}
	
	public CreerEtu(JFrame mere, String titre, Controller c){
		super(mere,titre, true);
		this.ctrl = c;
		mod = false;
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.setLayout(new BorderLayout());	
		
		JPanel big = new JPanel(new BorderLayout());
		
		JPanel namePan = new JPanel(new GridLayout(2, 1));
		JPanel nomPan = new JPanel(new BorderLayout());
			nom = new JTextField();
		JPanel prenomPan = new JPanel( new BorderLayout());
			prenom = new JTextField();
		JPanel buttonsPanel = new JPanel(new FlowLayout());
			JButton addB = new JButton("Ajouter");
			JButton annB = new JButton("Annuler");
				
		namePan.add(nomPan);
		namePan.add(prenomPan);
		nomPan.add(new JLabel("Nom :       "), BorderLayout.WEST);
		nomPan.add(nom, BorderLayout.CENTER);
		prenomPan.add(new JLabel("Prenom : "), BorderLayout.WEST);
		prenomPan.add(prenom, BorderLayout.CENTER);
		buttonsPanel.add(addB);
			addB.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(mod)
						ctrl.modifierEtudiant(new Etudiant(idEtuM, nom.getText(), prenom.getText()));
					else
						ctrl.creationEtudiant(new Etudiant(nom.getText(), prenom.getText()));
					dispose();
				}
			});
		buttonsPanel.add(annB);
			annB.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		
		big.add(namePan, BorderLayout.CENTER);
		this.add(big, BorderLayout.NORTH);
		this.add(buttonsPanel, BorderLayout.SOUTH);
		this.pack();
		this.setSize(getWidth()+50, getHeight());
		setResizable(false);
	}
	
	
}
