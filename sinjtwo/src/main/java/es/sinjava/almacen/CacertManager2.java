package es.sinjava.almacen;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Enumeration;

public class CacertManager2 {

	public static void main(String... args) throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, InvalidKeySpecException, UnrecoverableKeyException {

		// Le indicamos que tipo almacen
		KeyStore ks = KeyStore.getInstance("Windows-MY");

		char[] password = "changeit".toCharArray();

		ks.load(null, null);

		// hemos cargado el almacen de certificados de la máquina virtual de java

		System.out.println("Mostrando los almacences de certificados");
		for (Enumeration<String> alias = ks.aliases(); alias.hasMoreElements();) {
			String aliasCurrent = alias.nextElement();
			System.out.println("Elemento recorrido : " + aliasCurrent);
			// Información de cada uno de los certificados:
			Certificate[] chain = ks.getCertificateChain(aliasCurrent);
			System.out.println("chain :" + chain.length);
		}

	}

}
