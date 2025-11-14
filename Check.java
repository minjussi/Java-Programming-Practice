import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class Check extends JFrame {

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
					Check frame = new Check(productFile);
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
	public Check(String productFile) {
		this.productFile = productFile;
		setTitle("Product List");
		// keep main frame alive
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		// sorted product list according to expiration date
		List<Product> sortedList = ProductManage.getProductExpiration(productFile);
		
		// print product list
		StringBuilder builder = new StringBuilder();
	    for (Product p : sortedList) {
	    	if (p.getQuantity() > 0) {
	        builder.append(p.getId()).append(" - ")
	               .append(p.getName()).append(" / ")
	               .append(p.getQuantity()).append(" / ")
	               .append(p.getExpirationDate()).append(" / $")
	               .append(p.getPrice()).append("\n");
	    	}
	    }

	    textArea.setText(builder.toString());
	}

}
