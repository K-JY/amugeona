package com.amugeona.amugeona.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.amugeona.amugeona.common.Util;
import com.amugeona.amugeona.service.AmugeonaService;

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
