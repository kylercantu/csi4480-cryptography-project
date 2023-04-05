import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

/*  

Source Reference to implement RSA Algorithm using Java's Libraries:
https://www.baeldung.com/java-rsa

*/

public class AlgorithmRSA {
	
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	
	public AlgorithmRSA() {
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
			keyGen.initialize(2048); 
			KeyPair keyPair = keyGen.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public void generateKeys() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); //Creates KeyPairGenerator object and uses "RSA" algorithm
			keyGen.initialize(2048); //Uses 2048 bits to represent key sizes (617 digits total) --> 2^2048 different combinations
			KeyPair keyPair = keyGen.generateKeyPair();
			
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
			
			publicKeyToFile(publicKey);
			privateKeyToFile(privateKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}//End generateKeys
	
//	public void initKeys() {
//		KeyPairGenerator keyGen;
//		try {
//			keyGen = KeyPairGenerator.getInstance("RSA");
//			keyGen.initialize(2048); 
//			KeyPair keyPair = keyGen.generateKeyPair();
//			publicKey = keyPair.getPublic();
//			privateKey = keyPair.getPrivate();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}//End initKeys
	
	public String encryptMsg(JTextArea textArea) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] msgToByte = textArea.getText().getBytes(); //gets the bytes from the JTextArea and stores into Array
		Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); //The method used to "hide" the words/text
		encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey); //Initializes the cipher using the public key
		byte[] encryptedBytes = encryptCipher.doFinal(msgToByte); //Encrypts the message that we converted to bytes
		String encodedMsg = Base64.getEncoder().encodeToString(encryptedBytes); //Converts the message to Base 64 alphabets which are ASCII characters
		return encodedMsg; //Returns the encoded version of the encrypted message
	}//End encryptMsg
	
	
	public String decryptMsg(JTextArea textArea) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		byte[] msgToByte = textArea.getText().getBytes();
		byte[] decodeMsg = Base64.getDecoder().decode(msgToByte);
		Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedBytes = decryptCipher.doFinal(decodeMsg);
		String decryptedMsg = new String(decryptedBytes, StandardCharsets.UTF_8);
		return decryptedMsg;
	}//End decryptMsg
	
	
	
	public void publicKeyToFile(PublicKey key){
		try {
			String userDesktop = System.getProperty("user.home") + "/Desktop";
			File file = new File(userDesktop, "RSApublickey.txt");
		
			if(!file.exists()) {
				file.createNewFile();
			}
			
			PrintWriter pw = new PrintWriter(file);
			String encodeKey = Base64.getEncoder().encodeToString(key.getEncoded());
			pw.print(encodeKey);
			pw.close();
			
		}catch(IOException e) {
			System.out.println("Error with File");
		}
	}//End publicKeyToFile
	
	
	public void privateKeyToFile(PrivateKey key) {
		try {
			String userDesktop = System.getProperty("user.home") + "/Desktop";
			File file = new File(userDesktop, "RSAprivatekey.txt");
		
			if(!file.exists()) {
				file.createNewFile();
			}
			PrintWriter pw = new PrintWriter(file);
			String encodeKey = Base64.getEncoder().encodeToString(key.getEncoded());
			pw.print(encodeKey);
			pw.close();
			
		}catch(IOException e) {
			System.out.println("Error with File");
		}
	}//End privateKeyToFile
	
	
	public String getKeyFromFile() {
		String s;

		try {
			JFileChooser fc = new JFileChooser();
			int userChoice = fc.showOpenDialog(null);
			File f = new File(fc.getSelectedFile().getAbsolutePath());
			
			if(userChoice == JFileChooser.APPROVE_OPTION) {
				BufferedReader reader = new BufferedReader(new FileReader(f));
				return s = reader.readLine();
			} else {
				return null;
			}
		} catch (Exception ignore) {
			return null;
		}
	}//End getKeyFromFile
	

	
	

}//End RSA class
