package io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class Failfastandfailsafe {

	public static void main(String[] args) {
		//fail safe
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		
		for(int i : list) {
			System.out.println(i);
			list.add(11);
		}

	}

}
