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
	
}
