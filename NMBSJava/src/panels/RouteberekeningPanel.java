package panels;

import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.*;

import com.github.lgooddatepicker.components.TimePicker;

import gui.GUIDateFormat;

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

	private JTextField txtVan;
	private JTextField txtNaar;
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
		this.setVisible(false);

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
		txtVan = new JTextField("van");

		lblNaar = new JLabel("Naar: ");
		txtNaar = new JTextField("naar");

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
			datePicker.getJFormattedTextField().setText("01/11/2016");
			timePicker = new TimePicker();
			timePicker.setText("12:00");
			
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
}
