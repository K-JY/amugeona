package com.amugeona.amugeona.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.amugeona.amugeona.common.dao.AbstractDAO;


@Repository("amugeonaDAO")
public class AmugeonaDAO extends AbstractDAO{
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTypeFirstList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("amugeona.selectTypeFirstList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTypeList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("amugeona.selectTypeList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFoodList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("amugeona.selectFoodList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFoodWorldCup(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("amugeona.selectFoodWorldCup", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectRandomWorldCup() throws Exception {
		return (List<Map<String, Object>>) selectList("amugeona.selectRandomWorldCup");
	}
}
