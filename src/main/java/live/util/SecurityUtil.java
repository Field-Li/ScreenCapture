//package live.util;
//
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.IOException;
//import java.security.Key;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.security.Security;
//import java.security.spec.AlgorithmParameterSpec;
//
//public class SecurityUtil {
//
//	public static String hash(byte[] srcData, String algorithmName) {
//	    MessageDigest messageDigest;
//		try {
//			messageDigest = MessageDigest.getInstance(algorithmName);
//			messageDigest.update( srcData);
//		    byte[] text = messageDigest.digest();
//			return byte2hex(text);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public static String base64Encode(byte[] srcData){
//		String encodedStr = new sun.misc.BASE64Encoder().encode( srcData );
//		return encodedStr;
//	}
//
//	public static byte[] base64Decode(String encodedStr) {
//		try {
//			byte[] text = new sun.misc.BASE64Decoder().decodeBuffer(encodedStr);
//			return text;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public static byte[] aesEncode(byte[] srcData, byte[] keyData, byte[] ivData) {
//		try{
//			Security.addProvider(new BouncyCastleProvider());
//			String algorithmName = "AES/CBC/PKCS7Padding";
//		    Cipher cipher = Cipher.getInstance(algorithmName);
//		    Key key = new SecretKeySpec(keyData, algorithmName);
//		    AlgorithmParameterSpec iv = new IvParameterSpec(ivData);
//
//		    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
//		    byte[] text = cipher.doFinal(srcData);
//		    return text;
//		}catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public static byte[] aesDecode(byte[] encodedStr, byte[] keyData, byte[] ivData) {
//		try{
//			Security.addProvider(new BouncyCastleProvider());
//			String algorithmName = "AES/CBC/PKCS7Padding";
//		    Cipher cipher = Cipher.getInstance(algorithmName);
//		    Key key = new SecretKeySpec(keyData, algorithmName);
//		    AlgorithmParameterSpec iv = new IvParameterSpec(ivData);
//
//		    cipher.init(Cipher.DECRYPT_MODE, key, iv);
//		    byte[] text = cipher.doFinal( encodedStr );
//		    return text;
//		}catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public static String byte2hex(byte[] b) {
//        String hs = "";
//        String stmp = "";
//        for (int n = 0; n < b.length; n++) {
//            stmp = (Integer.toHexString(b[n] & 0XFF));
//            if (stmp.length() == 1)
//            	hs = hs + "0" + stmp;
//            else
//            	hs = hs + stmp;
//        }
//        return hs;
//    }
//
//    public static byte[] hex2byte(byte[] b) {
//        if ((b.length % 2) != 0) throw new IllegalArgumentException("argument error!");
//        byte[] b2 = new byte[b.length / 2];
//        for (int n = 0; n < b.length; n += 2) {
//            String item = new String(b, n, 2);
//            b2[n / 2] = (byte) Integer.parseInt(item, 16);
//        }
//        return b2;
//    }
//}
