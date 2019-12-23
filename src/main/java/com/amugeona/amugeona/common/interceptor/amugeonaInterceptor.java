package com.amugeona.amugeona.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.amugeona.amugeona.common.service.LogService;
import com.amugeona.amugeona.menupick.common.Util;

public class amugeonaInterceptor implements HandlerInterceptor{
	private static final Logger logger = LoggerFactory.getLogger(amugeonaInterceptor.class);
	
	@Resource(name = "logService")
	private LogService logService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Device device = DeviceUtils.getCurrentDevice(request);
		String cookie = Util.cookieCheck(request, response);
		String path = request.getRequestURI();
		String logIdx = Util.getLogIdx();
		String type1 = "LG001";
		
		logger.info("==== cookie : "+cookie + "=====");
		logger.info("==== path : "+path + "=====");
		logger.info("==== logIdx : "+logIdx + "=====");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logIdx",logIdx);
		map.put("logType1",type1);
		map.put("logType2","");
		map.put("logContent",path);
		map.put("cookieId",cookie);

		logService.InsertLog(map);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		String value = "";
		
		if(!Util.isBlank(userAgent) && userAgent.indexOf("MOBILE") > -1) {
			value = "M";
		}else {
			value = "W";
		}
		if(modelAndView != null) {
			modelAndView.addObject("device", value);
		}
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
