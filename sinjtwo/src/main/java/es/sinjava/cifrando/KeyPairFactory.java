package es.sinjava.cifrando;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

// Esta clase se encarga de generar un par de clave publicas y privada
// Y las vuelca en dos archivos

public class KeyPairFactory {

	public static void main(String... args) throws NoSuchAlgorithmException, IOException {
		System.out.println("Generamos el par de claves");
		generateKeys();
		System.out.println("Generadas con éxito");
	}

	private static void generateKeys() throws IOException, NoSuchAlgorithmException {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		// Volcamos las dos claves
		saveKey(publicKey, publicKey.getAlgorithm() + ".pub");
		saveKey(privateKey, privateKey.getAlgorithm() + ".pri");

	}

	private static void saveKey(Key key, String fileName) throws IOException {
		byte[] publicKeyBytes = key.getEncoded();
		Files.write(new File(fileName).toPath(), publicKeyBytes, StandardOpenOption.CREATE);
	}

}
