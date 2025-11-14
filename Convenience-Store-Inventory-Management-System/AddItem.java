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

public class AddItem extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static String productId;
	private static String productFile;
	private JTextField textNewName;
	private JTextField textNewPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddItem dialog = new AddItem(productId, productFile);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddItem(String productId, String productFile) {
		this.productId = productId;
		this.productFile = productFile;
		
		setTitle("Add New Product");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 2, 0, 0));
		{
			JLabel lblNewName = new JLabel("Product Name");
			contentPanel.add(lblNewName);
		}
		{
			textNewName = new JTextField();
			contentPanel.add(textNewName);
			textNewName.setColumns(10);
		}
		{
			JLabel lblNewPrice = new JLabel("Price");
			contentPanel.add(lblNewPrice);
		}
		{
			textNewPrice = new JTextField();
			contentPanel.add(textNewPrice);
			textNewPrice.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = textNewName.getText();
						String priceStr = textNewPrice.getText();
						
						StringBuilder error = new StringBuilder();
						
						if (name.isEmpty()) {
							error.append("Product name cannot be empty.\n");
						} else {
							// only strings
							for (char c : name.toCharArray()) {
								if (Character.isDigit(c)) {
									error.append("Invalid product name.\n");
									break;
								}
							}
						}
						
						int price = 0;
						try {
							price = Integer.parseInt(priceStr);
							if (price <= 0) {
								error.append("Invalid price.\n");
							}
						} catch (NumberFormatException ex) {
							// only digits
							error.append("Invalid price.\n");
						}
						
						if (error.length() > 0) {
							JOptionPane.showMessageDialog(null, error.toString(), "Error", 
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						// default format of the registered product
						Product newProduct = new Product(productId, name, 0, LocalDate.parse("9999-12-31"), price);
						ProductManage.addProduct(newProduct, productFile);

						// after adding new product -> input quantity and expiration date
						new Add(productId, productFile).setVisible(true);
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
