package com.amugeona.amugeona.menupick.service;

import java.util.List;
import java.util.Map;

public interface AmugeonaService {
	List<Map<String,Object>> selectTypeFirstList(Map<String, Object> commandMap) throws Exception;
	
	List<Map<String,Object>> selectTypeList(Map<String, Object> commandMap) throws Exception;
	
	List<Map<String,Object>> selectFoodList(Map<String, Object> commandMap) throws Exception;
	
	List<Map<String,Object>> selectFoodMoreList(Map<String, Object> commandMap) throws Exception;
	
	List<Map<String,Object>> selectFoodWorldCup(Map<String, Object> commandMap) throws Exception;
	
	Map<String,Object> selectFoodNm(Map<String, Object> commandMap) throws Exception;
	
	List<Map<String,Object>> selectRandomWorldCup() throws Exception;
}
