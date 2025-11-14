import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textStore;
	private JTextField textLocation;
	private JTextField textPin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);

		JLabel lblStore = new JLabel("Store Name");
		panel.add(lblStore);

		textStore = new JTextField();
		panel.add(textStore);
		textStore.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);

		JLabel lblLocation = new JLabel("Location");
		panel_1.add(lblLocation);

		textLocation = new JTextField();
		panel_1.add(textLocation);
		textLocation.setColumns(10);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);

		JLabel lblPin = new JLabel("PIN");
		panel_2.add(lblPin);

		textPin = new JTextField();
		panel_2.add(textPin);
		textPin.setColumns(10);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// if there is no data.txt file, create it
				File dataFile = new File("data.txt");

				if (!dataFile.exists()) {
					JOptionPane.showMessageDialog(null, "Please register a new account.", "Error",
							JOptionPane.ERROR_MESSAGE);
					try {
						dataFile.createNewFile();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}

				// if data.txt is exists
				else {
					try {
						// login check
						FileInputStream file = new FileInputStream("data.txt");
						Scanner buffer = new Scanner(file);
						
						boolean loginSuccess = false;

						while (buffer.hasNext()) {
							/*
							 * Since multiple convenience stores are listed in data.txt,
							 * the name, location, pin must be retrieved separately for each store*/
							String nameLine = buffer.nextLine();
							if (!buffer.hasNextLine()) break;
							String locationLine = buffer.nextLine();
							if (!buffer.hasNextLine()) break;
							String pinLine = buffer.nextLine();

							String[] nameTokens = nameLine.split(": ");
							String[] locationTokens = locationLine.split(": ");
							String[] pinTokens = pinLine.split(": ");

							// skip invalid input
							if (nameTokens.length < 2 || locationTokens.length < 2 || pinTokens.length < 2) continue;

							// store name, location, pin all three elements should be matched
							boolean matched = 
								nameTokens[1].equalsIgnoreCase(textStore.getText()) &&
								locationTokens[1].equalsIgnoreCase(textLocation.getText()) &&
								pinTokens[1].equals(textPin.getText());

							if (matched) {
								loginSuccess = true;
								break;
							}

						}
						
						// all information matched
						if (loginSuccess) {
							String storeName = textStore.getText();
						    String productFileName = storeName + "_products.txt";
						    
						    Main frame = new Main(productFileName);
						    frame.setVisible(true);
						    dispose();

						} else {
							JOptionPane.showMessageDialog(null, "Store name, Location, PIN are not correct.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Please register a new account.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel_3.add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Register registerFrame = new Register();
				registerFrame.setVisible(true);
			}
		});
		panel_3.add(btnRegister);
	}

}
