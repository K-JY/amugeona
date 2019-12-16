package com.amugeona.amugeona.menupick.common;

public class Util {
	public static boolean isBlank(String arg) {
		if(arg == null || "".equals(arg)) {
			return true;
		}
		return false;
	}

}
