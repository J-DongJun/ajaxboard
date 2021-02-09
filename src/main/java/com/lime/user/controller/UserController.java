package com.lime.user.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.lime.user.dao.UserDao;
import com.lime.user.service.UserService;
import com.lime.user.vo.UserVO;
import com.lime.util.CommUtils;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller("userController")
public class UserController {
	
	@Resource(name = "jsonView")
	private MappingJackson2JsonView jsonView;
	
	@Autowired UserService userService;
	private SqlSession sqlSession;
	
	// 회원가입 GET
	@RequestMapping(value="/user/userInsert.do", method = RequestMethod.GET)
	public String userInsertView(HttpServletRequest request)throws Exception {
		return "/user/userInsert";
	}
	
	// 회원가입 POST
	@RequestMapping(value="/user/userInsertPro.do", method = RequestMethod.POST)
	public String userInsertPro(HttpServletRequest request, UserVO userVO) throws Exception {
		
		System.out.println( CommUtils.getFormParam(request) );
		System.out.println("tostring = "+userVO.toString());
		
		userVO.setUserId(request.getParameter("userId"));
		userVO.setPwd(request.getParameter("pwd"));
		userVO.setUserName(request.getParameter("userName"));
		userService.addUser(userVO);
		int num = userVO.getUser_Seq();
		System.out.println("num>>>"+num);
		return "redirect:/login/login.do";
	}
	
	// 아이디 중복체크
	@ResponseBody
	@RequestMapping(value="/idcheck.do", method = RequestMethod.POST)
	public ModelAndView idChk(HttpServletRequest request, ModelMap model) throws Exception{
		Map<String, Object> inPutMap = CommUtils.getFormParam(request);
		
		
		EgovMap result = userService.idChk(inPutMap);
		System.out.println("컨트롤러result >>>>>>>>>>" + result);
		return new ModelAndView(jsonView,result);//result;
	} 
	
}
