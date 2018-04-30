package es.sinjava.almacen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Enumeration;

public class CacertManager {

	public static void main(String... args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, InvalidKeySpecException, UnrecoverableKeyException {

		// Le indicamos que tipo almacen
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		String filename = System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
		System.out.println(filename);
		InputStream inputStream = new FileInputStream(filename);
		char[] password = "changeit".toCharArray();
		ks.load(inputStream, password);

		// hemos cargado el almacen de certificados de la máquina virtual de java

		System.out.println("Mostrando los almacences de certificados");
		for (Enumeration<String> alias = ks.aliases(); alias.hasMoreElements();) {
			String aliasCurrent = alias.nextElement();
			System.out.println("Elemento recorrido : " + aliasCurrent);
			// Información de cada uno de los certificados:
			if ("sinjavalbs".equals(aliasCurrent)) {
				Certificate certificado = ks.getCertificate(aliasCurrent);

				PrivateKey key = (RSAPrivateCrtKey) ks.getKey("sinjavalbs", password);

				// PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(certificado.getEncoded());
				// KeyFactory kf = KeyFactory.getInstance("RSA");
				// PrivateKey privateKey = kf.generatePrivate(spec);
				//
				System.out.println(certificado.getType() + " - " + key.getClass());

			}
		}

	}

}
