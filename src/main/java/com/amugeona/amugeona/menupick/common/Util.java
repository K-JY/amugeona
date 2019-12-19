package com.amugeona.amugeona.menupick.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util {
	public static boolean isBlank(String arg) {
		if(arg == null || "".equals(arg)) {
			return true;
		}
		return false;
	}
	
	public static String getLogIdx() {
		Random random = new Random();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
		Calendar time = Calendar.getInstance();
		String logIdx = format1.format(time.getTime())+Integer.toString(random.nextInt(99999));
		
		return logIdx;
	}
	
	public static String cookieCheck(HttpServletRequest request, HttpServletResponse response){
		String cookieId = "";
		Cookie[] getCookie = request.getCookies();
		if(getCookie != null){
			for(int i=0; i<getCookie.length; i++){
				Cookie c = getCookie[i];
				String name = c.getName(); // 쿠키 이름 가져오기
				String value = c.getValue(); // 쿠키 값 가져오기
				
				if("cookieId".equals(name)){
					cookieId = value;
				}

			}
		}
		
		if("".equals(cookieId)) {
			cookieId = getLogIdx();
			Cookie setCookie = new Cookie("cookieId", cookieId); // 쿠키 이름을 name으로 생성
			
			setCookie.setMaxAge(60*60*24*365); // 기간을 하루로 지정

			response.addCookie(setCookie);
		}
	
		return cookieId;

	}
}
