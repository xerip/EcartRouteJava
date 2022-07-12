import java.util.*;

import pack.perso.Salarie;

public class HelloWorld {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		String str ="hello";
		System.out.println(str.toUpperCase());
		
		Test0.printtest0(Test0.test0_val);
		
		Salarie s = new Salarie();
		s.Say_complete_hello();
		
		List<Integer> myList = new ArrayList<Integer>();
		myList.add(7);
		myList.add(5); //-> [7,5]
		myList.add(2);
		
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		map.put(str, null);
		
		
		int j=1;
		int i = (j==0) ? 1 : 5;
		System.out.println(i);
	}
}
