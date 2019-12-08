package com.amugeona.amugeona.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.amugeona.amugeona.admin.service.AdminService;
import com.amugeona.amugeona.common.UserSha256;
import com.amugeona.amugeona.common.Util;
import com.amugeona.amugeona.common.dao.AbstractDAO;
import com.amugeona.amugeona.service.AmugeonaService;

@Controller
public class AdminController {

	@Resource(name = "adminService")
	private AdminService adminService;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "/admin/loginPage.do")
	public ModelAndView loginPage(Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin login =====");
		System.out.println(UserSha256.encrypt("khk0505263"));
		ModelAndView mv = new ModelAndView("/admin/loginPage");
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/menu.do")
	public ModelAndView loginPage(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin login =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("/admin/loginPage");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/admin/menu");
		return mv;
	}
	
	@RequestMapping(value = "/admin/typeManage.do")
	public ModelAndView typeManage(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin typeManage =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("/admin/loginPage");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/admin/type/manage");
		return mv;
	}
	
	@RequestMapping(value = "/admin/typeSelect.do")
	public ModelAndView typeSelect(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin typeManage =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("/admin/login");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/admin/type/select");
		return mv;
	}
	
	@RequestMapping(value = "/admin/foodManage.do")
	public ModelAndView foodManage(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin foodManage =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("/admin/login");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/admin/food/manage");
		return mv;
	}
	
	@RequestMapping(value = "/admin/foodSelect.do")
	public ModelAndView foodSelect(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin foodManage =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("/admin/login");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/admin/food/select");
		return mv;
	}
	
	@RequestMapping(value = "/admin/login.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login( HttpServletRequest request, @RequestBody Map<Object, Object> paramMap, ModelMap model) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		if(Util.isBlank((String)paramMap.get("id")) || Util.isBlank((String)paramMap.get("pw"))) { 
			resultMap.put("result",false); 
			return resultMap;
		}
		
		resultMap.put("id", (String)paramMap.get("id"));
		resultMap.put("pw", UserSha256.encrypt((String)paramMap.get("pw")));
		Map<String, Object> result = adminService.login(resultMap);
		
		if(result == null) {
			resultMap.put("result",false); 
			return resultMap;
		}
		
		request.getSession().setAttribute("loginYN", "Y");
		resultMap.put("result",true); 
		return resultMap; // 화면으로 던져준다!!
		 
	}
	
	public boolean isSessionCheck(HttpServletRequest request) {
		
		String flag = (String)request.getSession().getAttribute("loginYN");
		if("Y".equals(flag)) {
			return false;
		}else {
			return true;
		}
	}

}
