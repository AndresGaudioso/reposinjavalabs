package es.sinjava.ofuscator;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Ofuscator {

	// El codido tonto de la semana
	public static void main(String... args) {
		String semilla = "4598Amena";

		Encoder encoder = Base64.getEncoder();
		Decoder decoder = Base64.getDecoder();

		byte[] byteValue = semilla.getBytes();

		byte[] original = encoder.encode(byteValue);

		String byteAsString = new String(original);

		System.out.println("Sin Ofuscar " + byteAsString);

		// Leemos el archivo como texto y lo convertimos en bytes
		byte[] leidos = decoder.decode(byteAsString.getBytes());

		String salida = new String(leidos);
		System.out.println(salida);

		// Ahora invertimos el string base64

		StringBuilder stringBuilder = new StringBuilder(byteAsString);
		String resultado = stringBuilder.reverse().toString();
		int equalsPosition = resultado.lastIndexOf('=');
		resultado = (equalsPosition == -1) ? resultado
				: (equalsPosition == 0) ? resultado.substring(1).concat("=") : resultado.substring(2).concat("==");
		System.out.println(resultado);
		// Ofuscado
		byte[] ofuscado = decoder.decode(resultado.getBytes());

		salida = new String(ofuscado);
		System.out.println(salida);

	}

}
