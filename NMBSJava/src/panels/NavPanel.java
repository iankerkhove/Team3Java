package panels;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class NavPanel extends JPanel {

	private JButton btnRouteZoek;
	private JButton btnTreinZoek;
	private JButton btnStationZoek;
	private JButton btnVerlorenVoorwerpenZoek;
	private JButton btnbtnVerlorenVoorwerpenVoegToe;
	private JButton btnBiljetKoop;
	private JButton btnAbonnementKoop;
	private JButton btnPrijzenAanpassen;

	public NavPanel() {
		initialize();
		createComponents();
		addComponents();
	}

	private void initialize() {
		this.setLayout(new GridLayout(8, 1, 5, 5));
	}

	private void createComponents() {

		btnRouteZoek = new JButton("Zoek route");
		btnRouteZoek.setHorizontalAlignment(SwingConstants.LEFT);

		btnTreinZoek = new JButton("Zoek trein");
		btnTreinZoek.setHorizontalAlignment(SwingConstants.LEFT);

		btnStationZoek = new JButton("Zoek station");
		btnStationZoek.setHorizontalAlignment(SwingConstants.LEFT);

		btnVerlorenVoorwerpenZoek = new JButton("Zoek voorwerp");
		btnVerlorenVoorwerpenZoek.setHorizontalAlignment(SwingConstants.LEFT);

		btnbtnVerlorenVoorwerpenVoegToe = new JButton("Voeg voorwerp toe");
		btnbtnVerlorenVoorwerpenVoegToe.setHorizontalAlignment(SwingConstants.LEFT);

		btnBiljetKoop = new JButton("Koop biljet");
		btnBiljetKoop.setHorizontalAlignment(SwingConstants.LEFT);

		btnAbonnementKoop = new JButton("Koop abonnement");
		btnAbonnementKoop.setHorizontalAlignment(SwingConstants.LEFT);

		btnPrijzenAanpassen = new JButton("Pas prijzen aan");
		btnPrijzenAanpassen.setHorizontalAlignment(SwingConstants.LEFT);
	}

	private void addComponents() {
		this.add(btnRouteZoek);
		this.add(btnTreinZoek);
		this.add(btnStationZoek);
		this.add(btnBiljetKoop);
		this.add(btnAbonnementKoop);
		this.add(btnVerlorenVoorwerpenZoek);
		this.add(btnbtnVerlorenVoorwerpenVoegToe);
		this.add(btnPrijzenAanpassen);
	}

	public JButton getBtnRouteZoek() {
		return btnRouteZoek;
	}

	public JButton getBtnTreinZoek() {
		return btnTreinZoek;
	}

	public JButton getBtnStationZoek() {
		return btnStationZoek;
	}

	public JButton getBtnVerlorenVoorwerpenZoek() {
		return btnVerlorenVoorwerpenZoek;
	}

	public JButton getBtnbtnVerlorenVoorwerpenVoegToe() {
		return btnbtnVerlorenVoorwerpenVoegToe;
	}

	public JButton getBtnBiljetKoop() {
		return btnBiljetKoop;
	}

	public JButton getBtnAbonnementKoop() {
		return btnAbonnementKoop;
	}

	public JButton getBtnPrijzenAanpassen() {
		return btnPrijzenAanpassen;
	}
}
