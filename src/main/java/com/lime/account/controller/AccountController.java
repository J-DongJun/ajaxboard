package com.lime.account.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.lime.account.service.AccountService;
import com.lime.common.service.CommonService;
import com.lime.util.CommUtils;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class AccountController {


	@Resource(name = "jsonView")
	private MappingJackson2JsonView jsonView;

	@Resource(name="accountService")
	private AccountService accountService;

	@Resource(name="commonService")
	private CommonService commonService;
	
	
	
	
	/**
	 *
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	
	// 회계 리스트화면구현
	@RequestMapping(value = "/account/accountList.do")
	public String selectSampleList(HttpServletRequest request, ModelMap model,
									HttpSession session , @RequestParam(defaultValue="1")int pageNo) throws Exception {
		Map<String, Object> inOutMap = new HashMap<>();
		
		String userName = (String)session.getAttribute("userName");
		inOutMap.put("userName", userName);
		
//		int PageSize = 10; //pageSize
		int ListSize = 10; //recordCountPerPage
		
		
		PaginationInfo paginationInfo = new PaginationInfo();
		int count = accountService.getAllAccount();
		System.out.println("count >>>" + count);
		paginationInfo.setCurrentPageNo(pageNo); // 현재 페이지 번호
		paginationInfo.setRecordCountPerPage(ListSize); // 한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(4); // 페이징 리스트의 사이즈
		paginationInfo.setTotalRecordCount(count); // 전체 게시물 수
		//페이징 관련 정보가 있는 PaginationInfo 객체를 모델에 반드시 넣어준다. 
				
		int startrow = paginationInfo.getFirstRecordIndex();
		int endrow = paginationInfo.getLastRecordIndex();
		
		System.out.println("start : " + startrow);
		System.out.println("end : " + endrow);
		inOutMap.put("startrow", startrow);
		inOutMap.put("endrow", endrow);
		inOutMap.put("paginationInfo", paginationInfo);
		
		List<EgovMap> resultMap = accountService.listAccount(inOutMap);
		
		
		model.put("resultMap", resultMap);
		model.put("paginationInfo", paginationInfo);
		System.out.println("listinOutMap >>>>>"+inOutMap);
		System.out.println("listresultMap >>>>>"+resultMap);
		System.out.println("listpaginationInfo >>>>>"+paginationInfo);
		return "/account/accountList";
	}
	
	


	/**
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	// 회계정보 등록 화면
	@RequestMapping(value="/account/accountInsert.do")
	public String accountInsert(HttpServletRequest request, ModelMap model) throws Exception{

		Map<String, Object> inOutMap = new HashMap<>();
		
		inOutMap.put("category", "A000000");
		List<EgovMap> resultMap= commonService.selectCombo(inOutMap);
		System.out.println("insert resultMap : "+resultMap);
		model.put("resultMap", resultMap);
		return "/account/accountInsert";
	}
	
	
	// 회게정보 등록 기능
	@RequestMapping(value="/account/accountInsertPro.do", method = RequestMethod.POST)
	public String accountInsertPro(HttpServletRequest request, HttpSession session,ModelMap model)throws Exception{
		// MAP 데이터 연결 -> VO할때랑 똑같이하면된다 -> requestgetparam 해서  map.put 
		// 아니다 밑에 코드처럼 굳이 똑같이 설정안해도 폼태그속에 파라미터들을 다 가져와서 셋팅할수있다
		Map<String, Object> inPutMap  = CommUtils.getFormParam(request);
		
		String userName = (String)session.getAttribute("userName");
		inPutMap.put("userName", userName);
		System.out.println("userName >>>>>>>" + userName);
		System.out.println("inPutMap >>>>"+inPutMap);
		
		accountService.insertAccount(inPutMap);
		
		System.out.println("inPutMap>>>>>"+inPutMap);
		System.out.println("profitCost>>>>>>>"+inPutMap.get("profitCost"));
		
		model.put("inPutMap", inPutMap);
		
		return "redirect:/account/accountModify.do?account_seq="+inPutMap.get("account_seq");
	}
	
	
	// 회계정보 등록 후 수정페이지 화면
	@RequestMapping(value = "/account/accountModify.do", method = RequestMethod.GET)
	public ModelAndView accountModify(HttpServletRequest request, ModelMap model) throws Exception {
		
		
		int account_seq = Integer.parseInt(request.getParameter("account_seq"));
		System.out.println("account_seq >>>>>>>"+account_seq);
		Map<String, Object> seqResultMap = accountService.seqAccount(account_seq);
		
		// 수입 비용 가져오기
		Map<String, Object> inOutMap = new HashMap<>();
		inOutMap.put("category", "A000000");
		List<EgovMap> resultMap= commonService.selectCombo(inOutMap);
		System.out.println("insert resultMap : "+resultMap);
		
		// 대분류 가져오기
		inOutMap.put("code", seqResultMap.get("BIG_GROUP"));
		String BIG_GROUP= accountService.getComkor(inOutMap); 
		inOutMap.put("code", seqResultMap.get("PROFIT_COST"));
		String PROFIT_COST= accountService.getComkor(inOutMap); 
		inOutMap.put("code", seqResultMap.get("MIDDLE_GROUP"));
		String MIDDLE_GROUP= accountService.getComkor(inOutMap); 
		inOutMap.put("code", seqResultMap.get("SMALL_GROUP"));
		String SMALL_GROUP= accountService.getComkor(inOutMap); 
		inOutMap.put("code", seqResultMap.get("DETAIL_GROUP"));
		String DETAIL_GROUP= accountService.getComkor(inOutMap); 
		 
		inOutMap.put("category", seqResultMap.get("PROFIT_COST"));
		List<EgovMap> resultMap0= commonService.selectCombo(inOutMap);
		 
		inOutMap.put("category", seqResultMap.get("BIG_GROUP"));
		List<EgovMap> resultMap1= commonService.selectCombo(inOutMap);
		
		
		inOutMap.put("category", seqResultMap.get("MIDDLE_GROUP"));
		List<EgovMap> resultMap2= commonService.selectCombo(inOutMap);
		
		inOutMap.put("category", seqResultMap.get("SMALL_GROUP"));
		List<EgovMap> resultMap3= commonService.selectCombo(inOutMap); 
		
		inOutMap.put("category", seqResultMap.get("DETAIL_GROUP"));
		List<EgovMap> resultMap4= commonService.selectCombo(inOutMap); 
		
		System.out.println("seqResultMap >>>>"+seqResultMap);
		System.out.println("inOutMap>>>>>>"+inOutMap);
		
		model.put("seqResultMap", seqResultMap); // 등록한 값  
		model.put("resultMap0", resultMap0); // 등록한 값 (코드)
		model.put("resultMap1", resultMap1); // 등록한 값 (코드)    
		model.put("resultMap2", resultMap2); // 등록한 값 (코드)   
		model.put("resultMap3", resultMap3); // 등록한 값 (코드)   
		model.put("resultMap4", resultMap4); // 등록한 값 (코드)
		model.put("BIG_GROUP", BIG_GROUP); // 등록한 값(한글) 
		model.put("PROFIT_COST", PROFIT_COST); // 등록한 값(한글) 
		model.put("MIDDLE_GROUP", MIDDLE_GROUP); // 등록한 값(한글)  
		model.put("SMALL_GROUP", SMALL_GROUP); // 등록한 값(한글) 
		model.put("DETAIL_GROUP", DETAIL_GROUP); // 등록한 값(한글) 
		model.put("resultMap", resultMap); // 첫번째 셀렉박스 주입
		return new ModelAndView("/account/accountModify" ,"model",model );   
	} 
	
	// 회계정보 수정 기능구현
	@RequestMapping(value = "/account/accountModifyPro.do", method = RequestMethod.POST)
	public String accountModifyPro(HttpServletRequest request, HttpSession session) throws Exception {
		
		Map<String, Object> inOutMap = CommUtils.getFormParam(request);
		
		String userName = (String)session.getAttribute("userName");
		int account_seq = Integer.parseInt(request.getParameter("account_seq"));
		
		inOutMap.put("userName", userName);
		inOutMap.put("account_seq", account_seq);
		System.out.println("ModPro inOutMap>>>>"+ inOutMap);
		
		accountService.modAccount(inOutMap);
		return "redirect:/account/accountList.do";
	}
	

	/**
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	// Select 옵션 구현
	@RequestMapping(value="/account/selectCombo.do", method = RequestMethod.POST)
	public ModelAndView ajaxtest(HttpServletRequest request, ModelMap model) throws Exception{
		
		Map<String, Object> inOutMap  = CommUtils.getFormParam(request);
		System.out.println("셀렉콤보inOutMap : " + inOutMap);
		List<EgovMap> result = commonService.selectCombo(inOutMap);
		inOutMap.put("result", result);
		
		System.out.println("result = " + result);
		model.put("result", result);
		System.out.println("select 옵션구현 inOutMap>>>>" + inOutMap);
		return new ModelAndView(jsonView, inOutMap);
	}
	
	// 엑셀 업로드
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/account/downloadExcl.do")
	public void downloadExcl(HttpServletResponse response,HttpSession session) throws Exception {
		Map<String, Object> inOutMap = new HashMap<>(); 
		String userName = (String)session.getAttribute("userName");
		inOutMap.put("userName", userName);
		
		List<EgovMap> list = accountService.excelGetList(inOutMap);
		System.out.println("downloadExcl list >>>>" +list);
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("게시판");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;
		
		// 테이블 헤더용 스타일 
		CellStyle headStyle = wb.createCellStyle();
		
		// 가는 경계선을 가집니다.
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);



	    // 배경색은 노란색입니다.
	    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);


	    // 데이터는 가운데 정렬합니다.
	    headStyle.setAlignment(HorizontalAlignment.CENTER);


	    // 데이터용 경계 스타일 테두리만 지정
	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);


	    // 헤더 생성
	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("수익/비용");
	    
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("관");
	    
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("항");
	    
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("목");
	    
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("과");
	    
	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("금액");
	    
	    cell = row.createCell(6);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("등록일");
	    
	    cell = row.createCell(7);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("작성자");
	    
	    // 데이터 부분 생성

	    for(Object map1 : list) {
            Map<String, Object> mapValue = (Map<String, Object>) map1;
            row = sheet.createRow(rowNo++);
            cell = row.createCell(0);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+mapValue.get("PROFIT_COST"));
            cell = row.createCell(1);
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+mapValue.get("BIG_GROUP"));
            cell = row.createCell(2); 
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+mapValue.get("MIDDLE_GROUP"));
            cell = row.createCell(3); 
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+mapValue.get("SMALL_GROUP"));
            cell = row.createCell(4); 
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+mapValue.get("DETAIL_GROUP"));
            cell = row.createCell(5); 
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+mapValue.get("TRANSACTION_MONEY"));
            cell = row.createCell(6); 
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+mapValue.get("TRANSACTION_DATE"));
            cell = row.createCell(7); 
            cell.setCellStyle(bodyStyle);
            cell.setCellValue(""+mapValue.get("WRITER"));
        }

	    // 컨텐츠 타입과 파일명 지정
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=test.xls");

	    // 엑셀 출력
	    wb.write(response.getOutputStream());
	    wb.close();
	}
	

}// end of calss
