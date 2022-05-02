package com.bibekdhungana;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {

	public static void main(String[] args) {
		Map<String,Integer> myMap = new HashMap<String, Integer>();
		//linked hashmap -- preserve order
		//treemap -- some sort of ordering
		
		myMap.put("Bibek", 1);
		myMap.put("Bhaskar", 2);
		myMap.put("Samrat", 3);
		myMap.put("Bibek", 100);
		
		System.out.println(myMap.get("Bibek"));

		System.out.println(myMap.containsKey("Bhaskar"));
		
		
		//how to fetch all the data
		for(Map.Entry<String, Integer> keyValue: myMap.entrySet()) {
			System.out.println(keyValue.getKey() + "     " + keyValue.getValue());
		}

	}

}
