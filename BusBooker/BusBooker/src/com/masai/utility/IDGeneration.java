package com.masai.utility;

public class IDGeneration {
	public static int generateID() {
		return (int) (Math.random() * 1000000);
	}
}
