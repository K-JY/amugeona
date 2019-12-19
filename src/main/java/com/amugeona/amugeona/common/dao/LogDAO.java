package com.amugeona.amugeona.common.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.amugeona.amugeona.menupick.common.dao.AbstractDAO;

@Repository("logDAO")
public class LogDAO extends AbstractDAO{
	@SuppressWarnings("unchecked")
	public int insertLog(Map<String, Object> map) throws Exception {
		return (Integer)insert("log.insertLog", map);
	}

}
