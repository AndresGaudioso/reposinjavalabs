package alphabetic;

import java.text.Collator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MainAlphabet {
	
	public static void main(String...args) {
		Collator collation = Collator.getInstance();
		SortedSet<String> ordenadosSet= new TreeSet<>(collation); 
		ordenadosSet.add("Andres");
		ordenadosSet.add("Álvaro");
		ordenadosSet.add("Beatriz");
		ordenadosSet.add("Jacobo");
		ordenadosSet.add("andres");
		ordenadosSet.add("álvaro");
		ordenadosSet.add("beatriz");
		ordenadosSet.add("jacobo");
		
		for( String current:ordenadosSet) {
			System.out.println(current);
		}
		
	}

}
