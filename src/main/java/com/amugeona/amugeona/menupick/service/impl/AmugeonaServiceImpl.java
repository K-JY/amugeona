package com.amugeona.amugeona.menupick.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.amugeona.amugeona.menupick.dao.AmugeonaDAO;
import com.amugeona.amugeona.menupick.service.AmugeonaService;


@Service("amugeonaService")
public class AmugeonaServiceImpl implements AmugeonaService{

	@Resource(name = "amugeonaDAO")
	private AmugeonaDAO amugeonaDAO;
	
	@Override
	public List<Map<String, Object>> selectTypeList(Map<String, Object> commandMap) throws Exception {
		return amugeonaDAO.selectTypeList(commandMap);
	}

	@Override
	public List<Map<String, Object>> selectTypeFirstList(Map<String, Object> commandMap) throws Exception {
		return amugeonaDAO.selectTypeFirstList(commandMap);
	}

	@Override
	public List<Map<String, Object>> selectFoodList(Map<String, Object> commandMap) throws Exception {
		return amugeonaDAO.selectFoodList(commandMap);
	}

	@Override
	public List<Map<String, Object>> selectFoodWorldCup(Map<String, Object> commandMap) throws Exception {
		return amugeonaDAO.selectFoodWorldCup(commandMap);
	}

	@Override
	public List<Map<String, Object>> selectRandomWorldCup() throws Exception {
		return amugeonaDAO.selectRandomWorldCup();
	}

}
