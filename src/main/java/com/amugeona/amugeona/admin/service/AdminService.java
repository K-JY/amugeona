package com.amugeona.amugeona.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {
	public Map<String, Object> login(Map<String, Object> map) throws Exception;
	
	public List<Map<String,Object>> selectFoodType(Map<String,Object> map) throws Exception;
	
	public int insertFoodInfo(Map<String,Object> map) throws Exception;
	
	public int insertFoodMap(Map<String,Object> map) throws Exception;
	
	public List<Map<String,Object>> selectStepType(Map<String,Object> map) throws Exception;
	
	public List<Map<String,Object>> selectFoodList(Map<String,Object> map) throws Exception;
	
	public int updateFoodInfo(Map<String,Object> map) throws Exception;
	
	public int updateFoodMap(Map<String,Object> map) throws Exception;

}
