package com.lime.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.lime.common.service.CommonService;
import com.lime.login.service.LoginService;
import com.lime.util.CommUtils;

import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller("loginController")
public class LoginController {
	
	@Autowired private LoginService loginService;
	
	
	@Resource(name = "jsonView")
	private MappingJackson2JsonView jsonView;

	/*@Resource(name="commonService")
	private CommonService commonService;*/
	
	
	// 로그인  화면처리
	@RequestMapping(value="/login/login.do" )
	public String loginview(HttpServletRequest request)throws Exception {
		return "/login/login";
	}
	
	

	// 로그인 검토
	@ResponseBody
	@RequestMapping(value="/idCkedAjax.do", method = RequestMethod.POST)
	public ModelAndView idCkedAjax(HttpServletRequest request,ModelMap model,
									HttpSession session) throws Exception {
		Map<String, Object> inPutMap  = new HashMap<String, Object>();
		
		inPutMap.put("userId", request.getParameter("userId"));
		inPutMap.put("pwd", request.getParameter("pwd"));
		session = request.getSession();
		
		System.out.println("로그인컨트롤러 id >>>>>>>>" + inPutMap.get("userId"));
		System.out.println("로그인컨트롤러 pwd >>>>>>>>" + inPutMap.get("pwd"));
		EgovMap result = loginService.loginChk(inPutMap);
		
		// 로그인 확인 여부 후 세션으로 작성자 살림 -> 조건문안걸면 로그인 ajax에서 아뒤비번틀릴시 alert창 안뜨고 에러만남
		if(result != null){
		String userName = (String)result.get("userName");
		session.setAttribute("userName", userName);
		}
		
		model.put("inPutMap", inPutMap);
		System.out.println("로그인컨트롤러result >>>>>>>>>>" + result);
		System.out.println("inPutMap>>>>>>>>" + inPutMap);
		return new ModelAndView(jsonView, result);
	}
	
	// 로그아웃 처리
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		loginService.logout(session);
		System.out.println("session >>>>>>>"+session);
		return "/login/login";
	}



}// end of class
