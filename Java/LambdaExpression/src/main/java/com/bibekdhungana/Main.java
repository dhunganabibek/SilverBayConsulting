package com.bibekdhungana;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) {
		Function<String,Integer> myfunction = (String s) -> s.length();
		
		System.out.println(myfunction.apply("Hello"));
		
		Predicate<String> myPredicate = (String s) -> s.startsWith("B");
		myPredicate.test("Bibek");
		
		Consumer<String> myConsumer = (String s) -> System.out.println(s);
		myConsumer.accept("Bibek");
		
		//supplier

	}

}
