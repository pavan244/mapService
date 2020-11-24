package com.map.exceptions;

public class TextFormatException extends Exception {
	
	public void printException(String line)
	{
		System.out.println(line+" String should be in format arg1,arg2");
	}	
	
}
