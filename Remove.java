import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class Remove extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String productFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Remove frame = new Remove(productFile);
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
	public Remove(String productFile) {
		this.productFile = productFile;
		setTitle("Remove Product");
		// keep main frame alive
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		ButtonGroup group = new ButtonGroup();

		for (Product p : ProductManage.loadProducts(productFile)) {
			// print only the quantity of product > 0
			if (p.getQuantity() > 0) {
				JRadioButton rb = new JRadioButton(
						p.getId() + " - " + p.getName() + " (" + p.getQuantity() + ", " + p.getExpirationDate() + ")");
				rb.setActionCommand(p.getId() + "|" + p.getExpirationDate().toString());
				group.add(rb);
				panel.add(rb);
			}
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		contentPane.add(scrollPane);

		JPanel panel1 = new JPanel();

		JButton removeBtn = new JButton("Remove Product");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonModel selectedModel = group.getSelection();

				// error : button is not selected
				if (selectedModel == null) {
					JOptionPane.showMessageDialog(null, "Please select a product to remove.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				/*
				 * To distinguish the following cases
				 * 1. Different ID
				 * 2. Same ID, different expiration date
				 * 3. Same ID, same expiration date
				 */
				String[] parts = selectedModel.getActionCommand().split("\\|");
				String selectedId = parts[0];
				LocalDate selectedDate = LocalDate.parse(parts[1]);

				// user input remove quantity
				String input = JOptionPane.showInputDialog(null, "Enter quantity to remove:", "Remove Quantity",
						JOptionPane.PLAIN_MESSAGE);

				if (input == null)
					return;

				try {
					int qty = Integer.parseInt(input);
					if (qty <= 0) {
						JOptionPane.showMessageDialog(null, "Enter a valid number.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					List<Product> productList = ProductManage.loadProducts(productFile);
					boolean found = false;
					Product toRemove = null;

					for (Product p : productList) {
						// select exact product
						if (p.getId().equals(selectedId) && p.getExpirationDate().equals(selectedDate)) {
							if (qty > p.getQuantity()) {
								JOptionPane.showMessageDialog(null, "Cannot remove more than existing quantity.",
										"Error", JOptionPane.ERROR_MESSAGE);
								return;
							}

							p.setQuantity(p.getQuantity() - qty);
							if (p.getQuantity() == 0) {
								toRemove = p;
							}
							found = true;
							break;
						}
					}

					if (!found) {
						JOptionPane.showMessageDialog(null, "Product not found.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// if quantity become 0 (because of removal) -> remove it
					if (toRemove != null) {
						productList.remove(toRemove);
					}

					ProductManage.saveProducts(productList, productFile);
					JOptionPane.showMessageDialog(null, "Product updated successfully.");

					new Remove(productFile).setVisible(true);
					dispose();

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel1.add(removeBtn);
		contentPane.add(panel1);

	}

}
