package com.amugeona.amugeona.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.amugeona.amugeona.admin.service.AdminService;
import com.amugeona.amugeona.menupick.common.UserSha256;
import com.amugeona.amugeona.menupick.common.Util;
import com.amugeona.amugeona.menupick.common.dao.AbstractDAO;
import com.amugeona.amugeona.menupick.service.AmugeonaService;

@Controller
public class AdminController {

	@Resource(name = "adminService")
	private AdminService adminService;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "/admin/loginPage.do")
	public ModelAndView loginPage(Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin login =====");
		ModelAndView mv = new ModelAndView("/admin/loginPage");
		
		return mv;
	}
	
	@RequestMapping(value = "/admin/menu.do")
	public ModelAndView loginPage(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin login =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("redirect:/admin/loginPage.do");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/admin/menu");
		return mv;
	}
	
	@RequestMapping(value = "/admin/typeManage.do")
	public ModelAndView typeManage(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin typeManage =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("redirect:/admin/loginPage.do");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/admin/type/manage");
		return mv;
	}
	
	@RequestMapping(value = "/admin/typeSelect.do")
	public ModelAndView typeSelect(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin typeManage =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("redirect:/admin/loginPage.do");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/admin/type/select");
		return mv;
	}
	
	@RequestMapping(value = "/admin/foodManage.do")
	public ModelAndView foodManage(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin foodManage =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("redirect:/admin/loginPage.do");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("/admin/food/manage");
		return mv;
	}
	
	@RequestMapping(value = "/admin/foodSelect.do")
	public ModelAndView foodSelect(HttpServletRequest request, Map<String, Object> commandMap) throws Exception {
		logger.info("==== admin foodManage =====");
		if(isSessionCheck(request)) {
			ModelAndView mv = new ModelAndView("redirect:/admin/loginPage.do");
			return mv;
		}
		ModelAndView mv = new ModelAndView("/admin/food/select");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> resultMap = new HashMap<String, Object>();
		paramMap.put("groupId", "CM002");
		List<Map<String,Object>> step01 = adminService.selectStepType(paramMap);
		
		paramMap.put("groupId", "CM003");
		List<Map<String,Object>> step02 = adminService.selectStepType(paramMap);
		
		paramMap.put("groupId", "CM004");
		List<Map<String,Object>> step03 = adminService.selectStepType(paramMap);
		
		paramMap.put("groupId", "CM005");
		List<Map<String,Object>> step04 = adminService.selectStepType(paramMap);
		
		paramMap.put("groupId", "CM006");
		List<Map<String,Object>> step05 = adminService.selectStepType(paramMap);
		
		paramMap.put("groupId", "CM007");
		List<Map<String,Object>> step06 = adminService.selectStepType(paramMap);
		
		paramMap.put("groupId", "CM008");
		List<Map<String,Object>> step07 = adminService.selectStepType(paramMap);
		
		List<Map<String,Object>> list = adminService.selectFoodList(paramMap);
		
		mv.addObject("step01", step01);
		mv.addObject("step02", step02);
		mv.addObject("step03", step03);
		mv.addObject("step04", step04);
		mv.addObject("step05", step05);
		mv.addObject("step06", step06);
		mv.addObject("step07", step07);
		
		mv.addObject("list", list);

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
	
	@RequestMapping(value = "/admin/foodUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> foodUpdate( MultipartHttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Iterator<String> files = request.getFileNames();
		String foodCd = (String)request.getParameter("foodCd");
		String foodNm = (String)request.getParameter("foodNm");
		
		Map<String, Object> foodInfoMap = new HashMap<String, Object>();
		
		if(files.hasNext()) {
			MultipartFile multipartFile = request.getFile(files.next());
			String result = saveFile(multipartFile);
			
		    String img = "/images/food/"+result;
		    
		    foodInfoMap.put("img", img);
		    
		    
		}
		foodInfoMap.put("foodCd", foodCd);
		foodInfoMap.put("foodNm", foodNm);
		
		adminService.updateFoodInfo(foodInfoMap);
		

	    for(int i = 1;i<7;i++) {
	    	String value = (String)request.getParameter("step0"+i);
	    	if(!Util.isBlank(value)) {
	    		Map<String, Object> foodMapping = new HashMap<String, Object>();
		    	foodMapping.put("foodCd", foodCd);
		    	foodMapping.put("foodType", value);
		    	adminService.updateFoodMap(foodMapping);
	    	}
	    	 
	    }
	    
	    resultMap.put("result", true);
		return resultMap; // 화면으로 던져준다!!
		 
	}
	
	@RequestMapping(value = "/admin/foodDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> foodDelete(HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();

		String foodCd = (String)request.getParameter("foodCd");

		
		Map<String, Object> foodInfoMap = new HashMap<String, Object>();

		foodInfoMap.put("foodCd", foodCd);

		
		adminService.deleteFoodInfo(foodInfoMap);
		
	    resultMap.put("result", true);
		return resultMap; // 화면으로 던져준다!!
		 
	}
	
	@RequestMapping(value = "/admin/ajaxFoodSelect.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxFoodSelect( HttpServletRequest request, @RequestBody Map<Object, Object> paramMap, ModelMap model) throws Exception {
		Map<String, Object> resultMap = new HashMap<String,Object>();
		if(Util.isBlank((String)paramMap.get("foodCd"))) { 
			resultMap.put("result",false); 
			return resultMap;
		}
		
		resultMap.put("foodCd", (String)paramMap.get("foodCd"));

		List<Map<String, Object>> result = adminService.selectFoodType(resultMap);
		
		if(result == null) {
			resultMap.put("result",false); 
			return resultMap;
		}
		
		resultMap.put("result",true); 
		resultMap.put("list", result);
		return resultMap; // 화면으로 던져준다!!
		 
	}
	
	@RequestMapping(value = "/admin/foodInsert.do", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView foodInsert( HttpServletRequest request, MultipartFile uploadfile, Model model) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/food/manage");
		logger.info("upload() POST 호출");
	    logger.info("파일 이름: {}", uploadfile.getOriginalFilename());
	    logger.info("파일 크기: {}", uploadfile.getSize());

	    String result = saveFile(uploadfile);
	    
	    if(result !=null){ // 파일 저장 성공
	        model.addAttribute("result", result);
	    } else { // 파일 저장 실패
	        model.addAttribute("result","fail");
	    }
	    String foodName = (String)request.getParameter("foodName");
	    String img = "/images/food/"+result;
	    
	    Map<String, Object> foodInfoMap = new HashMap<String, Object>();
	    foodInfoMap.put("foodName", foodName);
	    foodInfoMap.put("img", img);
	    
	    adminService.insertFoodInfo(foodInfoMap);
	    long codeId = (Long)foodInfoMap.get("CODE_ID");
	    String foodCd = "";
	    if(codeId >= 1000) {
	    	foodCd = "FD"+Long.toString(codeId);
	    }else if(codeId >= 100) {
	    	foodCd = "FD0"+Long.toString(codeId);
	    }else if(codeId >= 10) {
	    	foodCd = "FD00"+Long.toString(codeId);
	    }else {
	    	foodCd = "FD000"+Long.toString(codeId);
	    }
	    for(int i = 1;i<7;i++) {
	    	String value = (String)request.getParameter("step0"+i);
	    	if(!Util.isBlank(value)) {
	    		Map<String, Object> foodMapping = new HashMap<String, Object>();
		    	foodMapping.put("foodId", foodCd);
		    	foodMapping.put("foodType", value.split("\\|")[0]);
		    	adminService.insertFoodMap(foodMapping);
	    	}
	    	 
	    }
	    
	    return mv;
	}
	
	public boolean isSessionCheck(HttpServletRequest request) {
		
		String flag = (String)request.getSession().getAttribute("loginYN");
		if("Y".equals(flag)) {
			return false;
		}else {
			return true;
		}
	}
	
	private String saveFile(MultipartFile file){
	    // 파일 이름 변경
	    UUID uuid = UUID.randomUUID();
	    String saveName = uuid + "_" + file.getOriginalFilename();

	    logger.info("saveName: {}",saveName);

	    // 저장할 File 객체를 생성(껍데기 파일)ㄴ
	    File saveFile = new File("E:\\dev\\workspace\\amugeona\\src\\main\\webapp\\images\\food",saveName); // 저장할 폴더 이름, 저장할 파일 이름

	    try {
	        file.transferTo(saveFile); // 업로드 파일에 saveFile이라는 껍데기 입힘
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }

	    return saveName;
	}

}
