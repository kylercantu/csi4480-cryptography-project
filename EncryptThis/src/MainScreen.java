import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

	private JPanel keyLbl;
	private JTextField keyTF;



	/**
	 * Create the frame.
	 */
	public MainScreen() {
		setTitle("Encrypt-This");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 683);
		setLocationRelativeTo(null);
		
		keyLbl = new JPanel();
		keyLbl.setBackground(Color.LIGHT_GRAY);
		keyLbl.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(keyLbl);
		keyLbl.setLayout(null);
		
		JRadioButton aesRadio = new JRadioButton("AES");
		aesRadio.setHorizontalAlignment(SwingConstants.CENTER);
		aesRadio.setBackground(Color.LIGHT_GRAY);
		aesRadio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		aesRadio.setBounds(272, 39, 109, 23);
		keyLbl.add(aesRadio);
		
		JRadioButton rsaRadio = new JRadioButton("RSA");
		rsaRadio.setHorizontalAlignment(SwingConstants.CENTER);
		rsaRadio.setBackground(Color.LIGHT_GRAY);
		rsaRadio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rsaRadio.setBounds(383, 39, 109, 23);
		keyLbl.add(rsaRadio);
		
		JLabel chooseAlgLbl = new JLabel("Choose an Algorithm:");
		chooseAlgLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		chooseAlgLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		chooseAlgLbl.setBounds(88, 39, 174, 23);
		keyLbl.add(chooseAlgLbl);
		
		keyTF = new JTextField();
		keyTF.setBounds(272, 91, 381, 31);
		keyLbl.add(keyTF);
		keyTF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Key:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(211, 90, 51, 28);
		keyLbl.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(21, 172, 410, 369);
		keyLbl.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBackground(Color.WHITE);
		textArea_1.setBounds(485, 172, 410, 369);
		keyLbl.add(textArea_1);
		
		JLabel plainTextLbl = new JLabel("Plain Text");
		plainTextLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		plainTextLbl.setBounds(44, 147, 103, 14);
		keyLbl.add(plainTextLbl);
		
		JLabel cipherTextLbl = new JLabel("Cipher Text");
		cipherTextLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		cipherTextLbl.setBounds(505, 148, 103, 14);
		keyLbl.add(cipherTextLbl);
		
		JButton encryptBtn = new JButton("Encrypt");
		encryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		encryptBtn.setBounds(154, 569, 130, 41);
		keyLbl.add(encryptBtn);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDecrypt.setBounds(633, 569, 130, 41);
		keyLbl.add(btnDecrypt);
		setVisible(true);
	}
}
