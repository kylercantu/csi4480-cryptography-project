import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.PublicKey;

public class HelpScreen extends JFrame {

	private JPanel contentPane;
    private JTextArea txtWords;

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpScreen frame = new HelpScreen("AES");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} // end main

    public HelpScreen(String defMethod) {
        setTitle("Help is on the way dear!");
		setResizable(false);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

        JLabel lblMethod = new JLabel("Choose a method to learn more about:");
        lblMethod.setOpaque(true);
        lblMethod.setBounds(12,12,250,23);
        contentPane.add(lblMethod);

        ButtonGroup btnGroup = new ButtonGroup();

        JRadioButton radAES = new JRadioButton("AES");
        radAES.setHorizontalAlignment(SwingConstants.CENTER);
        radAES.setOpaque(true);
		radAES.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radAES.setBounds(232, 11, 65, 23);
		contentPane.add(radAES);
        btnGroup.add(radAES);
		
		JRadioButton radRSA = new JRadioButton("RSA");
		radRSA.setHorizontalAlignment(SwingConstants.CENTER);
        radRSA.setOpaque(true);
		radRSA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radRSA.setBounds(298, 11, 70, 23);
		contentPane.add(radRSA);
        btnGroup.add(radRSA);

        if (defMethod == "AES") {
            radAES.setSelected(true);
        } else if (defMethod == "RSA") {
            radRSA.setSelected(true);
        }

        ActionListener radListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JRadioButton aButton = (JRadioButton) actionEvent.getSource();
                setText(aButton.getText());
            }
        };
        
        radAES.addActionListener(radListener);
        radRSA.addActionListener(radListener);

        txtWords = new JTextArea();
        setText("AES"); // default
        txtWords.setLineWrap(true);
        txtWords.setWrapStyleWord(true);
        txtWords.setOpaque(false);
        txtWords.setBounds(12,50,500,500);
        contentPane.add(txtWords);

    }

    public void setText(String method) {
        if (method == "AES") {
            txtWords.setText("AES stands for Advanced Encryption Standard and is a type of encryption that uses the same private key for encryption and decryption. This is known as symmetric encryption. \n\nChoose this method if you would like to have a single key for both encryption and decryption as well as set your own key if you would like.\n\nTo use AES in this App, perform the following steps: \n1) Select the AES button.\n2) Click generate key.\n3) Type in a key.\n4) Put that same key in the box labeled 'Manually Paste Key'\n5) Type the message that you would like encrypted/decrypted into the input box.\n6) Click either encrypt or decrypt.");
        } else if (method == "RSA") {
            txtWords.setText("RSA stands for Rivest-Shamir-Adleman and is an encryption method that uses both a public and a private key. This is known as asymmetric encryption.\n\nChoose this method if you would like to give multiple people your public key to encrypt their messages but only allow yourself to decrypt with your private key.\n\nTo use RSA in this App, perform the following steps: \n1) Select the RSA button.\n2) Click generate key.\n3) Two files will be added to your desktop; one containing your private key and another containing your public key.\n4) Click 'Select File' and upload the file with your public key if you want to encrypt or private key if you want to decrypt.\n5) Input the text into the left box.\n6) Click either encrypt or decrypt.");
        }
        txtWords.setText(txtWords.getText() + "\n\nIf you would like to email the output, select File->Email. This will open a window where you can put the recipient and subject as well as view a preview of the message to be emailed. Once you click 'Create Draft', a draft email will be created in your operating system's default mail application. Don't worry, this is your chance to review and finalize the email before sending.");
    } 


}
