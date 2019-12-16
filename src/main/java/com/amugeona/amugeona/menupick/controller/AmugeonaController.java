package com.amugeona.amugeona.menupick.controller;

import java.io.FileInputStream;
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
import org.springframework.web.servlet.ModelAndView;

import com.amugeona.amugeona.menupick.common.Util;
import com.amugeona.amugeona.menupick.service.AmugeonaService;

import org.omg.CORBA.portable.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AmugeonaController {
	@Resource(name = "amugeonaService")
	private AmugeonaService amugeonaService;

	private static final Logger logger = LoggerFactory.getLogger(AmugeonaController.class);
	@RequestMapping(value = "/amu/typeSelect.do")
	public ModelAndView openSampleBoardList(Map<String, Object> commandMap) throws Exception {
		logger.info("==== type selecte =====");
		ModelAndView mv = new ModelAndView("/amugeona/typeSelect");
		System.out.println("/amugeona/typeSelect.do");
		List<Map<String, Object>> list = amugeonaService.selectTypeFirstList(commandMap);
		mv.addObject("list", list);

		return mv;
	}
	
	
	
	@RequestMapping(value = "/amu/foodSelect.do")
	public ModelAndView foodSelect(HttpServletRequest request) throws Exception {
		logger.info("==== main =====");
		ModelAndView mv = new ModelAndView("/amugeona/foodSelect");
		
		String typeData = request.getParameter("typeData"); // type 코드들
		String stepData = request.getParameter("stepData"); // step 코드들
		
		logger.info("==== typeData : "+typeData);
		logger.info("==== stepData : "+stepData);
		
		if(Util.isBlank(typeData) || Util.isBlank(stepData)) {
			mv = new ModelAndView("redirect:/amugeona/main.do");
			return mv;
		}
		String[] typeDataList = typeData.split("\\|");
		String[] stepDataList = stepData.split("\\|");
		
		Map<String, Object> typeMap = new HashMap<String, Object>();
		for(int i = 0;i<typeDataList.length;i++) {
			typeMap.put(stepDataList[i], typeDataList[i]);
		}
		
		List<Map<String, Object>> list = amugeonaService.selectFoodList(typeMap);
		mv.addObject("list", list);

		return mv;
	}
	
	@RequestMapping(value = "/amu/searchRestaurant.do")
	public ModelAndView searchRestaurant(HttpServletRequest request) throws Exception {
		logger.info("==== searchRestaurant =====");
		ModelAndView mv = new ModelAndView("/amugeona/searchRestaurant");
		
		String foodNm = request.getParameter("foodNm"); // foodCd;

		mv.addObject("foodNm", foodNm);

		return mv;
	}
	
	@RequestMapping(value = "/amu/worldCup.do")
	public ModelAndView worldCup(HttpServletRequest request) throws Exception {
		logger.info("==== worldCup =====");
		ModelAndView mv = new ModelAndView("/amugeona/worldCup");
		String[] value = request.getParameter("data").split("\\|");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<String> list = new ArrayList<String>();
		for(int i = 0;i<value.length;i++) {
			list.add(value[i]);
		}
		
		paramMap.put("list", list);
		
		List<Map<String,Object>> result = amugeonaService.selectFoodWorldCup(paramMap);
		mv.addObject("list", result);
		
		return mv;
	}
	
	@RequestMapping(value = "/amu/mapList.do")
	public ModelAndView mapList(HttpServletRequest request) throws Exception {
		logger.info("==== mapList =====");
		ModelAndView mv = new ModelAndView("/amugeona/mapList");
		String foodName = request.getParameter("foodName");

		mv.addObject("foodName", foodName);
		
		return mv;
	}
	
	@RequestMapping(value = "/amu/randomWorldCup.do")
	public ModelAndView randomWorldCup(HttpServletRequest request) throws Exception {
		logger.info("==== worldCup =====");
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
			
			number.add((int)(Math.random()*allSize)+1);
		}
		
		Iterator<Integer> iNumber = number.iterator();
        while(iNumber.hasNext()){
        	ResultList.add(list.get(iNumber.next()));
        }

		mv.addObject("list", ResultList);
		
		return mv;
	}
	
	@RequestMapping(value = "/test.do")
	public ModelAndView test(HttpServletRequest request) throws Exception {
		logger.info("==== worldCup =====");
		ModelAndView mv = new ModelAndView("/test");
		
		
		return mv;
	}

	@RequestMapping(value = "/amu/ajaxTypeList.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> testJson( @RequestBody Map<Object, Object> paramMap, ModelMap model) throws Exception {
		System.out.println("/amugeona/ajaxTypeList.do");
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


}
