package panels;

import java.awt.GridLayout;

import javax.swing.*;

public class TaalPanel extends JPanel{
	private JButton btnFr;
	private JButton btnNl;
	private JButton btnEn;
	
	public TaalPanel(){
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(3, 1, 15, 15));
		
		btnFr = new JButton("Français");
		btnNl = new JButton("Nederlands");
		btnEn = new JButton("English");
		
		content.add(btnFr);
		content.add(btnNl);
		content.add(btnEn);
		
		this.add(content);
	}

	public JButton getBtnFr() {
		return btnFr;
	}

	public JButton getBtnNl() {
		return btnNl;
	}

	public JButton getBtnEn() {
		return btnEn;
	}
	

	
}
