package com.amugeona.amugeona.menupick.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.amugeona.amugeona.menupick.common.dao.AbstractDAO;


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
	public List<Map<String, Object>> selectFoodMoreList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("amugeona.selectFoodMoreList", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFoodWorldCup(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectList("amugeona.selectFoodWorldCup", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectRandomWorldCup() throws Exception {
		return (List<Map<String, Object>>) selectList("amugeona.selectRandomWorldCup");
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectFoodNm(Map<String, Object> map) throws Exception {
		return (Map<String, Object>) selectOne("amugeona.selectFoodNm", map);
	}
}
