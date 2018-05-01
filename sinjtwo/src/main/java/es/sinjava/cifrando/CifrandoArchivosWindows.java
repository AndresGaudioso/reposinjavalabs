package es.sinjava.cifrando;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.stream.Stream;

public class CifrandoArchivosWindows {

	public static void main(String[] args) throws Exception {

		String textIn = " Prueba de concepto de cifrado elegante almacen Cacert";

		// Se encripta

		CifradorDescifrador cifrador = CifradorDescifrador.getInstanceRsa();
		String alias = "GAUDIOSO SIMON ANDRES JOAQUIN - 25446123G's FNMT-RCM ID";
		String password = "changeit";

		byte[] cifrados = cifrador.encriptCacertW(alias, password.toCharArray(), textIn.getBytes());

		// Convertimos los bytes en algo que podamos escribir como texto
		Encoder encoder = Base64.getEncoder();
		String byteAsString = encoder.encodeToString(cifrados);

		// Este método escribe un archivo de texto con los argumentos que le paso
		File filetext = createFile(byteAsString);

		// Leemos el archivo, Que debería estar encriptado
		printFile(filetext);

		String content = readFile(filetext);

		// es un texto que esconde un binario
		Decoder decoder = Base64.getDecoder();

		// Leemos el archivo como texto y lo convertimos en bytes
		byte[] leidos = decoder.decode(content);

		// Se desencripta
		byte[] bytesDesencriptados = cifrador.deEncriptCacertW(alias, password.toCharArray(), leidos);

		String textoDesencripado = new String(bytesDesencriptados);

		// Se escribe el texto desencriptado
		System.out.println(textoDesencripado);

	}

	private static void printFile(File filetext) throws IOException {
		Stream<String> lambdaStream = Files.lines(filetext.toPath());
		lambdaStream.forEach(s -> System.out.println(s));
		lambdaStream.close();
	}

	private static String readFile(File filetext) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		Stream<String> lambdaStream = Files.lines(filetext.toPath());
		lambdaStream.forEach(s -> stringBuilder.append(s));
		lambdaStream.close();
		return stringBuilder.toString();
	}

	private static File createFile(String... strings) throws IOException {
		File filetext = File.createTempFile("sinjava", ".txt");
		// creo una colección de lineas que voy a escribir en él.

		List<String> lineas = Arrays.asList(strings);
		FileWriter writer = new FileWriter(filetext);
		for (String linea : lineas) {
			writer.append(linea + "\n");
		}
		writer.close();
		return filetext;
	}

}
