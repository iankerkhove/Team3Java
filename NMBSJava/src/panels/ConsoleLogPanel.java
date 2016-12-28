package panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.GUIDateFormat;

public class ConsoleLogPanel extends JPanel {

	private JLabel console;
	private String txt = "";

	public ConsoleLogPanel() {
		setLayout(new GridLayout(1, 1, 0, 0));
		console = new JLabel();
		JScrollPane scroller = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setSize(600, 100);
		this.add(scroller);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 50);
	};
	
	public void addLine(String toAdd){
		String temp = GUIDateFormat.getTimeWithSec() + " " + toAdd + "<br>";
		txt = temp + txt;
		console.setText("<html>"+ txt +"</html>");
	}
}
