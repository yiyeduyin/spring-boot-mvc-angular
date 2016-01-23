package com.cmiracle.util;

public class Util {
	
	public static boolean isNotNull(String s){
		return (s == null || s.trim().length() < 1) ? false : true;
	}
	
	public static boolean isNotNull(Integer s){
		return s == null? false:true;
	}

}
