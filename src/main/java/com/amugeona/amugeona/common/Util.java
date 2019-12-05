package com.amugeona.amugeona.common;

public class Util {
	public static boolean isBlank(String arg) {
		if(arg == null || "".equals(arg)) {
			return true;
		}
		return false;
	}

}
