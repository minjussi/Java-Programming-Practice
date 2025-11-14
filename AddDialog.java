import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textInputId;
	private static String productFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddDialog dialog = new AddDialog(productFile);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddDialog(String productFile) {
		this.productFile = productFile;
		setTitle("Product ID");
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JLabel lblInputId = new JLabel("Product ID");
			contentPanel.add(lblInputId);
		}
		{
			textInputId = new JTextField();
			contentPanel.add(textInputId);
			textInputId.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String inputId = textInputId.getText();
						if (inputId.isEmpty()) {
				            JOptionPane.showMessageDialog(null, "Please enter a Product ID.", 
				            		"Input Error", JOptionPane.ERROR_MESSAGE);
				            return;
				        } else {
				        	// Numbers only
						    boolean idValid = true;
						    for (char c : inputId.toCharArray()) {
						        if (!Character.isDigit(c)) {
						            idValid = false;
						            break;
						        }
						    }
						    if (!idValid) {
						    	JOptionPane.showMessageDialog(null, "Invalid Product ID.", 
					            		"Input Error", JOptionPane.ERROR_MESSAGE);
						    	return;
						    }
						}
						
						// if product id exists
						if (ProductManage.isProduct(inputId, productFile)) {
				            Add addFrame = new Add(inputId, productFile);
				            addFrame.setVisible(true);
				            dispose();
				        } else {
				        	// else -> add new product
				            AddItem addItemFrame = new AddItem(inputId, productFile);
				            addItemFrame.setVisible(true);
				            dispose();
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
