package com.amugeona.amugeona.service;

import java.util.List;
import java.util.Map;

public interface AmugeonaService {
	List<Map<String,Object>> selectTypeFirstList(Map<String, Object> commandMap) throws Exception;
	
	List<Map<String,Object>> selectTypeList(Map<String, Object> commandMap) throws Exception;
	
	List<Map<String,Object>> selectFoodList(Map<String, Object> commandMap) throws Exception;
}
