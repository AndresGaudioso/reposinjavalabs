package es.sinjava.cifrando;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class CifradorDescifrador {

	private static Cipher cipherRsa;

	public static CifradorDescifrador getInstanceRsa() {
		try {
			cipherRsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} catch (GeneralSecurityException e) {
			// ESto no debería ocurrir
		}
		return new CifradorDescifrador();
	}

	static {

	}

	public byte[] encript(String publicKeyFile, byte[] textAsByte) throws IOException, GeneralSecurityException {

		PublicKey publicKey = loadPublicKey(publicKeyFile);
		cipherRsa.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encriptado = cipherRsa.doFinal(textAsByte);

		return encriptado;
	}

	public byte[] deEncript(String privaKeyFile, byte[] textAsByte) throws IOException, GeneralSecurityException {
		PrivateKey privateKey = loadPrivateKey(privaKeyFile);
		cipherRsa.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytesDesencriptados = cipherRsa.doFinal(textAsByte);

		return bytesDesencriptados;
	}

	private static PublicKey loadPublicKey(String fileName) throws IOException, GeneralSecurityException {
		FileInputStream fis = new FileInputStream(fileName);
		int numBtyes = fis.available();
		byte[] bytes = new byte[numBtyes];
		fis.read(bytes);
		fis.close();

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		KeySpec keySpec = new X509EncodedKeySpec(bytes);
		PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
		return keyFromBytes;
	}

	private static PrivateKey loadPrivateKey(String fileName) throws IOException, GeneralSecurityException {
		FileInputStream fis = new FileInputStream(fileName);
		int numBtyes = fis.available();
		byte[] bytes = new byte[numBtyes];
		fis.read(bytes);
		fis.close();

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
		PrivateKey keyFromBytes = keyFactory.generatePrivate(keySpec);
		return keyFromBytes;
	}

}