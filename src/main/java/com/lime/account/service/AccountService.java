package com.lime.account.service;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface AccountService {

	public void insertAccount(Map<String, Object> inPutMap) throws Exception;
	
	public List<EgovMap> listAccount(Map<String, Object> inOutMap) throws Exception;
	
	public List<EgovMap> excelGetList(Map<String, Object> inOutMap) throws Exception;
	
	public Map<String, Object> seqAccount(int account_seq) throws Exception;
	
	public void modAccount(Map<String,Object> inOutMap) throws Exception;

	public int getAllAccount();
	
	public String getComkor(Map<String, Object> inOutMap);
}
