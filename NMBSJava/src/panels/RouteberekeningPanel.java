package panels;

import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.*;

import com.github.lgooddatepicker.components.TimePicker;

import gui.GUIDateFormat;
import gui.StationsAutoCompletor;

import java.awt.Font;

@SuppressWarnings("serial")
public class RouteberekeningPanel extends JPanel {

	private JPanel searchPanel;
	private JPanel timePanel;
	private JPanel resultPanel;

	private JLabel lblTitle;
	private JLabel lblVan;
	private JLabel lblNaar;
	private JLabel lblTijd;
	private JLabel lblResult;

	private StationsAutoCompletor txtVan;
	private StationsAutoCompletor txtNaar;
	private JDatePickerImpl datePicker;
	private TimePicker timePicker;

	private JButton btnZoek;

	public RouteberekeningPanel() {
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
		lblResult = new JLabel("Druk op zoeken om een route weer te geven.");
		lblResult.setHorizontalAlignment(SwingConstants.LEFT);
		lblResult.setVerticalAlignment(SwingConstants.TOP);

		/* Add all components */
		resultPanel.add(lblResult);

	}

	private void initSearchPanel() {
		/* init pane */
		searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(5, 2, 5, 5));

		/* Create all components */
		lblTitle = new JLabel("Routeberekening");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblVan = new JLabel("Van: ");
		txtVan = new StationsAutoCompletor();

		lblNaar = new JLabel("Naar: ");
		txtNaar = new StationsAutoCompletor();

		lblTijd = new JLabel("Tijd:");
		{
			timePanel = new JPanel();
			timePanel.setLayout(new GridLayout(1, 2, 5, 5));

			Properties properties = new Properties();
			properties.put("text.today", "Today");
			properties.put("text.month", "Month");
			properties.put("text.year", "Year");
			JDatePanelImpl datePanel = new JDatePanelImpl(new UtilDateModel(), properties);
			datePicker = new JDatePickerImpl(datePanel, new GUIDateFormat());
			datePicker.getJFormattedTextField().setText(GUIDateFormat.getDate());
			timePicker = new TimePicker();
			timePicker.setText(GUIDateFormat.getTime());

			timePanel.add(datePicker);
			timePanel.add(timePicker);
		}

		btnZoek = new JButton("Zoek");

		/* Add all components */
		searchPanel.add(lblTitle);
		searchPanel.add(new JLabel());
		searchPanel.add(lblVan);
		searchPanel.add(txtVan);
		searchPanel.add(lblNaar);
		searchPanel.add(txtNaar);
		searchPanel.add(lblTijd);
		searchPanel.add(timePanel);
		searchPanel.add(new JLabel());
		searchPanel.add(btnZoek);
	}

	public JPanel getSearchPanel() {
		return searchPanel;
	}

	public JPanel getTimePanel() {
		return timePanel;
	}

	public JPanel getResultPanel() {
		return resultPanel;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public JLabel getLblVan() {
		return lblVan;
	}

	public JLabel getLblNaar() {
		return lblNaar;
	}

	public JLabel getLblTijd() {
		return lblTijd;
	}

	public JLabel getLblResult() {
		return lblResult;
	}

	public StationsAutoCompletor getTxtVan() {
		return txtVan;
	}

	public StationsAutoCompletor getTxtNaar() {
		return txtNaar;
	}

	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}

	public TimePicker getTimePicker() {
		return timePicker;
	}

	public JButton getBtnZoek() {
		return btnZoek;
	}
}
