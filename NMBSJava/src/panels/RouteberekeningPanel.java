package panels;

import java.awt.GridLayout;

import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.*;

import com.github.lgooddatepicker.components.TimePicker;

import gui.GUIDateFormat;
import gui.StationsAutoCompletor;
import gui.LangageHandler;

import java.awt.Font;

@SuppressWarnings("serial")
public class RouteberekeningPanel extends JPanel {

	private String taal = LangageHandler.getTaal();
	
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
	
	private JRadioButton rdbVertrek;
	private JRadioButton rdbAankomst;
	private ButtonGroup grpTimeSel;

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
		lblResult = new JLabel();
		LangageHandler.chooseLangageLbl(lblResult, taal, "resRoute");
		lblResult.setHorizontalAlignment(SwingConstants.LEFT);
		lblResult.setVerticalAlignment(SwingConstants.TOP);

		/* Add all components */
		JScrollPane scroller = new JScrollPane(lblResult, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resultPanel.add(scroller);

	}

	private void initSearchPanel() {
		/* init pane */
		searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(6, 2, 5, 5));

		/* Create all components */
		lblTitle = new JLabel();
		LangageHandler.chooseLangageLbl(lblTitle, taal, "routeberekening");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lblVan = new JLabel();
		LangageHandler.chooseLangageLbl(lblVan, taal, "van");
		txtVan = new StationsAutoCompletor();

		lblNaar = new JLabel("Naar: ");
		LangageHandler.chooseLangageLbl(lblNaar, taal, "naar");
		txtNaar = new StationsAutoCompletor();

		lblTijd = new JLabel();
		LangageHandler.chooseLangageLbl(lblTijd, taal, "tijd");
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
		
		rdbVertrek = new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbVertrek, taal, "vertrek");
		rdbVertrek.setSelected(true);
		rdbVertrek.setMnemonic(1);
		rdbAankomst = new JRadioButton();
		LangageHandler.chooseLangageRdb(rdbAankomst, taal, "Aankomst");
		rdbAankomst.setMnemonic(2);
		grpTimeSel = new ButtonGroup();
		grpTimeSel.add(rdbVertrek);
		grpTimeSel.add(rdbAankomst);
		
		JPanel timeSelPanel = new JPanel();
		timeSelPanel.add(rdbVertrek);
		timeSelPanel.add(rdbAankomst);

		btnZoek = new JButton("Zoek");
		LangageHandler.chooseLangageBtn(btnZoek, taal, "zoek");

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
		searchPanel.add(timeSelPanel);
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

	public ButtonGroup getGrpTimeSel() {
		return grpTimeSel;
	}
}
