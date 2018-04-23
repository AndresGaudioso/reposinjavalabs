package es.sinjava.cifrando;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class CifradorDescifrador {

	private static Cipher cipherRsa;
	private static KeyFactory keyFactory;
	private static KeyStore ks;

	public static CifradorDescifrador getInstanceRsa() {
		try {
			cipherRsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (GeneralSecurityException e) {
			System.out.println("Error: " + e);
		}
		return new CifradorDescifrador();
	}

	public byte[] encriptCacert(String alias, char[] password, byte[] textAsByte)
			throws IOException, GeneralSecurityException {
		PublicKey publicKey = loadPublicKeyFromCacert(alias, password);
		cipherRsa.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encriptado = cipherRsa.doFinal(textAsByte);

		return encriptado;
	}

	public byte[] deEncriptCacert(String alias, char[] password, byte[] textAsByte)
			throws IOException, GeneralSecurityException {
		PrivateKey privateKey = loadPrivateKeyFromCacert(alias, password);
		cipherRsa.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytesDesencriptados = cipherRsa.doFinal(textAsByte);

		return bytesDesencriptados;
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
		byte[] content = Files.readAllBytes(new File(fileName).toPath());
		KeySpec keySpec = new X509EncodedKeySpec(content);
		PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
		return keyFromBytes;
	}

	private static PrivateKey loadPrivateKey(String fileName) throws IOException, GeneralSecurityException {
		byte[] content = Files.readAllBytes(new File(fileName).toPath());
		KeySpec keySpec = new PKCS8EncodedKeySpec(content);
		PrivateKey keyFromBytes = keyFactory.generatePrivate(keySpec);
		return keyFromBytes;
	}

	private PublicKey loadPublicKeyFromCacert(String alias, char[] password)
			throws GeneralSecurityException, IOException {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		String filename = System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
		InputStream inputStream = new FileInputStream(filename);
		ks.load(inputStream, password);
		Certificate certificado = ks.getCertificate(alias);
		inputStream.close();
		return certificado.getPublicKey();
	}

	private PrivateKey loadPrivateKeyFromCacert(String alias, char[] password)
			throws IOException, GeneralSecurityException {
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		String filename = System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
		InputStream inputStream = new FileInputStream(filename);
		ks.load(inputStream, password);
		PrivateKey key = (RSAPrivateCrtKey) ks.getKey("sinjavalbs", password);
		inputStream.close();
		return key;
	}

}
