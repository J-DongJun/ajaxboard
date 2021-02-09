package com.lime.account.service.Impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("accountDAO")
public class AccountDAO extends EgovAbstractMapper{
	
	// 회계정보 추가
	public void insertAccout(Map<String, Object> inPutMap)throws Exception{
		insert("accountInsert", inPutMap);
	}
	
	// 회계정보 리스트
	public List<EgovMap> listAccount(Map<String, Object> inOutMap)throws Exception{
		return selectList("accountList", inOutMap);
	}
	
	// 엑셀 회계정보 리스트
	public List<EgovMap> excelGetList(Map<String, Object> inOutMap)throws Exception{
		return selectList("excelList", inOutMap);
	}
	
	// 회계정보 수정페이지 
	public Map<String, Object> seqAccount(int account_seq){
		return selectOne("accountSeqNum", account_seq);
	}
	
	// 회계정보 수정
	public void modAccount(Map<String,Object> inOutMap)throws Exception{
		System.out.println("daoModPro inOutMap>>>>"+ inOutMap);
		update("accountUpdatePro", inOutMap);
	}

	public int getListCnt() {
		return selectOne("getAllAccount");
	}
	
	// 등록 후 수정 셀렉트박스 한글로 불로올때
	public String getComkor(Map<String, Object> inOutMap) {
		return selectOne("getComKor",inOutMap); 
	} 
}
