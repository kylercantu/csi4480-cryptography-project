
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

	private JPanel mainPanel;
	private JTextField keyTF;


	/**
	 * Create the frame.
	 */
	public MainScreen() {
		
		//JFrame Settings
		setTitle("Encrypt-This");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 683);
		setLocationRelativeTo(null);
		
		//JPanel Settings
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		JRadioButton aesRadio = new JRadioButton("AES");
		aesRadio.setHorizontalAlignment(SwingConstants.CENTER);
		aesRadio.setBackground(Color.LIGHT_GRAY);
		aesRadio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		aesRadio.setBounds(272, 39, 109, 23);
		mainPanel.add(aesRadio);
		
		JRadioButton rsaRadio = new JRadioButton("RSA");
		rsaRadio.setHorizontalAlignment(SwingConstants.CENTER);
		rsaRadio.setBackground(Color.LIGHT_GRAY);
		rsaRadio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rsaRadio.setBounds(383, 39, 109, 23);
		mainPanel.add(rsaRadio);
		
		JLabel chooseAlgLbl = new JLabel("Choose an Algorithm:");
		chooseAlgLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		chooseAlgLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		chooseAlgLbl.setBounds(88, 39, 174, 23);
		mainPanel.add(chooseAlgLbl);
		
		keyTF = new JTextField();
		keyTF.setBounds(272, 91, 381, 31);
		mainPanel.add(keyTF);
		keyTF.setColumns(10);
		
		JLabel keyLbl = new JLabel("Key:");
		keyLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		keyLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		keyLbl.setBounds(211, 90, 51, 28);
		mainPanel.add(keyLbl);
		
		JTextArea plainTextArea = new JTextArea();
		plainTextArea.setBackground(Color.WHITE);
		plainTextArea.setBounds(21, 172, 424, 369);
		mainPanel.add(plainTextArea);
		
		JTextArea cipherTextArea = new JTextArea();
		cipherTextArea.setBackground(Color.WHITE);
		cipherTextArea.setBounds(471, 172, 424, 369);
		mainPanel.add(cipherTextArea);
		
		JLabel plainTextLbl = new JLabel("Plain Text");
		plainTextLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		plainTextLbl.setBounds(44, 147, 103, 14);
		mainPanel.add(plainTextLbl);
		
		JLabel cipherTextLbl = new JLabel("Cipher Text");
		cipherTextLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		cipherTextLbl.setBounds(505, 148, 103, 14);
		mainPanel.add(cipherTextLbl);
		
		JButton encryptBtn = new JButton("Encrypt");
		encryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		encryptBtn.setBounds(154, 569, 130, 41);
		mainPanel.add(encryptBtn);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDecrypt.setBounds(633, 569, 130, 41);
		mainPanel.add(btnDecrypt);
		setVisible(true);
	}//End constructor
}//End MainScreen Class
