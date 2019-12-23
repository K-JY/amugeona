package com.amugeona.amugeona.menupick.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amugeona.amugeona.common.service.LogService;
import com.amugeona.amugeona.menupick.common.Util;
import com.amugeona.amugeona.menupick.service.AmugeonaService;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.omg.CORBA.portable.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AmugeonaController {
	@Resource(name = "amugeonaService")
	private AmugeonaService amugeonaService;
	@Resource(name = "logService")
	private LogService logService;
	private static final Logger logger = LoggerFactory.getLogger(AmugeonaController.class);
	@RequestMapping(value = "/amu/typeSelect.do")
	public ModelAndView openSampleBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==== /amu/typeSelect.do =====");
		logger.info("==== 메뉴 고르기 화면 =====");
		
		ModelAndView mv = new ModelAndView("/amugeona/typeSelect");
		Map<String, Object> commandMap = new HashMap<String, Object>();
		List<Map<String, Object>> list = amugeonaService.selectTypeFirstList(commandMap); // 최초 메뉴 타입 조회
		mv.addObject("list", list);

		return mv;
	}
	
	
	
	@RequestMapping(value = "/amu/foodSelect.do")
	public ModelAndView foodSelect(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==== /amu/foodSelect.do =====");
		logger.info("==== 음식 조회 화면 =====");
		ModelAndView mv = new ModelAndView("/amugeona/foodSelect");
		
		String typeData = request.getParameter("typeData"); // type 코드들
		String stepData = request.getParameter("stepData"); // step 코드들
		
		logger.info("==== typeData : "+typeData);
		logger.info("==== stepData : "+stepData);
		
		if(Util.isBlank(typeData) || Util.isBlank(stepData)) {
			typeData = "";
			stepData = "";
		}
		
		String[] typeDataList = typeData.split("\\|");
		String[] stepDataList = stepData.split("\\|");
		
		Map<String, Object> typeMap = new HashMap<String, Object>();
		for(int i = 0;i<typeDataList.length;i++) {
			typeMap.put(stepDataList[i], typeDataList[i]);
		}
		
		logService.InsertTypeLog(typeMap,Util.cookieCheck(request, response)); // 타입 로그
		
		List<Map<String, Object>> list = amugeonaService.selectFoodList(typeMap);
		mv.addObject("list", list);
		mv.addObject("typeData", typeData);
		mv.addObject("stepData", stepData);

		return mv;
	}
	
	@RequestMapping(value = "/amu/worldCup.do")
	public ModelAndView worldCup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==== /amu/worldCup.do =====");
		logger.info("==== 음식 월드컵 =====");
		
		ModelAndView mv = new ModelAndView("/amugeona/worldCup");
		String data = request.getParameter("data");
		String[] value = request.getParameter("data").split("\\|");
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<String> list = new ArrayList<String>();
		for(int i = 0;i<value.length;i++) {
			list.add(value[i]);
		}
		
		String stepData = request.getParameter("stepData"); // stepData
		String typeData = request.getParameter("typeData"); // stepData
		
		paramMap.put("list", list);
		
		List<Map<String,Object>> result = amugeonaService.selectFoodWorldCup(paramMap);
		
		mv.addObject("list", result);
		
		mv.addObject("typeData", typeData);
		mv.addObject("stepData", stepData);
		return mv;
	}
	
	@RequestMapping(value = "/amu/randomWorldCup.do")
	public ModelAndView randomWorldCup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==== /amu/randomWorldCup.do =====");
		logger.info("==== 랜던월드컵 화면 =====");
		ModelAndView mv = new ModelAndView("/amugeona/worldCup");
		
		String size = request.getParameter("size");
		
		if(Util.isBlank(size)) {
			size = "8";
		}
		int allSize = 0;
		List<Map<String,Object>> ResultList = new ArrayList<Map<String, Object>>();
		Set<Integer> number = new HashSet<Integer>();
		
		List<Map<String,Object>> list = amugeonaService.selectRandomWorldCup();
		allSize = list.size();
		
		while(true) {
			if(number.size() == Integer.parseInt(size) ) {
				break;
			}
			
			number.add((int)(Math.random()*(allSize-1))+1);
		}
		
		Iterator<Integer> iNumber = number.iterator();
        while(iNumber.hasNext()){
        	ResultList.add(list.get(iNumber.next()));
        }

		mv.addObject("list", ResultList);
		
		return mv;
	}
	
	@RequestMapping(value = "/amu/mapList.do")
	public ModelAndView mapList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==== /amu/mapList.do =====");
		logger.info("==== 카카오 맵 음식점 찾기 화면 =====");
		ModelAndView mv = new ModelAndView("/amugeona/mapList");
		
		String foodCd = request.getParameter("foodCd"); // foodCd
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("foodCd", foodCd);
		
		Map<String, Object> resultMap = amugeonaService.selectFoodNm(parameterMap);
		String foodNm = (String)resultMap.get("CODE_NAME"); // foodCd
		String stepData = request.getParameter("stepData"); // stepData
		String typeData = request.getParameter("typeData"); // stepData
		if(Util.isBlank(foodNm)) {
			mv = new ModelAndView("redirect:/main.do");
			return mv;
		}
		mv.addObject("foodNm", foodNm);
		mv.addObject("foodCd", foodCd);
		mv.addObject("stepData", stepData);
		mv.addObject("typeData", typeData);
		
		setFoodLog(request,response,foodNm,"LG004");
		
		return mv;
	}
	
	
	
	@RequestMapping(value = "/test.do")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==== worldCup =====");
		ModelAndView mv = new ModelAndView("/test");
		
		
		return mv;
	}
	
	@RequestMapping(value = "/boomtest.do")
	public ModelAndView boomTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==== worldCup =====");
		ModelAndView mv = new ModelAndView("/sample/boomtest");
		
		
		return mv;
	}
	
	@RequestMapping(value = "/kakaolinktest.do")
	public ModelAndView kakaolinktest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("==== worldCup =====");
		ModelAndView mv = new ModelAndView("/sample/kakaolinktest");
		
		
		return mv;
	}

	@RequestMapping(value = "/amu/ajaxTypeList.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> testJson(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<Object, Object> paramMap, ModelMap model) throws Exception {
		logger.info("/amugeona/ajaxTypeList.do");
		Map<String, Object> resultMap = new HashMap<String,Object>();
		if(Util.isBlank((String)paramMap.get("category"))) { 
			resultMap.put("result",false); 
			return resultMap;
		}
		
		resultMap.put("category", (String)paramMap.get("category"));
		List<Map<String, Object>> list = amugeonaService.selectTypeList(resultMap);
		resultMap.put("list", list);
		
		return resultMap; // 화면으로 던져준다!!
		 
	}
	
	@RequestMapping(value = "/amu/ajaxFirstTypeList.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxFirstTypeList(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<Object, Object> paramMap, ModelMap model) throws Exception {
		logger.info("/amugeona/ajaxFirstTypeList.do");
		Map<String, Object> resultMap = new HashMap<String,Object>();
		
		List<Map<String, Object>> list = amugeonaService.selectTypeFirstList(resultMap);
		resultMap.put("list", list);
		
		return resultMap; // 화면으로 던져준다!!
		 
	}
	
	@RequestMapping(value = "/amu/ajaxSelectFoodLog.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ajaxSelectFoodLog(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<Object, Object> paramMap, ModelMap model) throws Exception {
		logger.info("/amugeona/ajaxSelectFoodLog.do");
		Map<String, Object> resultMap = new HashMap<String,Object>();
		if(Util.isBlank((String)paramMap.get("foodNm"))) { 
			resultMap.put("result",false); 
			return resultMap;
		}
		String foodNm = (String)paramMap.get("foodNm");
		
		if(setFoodLog(request,response,foodNm,"LG003")) {
			resultMap.put("result",true); 
		}else {
			resultMap.put("result",false); 
		}
		
		return resultMap; // 화면으로 던져준다!!
		 
	}
	
	public boolean setFoodLog(HttpServletRequest request, HttpServletResponse response, String foodNm, String type) {
		String cookie = Util.cookieCheck(request, response);
		String logIdx = Util.getLogIdx();
		String type1 = type;
		
		logger.info("==== cookie : "+cookie + "=====");
		logger.info("==== foodNm : "+foodNm + "=====");
		logger.info("==== logIdx : "+logIdx + "=====");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logIdx",logIdx);
		map.put("logType1",type1);
		map.put("logType2","");
		map.put("logContent",foodNm);
		map.put("cookieId",cookie);

		try {
			logService.InsertLog(map);
		} catch (Exception e) {
			logger.error(e.toString());
			return false;
		}
		
		return true;
	}


}
