import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.KeyFactory;
import java.security.KeyGenerator;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
public class AlgorithmAES {

    private static SecretKeySpec secretKey;
    private static byte[] key;
    
    //set key
    public void setKey(String myKey) {
        try {
        key = myKey.getBytes("UTF-8");
            
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // orig, new length
            secretKey = new SecretKeySpec(key, "AES");


        } catch (NoSuchAlgorithmException e) { }
        catch (UnsupportedEncodingException e) { }
    }
    //encrypt
    public String encrypt(String strToEnc, String sec) {
        try {
            setKey(sec);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEnc.getBytes("UTF-8")));
        } catch (Exception e) {
            
        }
        return null;
    }
    //decrypt
    public String decrypt(String strToDec, String sec) {
        try {
            setKey(sec);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDec)));
        } catch (Exception e) {
            
        }
        return null;
    }

    public void publicKeyToFile(String key){
		try {
			String userDesktop = System.getProperty("user.home") + "/Desktop";
			File file = new File(userDesktop, "publickey.txt");
		
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
    public static void main(String[] args) throws Exception {
        final String secretKey = "KeepONMovingBuddy";
        String originalString = "Secret Site: - http://10.67.84.101/";

        //Enc
        String encSite = encrypt(originalString, secretKey);
        
        //Dec
        String decSite = decrypt(encSite, secretKey);
        //DisplayAll
        System.out.println("Original: " + originalString);
        System.out.println("Encrypted text: " + encSite);
        System.out.println("Decrypted text: " + decSite);

    }
}