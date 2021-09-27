package com.elenine.onelibrary.util;

import java.util.Random;

public class RandomPassword {
	
	public static String RandomPasswordMethod() {
		Random dice = new Random();
		String password = Integer.toString(dice.nextInt(10000));
		return password;
	}

}
