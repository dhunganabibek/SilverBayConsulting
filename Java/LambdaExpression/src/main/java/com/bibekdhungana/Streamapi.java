package com.bibekdhungana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Streamapi {

	public static void main(String[] args) {
		List<String> mylist  = new ArrayList<String>(Arrays.asList("Bibek","Bob","Alex","Dhan"));
		
		System.out.println(mylist);
		//mylist.removeIf(s -> s.startsWith("B"));
		System.out.println(mylist);
		
		//stream api -- it helps tp create pipeline and support lazy loading of data.
		
//		mylist.stream()
//		.map( (String s) ->  "Mr. " + s.toUpperCase())
//		.forEach((String s) -> System.out.println(s));
		
		mylist.stream()
		.map(String::toUpperCase)
		.forEach(System.out::println);
		
		

	}

}
