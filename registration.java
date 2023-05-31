import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class registration {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txttelephone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registration window = new registration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public registration() {
		initialize();
		Connect();
	}
	Connection con;
	PreparedStatement pst;
	String name, telephone, gender, colour;
	
	public void Connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/registration", "root", "");
		}
		catch(ClassNotFoundException ex)	
		{
			ex.printStackTrace();
		}
		catch(SQLException ex) 
		{
		   ex.printStackTrace();
		}
								
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.CYAN);
		frame.getContentPane().setBackground(new Color(0, 255, 64));
		frame.getContentPane().setForeground(new Color(0, 128, 192));
		frame.setBounds(100, 100, 709, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(193, 23, 285, 68);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(26, 124, 617, 317);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("FirstName and LastName");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 41, 216, 27);
		panel.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setBounds(256, 41, 272, 25);
		panel.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Telephone");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 105, 169, 27);
		panel.add(lblNewLabel_1_1);
		
		txttelephone = new JTextField();
		txttelephone.setColumns(10);
		txttelephone.setBounds(256, 108, 272, 25);
		panel.add(txttelephone);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Gender");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 165, 169, 27);
		panel.add(lblNewLabel_1_1_1);
		
		JRadioButton rbmale = new JRadioButton("Male");
		rbmale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbmale.setBounds(256, 165, 153, 26);
		panel.add(rbmale);
		
		JRadioButton rbfemale = new JRadioButton("Female");
		rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbfemale.setBounds(425, 170, 103, 21);
		panel.add(rbfemale);
		
		JComboBox txtcolour = new JComboBox();
		txtcolour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtcolour.setModel(new DefaultComboBoxModel(new String[] {"Red", "Blue", "Black", "White", "Green", "Yellow", "Pink", "Violet"}));
		txtcolour.setBounds(228, 245, 300, 21);
		panel.add(txtcolour);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Colour");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1_1.setBounds(10, 242, 169, 27);
		panel.add(lblNewLabel_1_1_1_1);
		
		JButton btnNewButton = new JButton("Save");
		
				

	
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {  
	
	
	            name = txtname.getText();
	            telephone = txttelephone.getText();
	            
	            if(rbmale.isSelected()) {
	            	gender = "Male";
	            }
	            if(rbfemale.isSelected()) {
	            	gender = "Female";
	            }
	            colour = txtcolour.getSelectedItem().toString();
	            
	            
				try {
					pst = con.prepareStatement("inset into registration(name,telephone,gender,colour)values(?,?,?,?)");
					pst.setString(1, name);
					pst.setString(2, telephone);
					pst.setString(3, gender);
					pst.setString(4, colour);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Added!");
					txtname.setText("");
					txttelephone.setText("");
					txtcolour.setSelectedIndex(-1);
					txtname.requestFocus();	
				}
				catch(SQLException e1){
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(472, 477, 114, 59);
		frame.getContentPane().add(btnNewButton);
	}
}
