import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Register extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textNewStore;
	private JTextField textNewLocation;
	private JTextField textNewPin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Register dialog = new Register();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Register() {
		setTitle("Register");
		setBounds(100, 100, 350, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(7, 1, 0, 0));
		{
			JLabel lblNewStore = new JLabel("Store Name    ex.gs25");
			contentPanel.add(lblNewStore);
		}
		{
			textNewStore = new JTextField();
			contentPanel.add(textNewStore);
			textNewStore.setColumns(10);
		}
		{
			JLabel lblNewLocation = new JLabel("Location    ex.seoul");
			contentPanel.add(lblNewLocation);
		}
		{
			textNewLocation = new JTextField();
			contentPanel.add(textNewLocation);
			textNewLocation.setColumns(10);
		}
		{
			JLabel lblNewPin = new JLabel("PIN    ex. 0000");
			contentPanel.add(lblNewPin);
		}
		{
			textNewPin = new JTextField();
			contentPanel.add(textNewPin);
			textNewPin.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {

							String newStore = textNewStore.getText().trim();
							String newLocation = textNewLocation.getText().trim();
							String newPin = textNewPin.getText().trim();

							List<String> existingStores = new ArrayList<>();

							/*
							 * Make a list for store name
							 * so that there is no duplicated store name
							 */
							try {
								FileInputStream file = new FileInputStream("data.txt");
								Scanner buffer = new Scanner(file);

								while (buffer.hasNextLine()) {
									String line = buffer.nextLine();
									if (line.startsWith("Store Name: ")) {
										String storeName = line.substring(12).trim();
										existingStores.add(storeName);
									}
								}

								buffer.close();
								file.close();

							} catch (FileNotFoundException ex) {
								ex.printStackTrace();
							}

							// Checking Errors
							StringBuilder errorMessages = new StringBuilder();

							// Store Name Check
							if (newStore.isEmpty() || newStore.length() > 100) {
								// length limit
								errorMessages.append("Store name is invalid.\n");
							} else {
								// store name is unique
								boolean duplicate = existingStores.stream().anyMatch(s -> s.equalsIgnoreCase(newStore));
								if (duplicate) {
									errorMessages.append("Store name already exists.\n");
								}
							}

							// Location Check
							if (newLocation.isEmpty() || newLocation.length() > 100) {
								// length limit
								errorMessages.append("Location is invalid.\n");
							} else {
								// Strings only
								boolean locationHasDigit = false;
								for (char c : newLocation.toCharArray()) {
									if (Character.isDigit(c)) {
										locationHasDigit = true;
										break;
									}
								}
								if (locationHasDigit) {
									errorMessages.append("Location is invalid.\n");
								}
							}

							// PIN Check
							if (newPin.isEmpty() || newPin.length() != 4) {
								// length limit -> only 4-digit
								errorMessages.append("PIN must be 4-digit number.\n");
							} else {
								// Number only
								boolean pinValid = true;
								for (char c : newPin.toCharArray()) {
									if (!Character.isDigit(c)) {
										pinValid = false;
										break;
									}
								}
								if (!pinValid) {
									errorMessages.append("PIN must be a 4-digit number.\n");
								}
							}

							// Print all error messages
							if (errorMessages.length() > 0) {
								JOptionPane.showMessageDialog(null, errorMessages.toString(), "Input Error",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							FileOutputStream file = new FileOutputStream("data.txt", true);
							PrintStream buffer = new PrintStream(file);

							buffer.println("Store Name: " + newStore);
							buffer.println("Location: " + newLocation);
							buffer.println("Pin: " + newPin);

							buffer.close();
							file.close();

							dispose();
							Login dialog = new Login();
							dialog.setVisible(true);

						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
