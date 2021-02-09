package com.lime.account.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lime.account.service.AccountService;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Resource(name="accountDAO")
	private AccountDAO accountDAO;
	
	// 회계정보 추가
	@Override
	public void insertAccount(Map<String, Object> inPutMap) throws Exception {
		accountDAO.insertAccout(inPutMap);
	}

	// 회계정보 리스트
	@Override
	public List<EgovMap> listAccount(Map<String, Object> inOutMap) throws Exception {
		return accountDAO.listAccount(inOutMap);
	}

	@Override
	public Map<String, Object> seqAccount(int account_seq) throws Exception {
		return accountDAO.seqAccount(account_seq);
	}
	
	@Override
	public void modAccount(Map<String, Object> inOutMap) throws Exception {
		System.out.println("serModPro inOutMap>>>>"+ inOutMap);
		accountDAO.modAccount(inOutMap);
	}

	@Override
	public int getAllAccount() {
		return accountDAO.getListCnt();
	}
	
	// 엑셀 회계정보 리스트
	@Override
	public List<EgovMap> excelGetList(Map<String, Object> inOutMap) throws Exception {
		return accountDAO.excelGetList(inOutMap);
	}

	@Override
	public String getComkor(Map<String, Object> inOutMap) {
		return accountDAO.getComkor(inOutMap);
	}
	
}
