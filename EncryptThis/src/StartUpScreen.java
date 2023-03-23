import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;


public class StartUpScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartUpScreen frame = new StartUpScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartUpScreen() {
		//JFrame settings
		setResizable(false);
		setTitle("Encrypt-This");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 464);
		setLocationRelativeTo(null);
		
		//JPanel Settings
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("Encrypt-This");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setForeground(Color.WHITE);
		titleLbl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		titleLbl.setBounds(136, 123, 203, 58);
		contentPane.add(titleLbl);
		
		JLabel pictureLbl = new JLabel("");
		pictureLbl.setBounds(0, 0, 477, 304);
		pictureLbl.setIcon(new ImageIcon(StartUpScreen.class.getResource("/Images/Cryptography.jpg")));
		contentPane.add(pictureLbl);
		
		JButton proceedBtn = new JButton("Proceed");
		proceedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == proceedBtn)
				dispose();
				MainScreen ms = new MainScreen();
			}
		});
		proceedBtn.setBounds(175, 339, 126, 46);
		contentPane.add(proceedBtn);
	}//End constructor
}//End StartUpScreen class
