package com.amugeona.amugeona.common.service;

import java.util.Map;

public interface LogService {
	public int InsertLog(Map<String, Object> map) throws Exception;
	
	public int InsertTypeLog(Map<String, Object> map, String cookieId) throws Exception;

}
