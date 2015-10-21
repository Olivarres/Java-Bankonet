package com.bankonet.lib;

import java.util.Scanner;

public class ConsoleReader {
	
	private static ConsoleReader INSTANCE = null;
	private Scanner scanner = new Scanner(System.in);
	private ConsoleReader(){}
	
	public static ConsoleReader getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new ConsoleReader();	
		}
		return INSTANCE;
	}
	
	public String readLine(String msg) {
		System.out.println(msg);
		return scanner.next();
	}
	
	public Integer readInt(String msg) {
		return scanner.nextInt();
	}
}
