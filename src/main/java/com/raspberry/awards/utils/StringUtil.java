package com.raspberry.awards.utils;

public class StringUtil {

	public static Boolean getBooleanByString(String bool) {
		if(bool.equals("yes"))
			return true;
		if(bool == null || bool.isEmpty())
			return false;
		
		return false;
	}
	
	public static String[] split(String value, String separator) {
		value = value.replace(", and ", ",");
		value = value.replace(" and ", ",");
		
		return value.split(separator);
	}
}
