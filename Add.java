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
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;

public class Add extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static String productId;
	private static String productFile;
	private JTextField textQty;
	private JTextField textExp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Add dialog = new Add(productId, productFile);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Add(String productId, String productFile) {
		this.productId = productId;
		this.productFile = productFile;

		setTitle("Add Product");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 1, 0, 0));
		{
			JLabel lblQty = new JLabel("Quantity to Add");
			contentPanel.add(lblQty);
		}
		{
			textQty = new JTextField();
			contentPanel.add(textQty);
			textQty.setColumns(10);
		}
		{
			JLabel lblExp = new JLabel("Expiration Date (ex. YYYY-MM-DD)");
			contentPanel.add(lblExp);
		}
		{
			textExp = new JTextField();
			contentPanel.add(textExp);
			textExp.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String qtyStr = textQty.getText();

						StringBuilder error = new StringBuilder();

						int qtyToAdd = 0;
						try {
							qtyToAdd = Integer.parseInt(qtyStr);
							// limitation
							if (qtyToAdd <= 0 || qtyToAdd > 1000) {
								error.append("Invalid quantity.\n");
							}
						} catch (NumberFormatException ex) {
							error.append("Invalid quantity.\n");
						}

						LocalDate expDate;

						try {
							String dateStr = textExp.getText().trim();
							if (dateStr.isEmpty())
								throw new IllegalArgumentException();
							expDate = LocalDate.parse(dateStr);
						} catch (Exception ex) {
							error.append("Invalid expiration date. Use YYYY-MM-DD.\n");
							JOptionPane.showMessageDialog(null, error.toString(), "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if (error.length() > 0) {
							JOptionPane.showMessageDialog(null, error.toString(), "Error", 
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						List<Product> productList = ProductManage.loadProducts(productFile);
						boolean found = false;

						// if id && expiration exists -> add only quantity
						for (Product p : productList) {
							if (p.getId().equals(productId) && p.getExpirationDate().equals(expDate)) {
								p.setQuantity(p.getQuantity() + qtyToAdd);
								found = true;
								break;
							}
						}

						// else : set expiration date & quantity
						if (!found) {
							String name = null;
							int price = 0;
							for (Product p : productList) {
								if (p.getId().equals(productId)) {
									name = p.getName();
									price = p.getPrice();
									break;
								}
							}
							Product newProduct = new Product(productId, name, qtyToAdd, expDate, price);
							productList.add(newProduct);
						}
						ProductManage.saveProducts(productList, productFile);
						JOptionPane.showMessageDialog(null, "Product updated successfully!");
						dispose();

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
