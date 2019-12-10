package com.amugeona.amugeona.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.amugeona.amugeona.common.dao.AbstractDAO;


@Repository("adminDAO")
public class AdminDAO extends AbstractDAO{
	@SuppressWarnings("unchecked")
	public Map<String, Object> login(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("admin.selectLogin", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFoodType(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("admin.selectFoodType", map);
	}
	
	@SuppressWarnings("unchecked")
	public int insertFoodInfo(Map<String, Object> map) throws Exception {
		return (Integer)insert("admin.insertFoodInfo", map);
	}
	
	@SuppressWarnings("unchecked")
	public int insertFoodMap(Map<String, Object> map) throws Exception {
		return (Integer)insert("admin.insertFoodMap", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> selectStepType(Map<String, Object> map) throws Exception {
		return (List<Map<String,Object>>)selectList("admin.selectStepType", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> selectFoodList(Map<String, Object> map) throws Exception {
		return (List<Map<String,Object>>)selectList("admin.selectFoodList", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateFoodInfo(Map<String, Object> map) throws Exception {
		return (Integer)insert("admin.updateFoodInfo", map);
	}
	
	@SuppressWarnings("unchecked")
	public int updateFoodMap(Map<String, Object> map) throws Exception {
		return (Integer)insert("admin.updateFoodMap", map);
	}
	
}
