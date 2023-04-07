
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainScreen extends JFrame {

	private JPanel mainPanel;
	private JTextField keyTF;
	private AlgorithmRSA rsa = new AlgorithmRSA();
	private static AlgorithmAES aes = new AlgorithmAES();
	private String inputKeytext;
	JFileChooser fc = new JFileChooser();

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
		aesRadio.setBounds(272, 33, 109, 23);
		mainPanel.add(aesRadio);
		
		JRadioButton rsaRadio = new JRadioButton("RSA");
		rsaRadio.setHorizontalAlignment(SwingConstants.CENTER);
		rsaRadio.setBackground(Color.LIGHT_GRAY);
		rsaRadio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rsaRadio.setBounds(383, 33, 109, 23);
		mainPanel.add(rsaRadio);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(aesRadio);
		buttonGroup.add(rsaRadio);
		aesRadio.setSelected(true);
		
		
		JButton selectFileBtn = new JButton("Select File");
		selectFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rsaRadio.isSelected()) {
					String key = rsa.getKeyFromFile();
					keyTF.setText(key);
				}
			}
		});
		selectFileBtn.setFont(new Font("Tahoma", Font.PLAIN, 10));
		selectFileBtn.setBounds(403, 103, 89, 27);
		mainPanel.add(selectFileBtn);
		

	
		JButton generateKeyBtn = new JButton("Generate Key");
		generateKeyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rsaRadio.isSelected()) {
					rsa.generateKeys();
					JOptionPane.showMessageDialog(null, "Key(s) Generated and Saved to Desktop.");
				}
				if (aesRadio.isSelected()) {
					inputKeytext = JOptionPane.showInputDialog(generateKeyBtn, "Choose your key");
					aes.setKey(inputKeytext);
				}
			}
		});
		generateKeyBtn.setBounds(725, 52, 130, 63);
		mainPanel.add(generateKeyBtn);
		
		JLabel generateKeyLbl = new JLabel("Don't have a key?");
		generateKeyLbl.setBounds(725, 27, 130, 14);
		mainPanel.add(generateKeyLbl);
		
		JLabel chooseAlgLbl = new JLabel("Choose an Algorithm:");
		chooseAlgLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		chooseAlgLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		chooseAlgLbl.setBounds(92, 33, 174, 23);
		mainPanel.add(chooseAlgLbl);
		
		keyTF = new JTextField();
		keyTF.setBounds(272, 68, 381, 31);
		mainPanel.add(keyTF);
		keyTF.setColumns(10);
		
		JLabel keyLbl = new JLabel("Key:");
		keyLbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		keyLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		keyLbl.setBounds(65, 67, 198, 28);
		mainPanel.add(keyLbl);
		
		JTextArea inputTextArea = new JTextArea();
		inputTextArea.setBackground(Color.WHITE);
		inputTextArea.setBounds(21, 172, 424, 369);
		inputTextArea.setLineWrap(true);
		mainPanel.add(inputTextArea);
		
		JTextArea outputTextArea = new JTextArea();
		outputTextArea.setEditable(false);
		outputTextArea.setBackground(Color.WHITE);
		outputTextArea.setBounds(471, 172, 424, 369);
		outputTextArea.setLineWrap(true);
		mainPanel.add(outputTextArea);
		
		JLabel inputLbl = new JLabel("Input:");
		inputLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		inputLbl.setBounds(44, 147, 103, 14);
		mainPanel.add(inputLbl);
		
		JLabel outputLbl = new JLabel("Output:");
		outputLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		outputLbl.setBounds(505, 148, 103, 14);
		mainPanel.add(outputLbl);
		
		JButton encryptBtn = new JButton("Encrypt");
		encryptBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rsaRadio.isSelected()) {
					String publicKey = getFileContents();
					if(keyTF.getText().equals(publicKey) || keyTF.getText().length() == 392) {
						try {
							String encMsg = rsa.encryptMsg(inputTextArea);
							outputTextArea.setText(encMsg);
						} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
								| IllegalBlockSizeException | BadPaddingException e1) {
							
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Key");
					}	
				}
				if(aesRadio.isSelected()) {
					
					String encMsg = aes.encrypt(inputTextArea.getText(), inputKeytext);
					outputTextArea.setText(encMsg); 
					
				}
				
			}
		});
		encryptBtn.setBounds(315, 569, 130, 41);
		mainPanel.add(encryptBtn);
		
		

		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rsaRadio.isSelected()) {
					String privateKey = getPrivateKeyFromFile();
					if(keyTF.getText().equals(privateKey)) {
						try {
							String decMsg = rsa.decryptMsg(inputTextArea);
							outputTextArea.setText(decMsg);
						} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException
								| IllegalBlockSizeException | BadPaddingException e1) {
	
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Key");
					}
				}
				if(aesRadio.isSelected()) {
					String decMsg = aes.decrypt(inputTextArea.getText(), keyTF.getText());
					outputTextArea.setText(decMsg);
				}
				
			}
		});
		btnDecrypt.setBounds(471, 569, 130, 41);
		mainPanel.add(btnDecrypt);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Email");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmailSender(outputTextArea.getText()).setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		setVisible(true);

	}//End constructor
	
	
	public String getFileContents() {
		String filePath = System.getProperty("user.home") + "\\Desktop\\RSApublickey.txt";
		
		try {
			 File file = new File(filePath);
            Scanner scan = new Scanner(file);
            String returnMe = scan.nextLine();
            scan.close();
            return returnMe;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "No such file";
		}
	}
	
	public String getPrivateKeyFromFile() {
		String filePath = System.getProperty("user.home") + "\\Desktop\\RSAprivatekey.txt";
		
		try {
			 File file = new File(filePath);
            Scanner scan = new Scanner(file);
            String returnMe = scan.nextLine();
            scan.close();
            return returnMe;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "No such file";
		}
	}
	
}//End MainScreen Class
