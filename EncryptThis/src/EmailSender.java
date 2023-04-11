import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.PublicKey;

public class EmailSender extends JFrame {

	private JPanel contentPane;
	private JTextField tfTo;
	private JTextField tfFrom;
	private JTextField tfSubject;
	private EmailSender frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailSender frame = new EmailSender("","");
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
	public EmailSender(final String outputBox, final String publicKey) {
		setTitle("Send Email");
		setResizable(false);
		setBounds(100, 100, 501, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTo = new JLabel("To:");
		lblTo.setBounds(10, 13, 26, 14);
		contentPane.add(lblTo);
		
		tfTo = new JTextField();
		tfTo.setBounds(69, 10, 347, 20);
		contentPane.add(tfTo);
		tfTo.setColumns(10);


		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setBounds(10,41,46,14);
		contentPane.add(lblSubject);
		
		tfSubject = new JTextField();
		tfSubject.setBounds(69, 38, 347, 20);
		contentPane.add(tfSubject);
		tfSubject.setColumns(10);

		JLabel lblReminder = new JLabel("Don't forget to provide your recipient with your public key!");
		lblReminder.setBounds(10, 66, getPreferredSize().width, 14);
		contentPane.add(lblReminder);
		
		JButton btnSend = new JButton("Create Draft");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Boolean hasKey = true;
					if (publicKey == "No such file") {
						hasKey = false;
					}
					sendEmail(tfTo.getText(), tfSubject.getText(), hasKey, publicKey, outputBox);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSend.setBounds(182, 75, 115, 23);
		btnSend.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnSend);

		JLabel lblPreview = new JLabel("Preview");
		lblPreview.setBounds(215, 122, 115, 23);
		contentPane.add(lblPreview);
		
		JTextArea txtPreview = new JTextArea("Preview");
		txtPreview.setLineWrap(true);
		txtPreview.setOpaque(false);
		//lblPreview.setHorizontalAlignment(SwingConstants.CENTER);
		txtPreview.setBounds(10, 157, 465, 232);
		// if nothing was in the box
		if (outputBox == null) {
			txtPreview.setText("No preview available.");
		} else {
			if (publicKey != "No such file") {
				txtPreview.setText("Public Key: " + publicKey + "\n\n\n" + outputBox);
			} else {
				txtPreview.setText(outputBox);
			}
		}
		contentPane.add(txtPreview);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void sendEmail(String to, String subject, Boolean hasKey, String publicKey, String msgText) throws IOException {
		
		// remove spaces
		to = to.replaceAll(" ", "");
		subject = subject.replaceAll(" ", "%20");
		msgText = msgText.replaceAll(" ", "%20");
		
		 Desktop desktop;
		 if (Desktop.isDesktopSupported() 
		   && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
		  URI mailto;
		try {
			String strMailTo;
			if (hasKey) {
				strMailTo = "mailto:" + to + "?subject=" + subject + "&body=Public%20Key:%20" + publicKey + "%0A%0A" + msgText;
			} else {
				strMailTo = "mailto:" + to + "?subject=" + subject + "&body=" + msgText;
			}
			mailto = new URI(strMailTo);
			desktop.mail(mailto);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		 } else {
		  throw new RuntimeException("desktop doesn't support mailto");
		 }
		
	}
}
