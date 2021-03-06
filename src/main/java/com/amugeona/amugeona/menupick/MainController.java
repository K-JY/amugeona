package com.amugeona.amugeona.menupick;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/")
	public ModelAndView start(Map<String, Object> commandMap) throws Exception {
		logger.info("==== main =====");
		ModelAndView mv = new ModelAndView("main");
		return mv;
	}
	
	@RequestMapping(value = "/main.do")
	public ModelAndView main(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== main =====");
		Device device = DeviceUtils.getCurrentDevice(request);
		logger.info("mobile info : "+device.isMobile());
		ModelAndView mv = new ModelAndView("main");
		return mv;
	}
	
}
