import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
					EmailSender frame = new EmailSender("");
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
	public EmailSender(final String outputBox) {
		setTitle("Send Email");
		setResizable(false);
		setBounds(100, 100, 501, 439);
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
		
		JLabel lblFrom = new JLabel("From:");
		lblFrom.setBounds(10, 41, 46, 14);
		contentPane.add(lblFrom);
		
		tfFrom = new JTextField();
		tfFrom.setBounds(69, 38, 347, 20);
		contentPane.add(tfFrom);
		tfFrom.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setBounds(10, 66, 46, 14);
		contentPane.add(lblSubject);
		
		tfSubject = new JTextField();
		tfSubject.setBounds(69, 63, 347, 20);
		contentPane.add(tfSubject);
		tfSubject.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sendEmail(tfTo.getText(), tfFrom.getText(), tfSubject.getText(), outputBox);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSend.setBounds(194, 123, 89, 23);
		btnSend.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnSend);
		
		JLabel lblPreview = new JLabel("Preview");
		lblPreview.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreview.setBounds(10, 157, 465, 232);
		// if nothing was in the box
		if (outputBox == null) {
			lblPreview.setText("");
		} else {
			lblPreview.setText(outputBox);
		}
		contentPane.add(lblPreview);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public void sendEmail(String to, String from, String subject, String msgText) throws IOException {
		
		// remove spaces
		to = to.replaceAll(" ", "");
		from = from.replaceAll(" ", "");
		subject = subject.replaceAll(" ", "%20");
		msgText = msgText.replaceAll(" ", "%20");
		
		 Desktop desktop;
		 if (Desktop.isDesktopSupported() 
		   && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
		  URI mailto;
		try {
			String strMailto = "mailto:" + to + "?subject=" + subject + "&body=From:" + from + "%20%0A%0A" + msgText;
			mailto = new URI(strMailto);
			desktop.mail(mailto);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 } else {
		  // TODO fallback to some Runtime.exec(..) voodoo?
		  throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
		 }
		
	}
}
