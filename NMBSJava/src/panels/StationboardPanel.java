package panels;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class StationboardPanel extends JPanel {

	private JPanel searchPanel;
	private JPanel resultPanel;

	private JLabel lblTitle;
	private JLabel lblStation;
	private JLabel lblResult;

	private JTextField txtStation;

	private JButton btnZoek;

	public StationboardPanel() {
		initSearchPanel();
		initResultPanel();
		initializeCompletePanel();
	}

	private void initializeCompletePanel() {
		/* init pane */
		this.setLayout(new GridLayout(2, 1, 5, 5));

		/* Add all components */
		this.add(searchPanel);
		this.add(resultPanel);
	}

	private void initResultPanel() {
		/* init pane */
		resultPanel = new JPanel();
		resultPanel.setLayout(new GridLayout(1, 1, 5, 5));

		/* Create all components */
		lblResult = new JLabel("Druk op zoeken om een stationsbord weer te geven.");
		lblResult.setHorizontalAlignment(SwingConstants.LEFT);
		lblResult.setVerticalAlignment(SwingConstants.TOP);

		/* Add all components */
		resultPanel.add(lblResult);

	}

	private void initSearchPanel() {
		/* init pane */
		searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(4, 2, 5, 5));

		/* Create all components */
		lblTitle = new JLabel("Stationsbord");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblStation = new JLabel("Station: ");
		txtStation = new JTextField("");

		btnZoek = new JButton("Zoek");

		/* Add all components */
		searchPanel.add(lblTitle);
		searchPanel.add(new JLabel());
		searchPanel.add(lblStation);
		searchPanel.add(txtStation);
		searchPanel.add(new JLabel());
		searchPanel.add(btnZoek);
		searchPanel.add(new JLabel());
		searchPanel.add(new JLabel());
	}

	public JPanel getSearchPanel() {
		return searchPanel;
	}

	public JPanel getResultPanel() {
		return resultPanel;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public JLabel getLblStation() {
		return lblStation;
	}

	public JLabel getLblResult() {
		return lblResult;
	}

	public JTextField getTxtStation() {
		return txtStation;
	}

	public JButton getBtnZoek() {
		return btnZoek;
	}

}
