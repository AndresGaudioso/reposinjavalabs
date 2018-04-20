package es.sinjava;

import java.text.Collator;
import java.util.SortedSet;
import java.util.TreeSet;

public class App {
	public static void main(String... args) {

		SortedSet<String> noOrdenadosSet = new TreeSet<>();
		noOrdenadosSet.add("Andres");
		noOrdenadosSet.add("Álvaro");
		noOrdenadosSet.add("Beatriz");
		noOrdenadosSet.add("Jacobo");
		noOrdenadosSet.add("andres");
		noOrdenadosSet.add("álvaro");
		noOrdenadosSet.add("beatriz");
		noOrdenadosSet.add("jacobo");

		noOrdenadosSet.forEach(it -> System.out.println(it));

		System.out.println("________________");

		SortedSet<String> ordenadosSet = new TreeSet<>(Collator.getInstance());
		ordenadosSet.add("Andres");
		ordenadosSet.add("Álvaro");
		ordenadosSet.add("Beatriz");
		ordenadosSet.add("Jacobo");
		ordenadosSet.add("andres");
		ordenadosSet.add("álvaro");
		ordenadosSet.add("beatriz");
		ordenadosSet.add("jacobo");
		ordenadosSet.forEach(it -> System.out.println(it));

	}
}
