package panels;

import javax.swing.*;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel {

	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblAdmin;
	
	private JRadioButton rbtnAdmin;
	
	private JButton btnLogin;
	
	private JTextField txtUsername;
	private JTextField txtPassword;
	
	public LoginPanel(){
		setLayout(null);
		
		lblUsername = new JLabel();
		lblUsername.setBounds(97,100,60,20);
		lblUsername.setText("Username:");
		this.add(lblUsername);
		
		lblPassword = new JLabel();
		lblPassword.setBounds(97,131,60,20);
		lblPassword.setText("Password:");
		this.add(lblPassword);
		
		lblAdmin = new JLabel();
		lblAdmin.setBounds(190,162,60,20);
		lblAdmin.setText("Admin");
		this.add(lblAdmin);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(167, 100, 102, 20);
		this.add(txtUsername);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(167, 131, 102, 20);
		this.add(txtPassword);
		
		rbtnAdmin = new JRadioButton();
		rbtnAdmin.setLocation(167, 162);
		rbtnAdmin.setSize(21, 20);
		this.add(rbtnAdmin);
		
		btnLogin = new JButton();
		btnLogin.setBounds(167, 193, 86, 20);
		btnLogin.setText("Login");
		this.add(btnLogin);
		
	}

}
