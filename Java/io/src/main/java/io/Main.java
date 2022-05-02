package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<Character, Integer> myMap = new LinkedHashMap<>();
		try {
			try (FileInputStream fis = new FileInputStream("src/main/resources/if.txt")) {
				byte[] readAllBytes = fis.readAllBytes();
				String context = new String(readAllBytes);
				
				for(int i = 0; i < context.length(); i++) {
					Character c = context.charAt(i);
					if (!Character.isAlphabetic(c)) {
						continue;
					}
					if(myMap.containsKey(c)) {
						int occurence = myMap.get(c);
						myMap.put(c, occurence + 1);
					}
					else {
						myMap.put(c,1);
					}
				}
			}
			for(Map.Entry<Character, Integer> entry: myMap.entrySet()) {
				System.out.println(entry.getKey() + "-------" + entry.getValue());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
