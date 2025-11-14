import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

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
					Main frame = new Main(productFile);
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
	public Main(String productFile) {
		this.productFile = productFile;
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// if there is no product file, it should be created
		// file name : store name_products.txt (store name is unique)
		File file = new File(productFile);
	    if (!file.exists()) {
	        try {
	            file.createNewFile();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	    }

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		ImageIcon icon = new ImageIcon(getClass().getResource("banner.png"));
		Image scaled = icon.getImage().getScaledInstance(350, 120, Image.SCALE_SMOOTH);
		JLabel imageLabel = new JLabel(new ImageIcon(scaled));

		contentPane.add(imageLabel);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setVgap(50);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Convenience Store Inventory Management Program");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		panel_1.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(25);
		flowLayout.setVgap(50);
		contentPane.add(panel);
		
		// add product
		JButton btnAdd = new JButton("ADD PRODUCT");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDialog addMain = new AddDialog(productFile);
				addMain.setVisible(true);
			}
		});
		panel.add(btnAdd);
		
		// check product list
		JButton btnCheck = new JButton("CHECK LIST");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check checkMain = new Check(productFile);
				checkMain.setVisible(true);
			}
		});
		panel.add(btnCheck);
		
		// remove product
		JButton btnRemove = new JButton("REMOVE PRODUCT");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Remove removeMain = new Remove(productFile);
				removeMain.setVisible(true);
			}
		});
		panel.add(btnRemove);
	}

}
