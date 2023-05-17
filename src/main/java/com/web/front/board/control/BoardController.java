package com.web.front.board.control;


import com.web.config.CmeResultVO;
import com.web.config.control.CmeDefaultExtendController;
import com.web.config.util.ComUtil;
import com.web.config.util.SessionUtil;
import com.web.front.board.service.BoardService;
import com.web.front.board.vo.BoardVO;
import com.web.front.login.LoginConstant;
import com.web.front.login.util.LUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/front/board")
public class BoardController extends CmeDefaultExtendController {
    @Autowired
    BoardService boardService;

    LUtil lUtil = LUtil.getInstance();

    /* @Method Name  : boardList
     * @작성일   : 20190314
     * @작성자   : 김수연
     * @변경이력  :
     * @Method 설명 :게시판 리스트
     * @throws Exception*/

    @RequestMapping(value = "/list.dm")
    public ModelAndView boardList(@ModelAttribute BoardVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String lang = getLocale(request);
        if(null == lang|| "".equals(lang)){
            setLocale(request,response,"en");
            lang = getLocale(request);
        }
        vo.setLang(lang);

        if (null == vo.getBoard_id() || "".equals(vo.getBoard_id())) {
            ComUtil.postRedirect(response, request, null, "/front.main.index.ds/proc.go?lang=" + getLocale(request), "");
            return null;
        }

        if(!"".equals(vo.getSearchWrd()) && vo.getSearchWrd() != "" ) { //검색어
            vo.setSearchWrd(URLDecoder.decode(vo.getSearchWrd(),"utf-8")); //한글깨짐현상
        }

        if("BDMT_000000000005".equals(vo.getBoard_id())) { //1:1문의 날짜 Default 설정

            if (vo.getFuncNm().equals("")) {
                vo.setFuncNm("3");
            }

            if ("".equals(vo.getSearchBgnDe())) { //날짜 체크
                SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, -1);
                String strDt = DateFormat.format(cal.getTime());

                vo.setSearchBgnDe(strDt);// 1달전
                vo.setSearchEndDe((String) DateFormat.format(new Date())); // 현재날짜
            } // 날짜 default값
        }

        BoardVO boardInfo = boardService.getBoardMst(vo); //옵션 사용여부를 위해 가져옴

        if("Y".equals(boardInfo.getCat_view_yn())){
            List<BoardVO> optList = boardService.getBoardOptionList(vo); //옵션 리스트 검색
            model.addAttribute("optList",optList);
        }
        vo.setCat_view_yn(boardInfo.getCat_view_yn());

        int boardCnt = boardService.getBoardListCnt(vo); //게시글 cnt

        vo.setFirstIndex((vo.getPageIndex() - 1) * 10);
        vo.setRecordCountPerPage(vo.getPageUnit());
        model.addAttribute("listCnt", boardCnt);
        model.addAttribute("pageSize", (boardCnt -1 ) / 10 + 1);

        List<BoardVO> list = boardService.getBoardList(vo);
        model.addAttribute("list" , list);

        String id = SessionUtil.getinstance().getStrValue(LoginConstant.LOGIN_ID);
        if (!"".equals(id)) {
            vo.setUser_email(id);
        }

        model.addAttribute("vo", vo);


        if ("BDMT_000000000005".equals(vo.getBoard_id())) {
             return new ModelAndView("/front/board/inquiry"); //1대1문의
        }else if("BDMT_000000000006".equals(vo.getBoard_id())){
             return new ModelAndView("/front/board/faq"); //faq
        }else{
            return new ModelAndView("/front/board/notice"); //미정 나중에 공지사항 나오면 공지로
        }
    }

    @RequestMapping(value = "/read.dm")
    public ModelAndView boardDetail(@ModelAttribute BoardVO vo,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String lang = getLocale(request);
        if(null == lang|| "".equals(lang)){
            setLocale(request,response,"en");
            lang = getLocale(request);
        }
        vo.setLang(lang);
        BoardVO boardInfo = boardService.getBoardMst(vo);
        model.addAttribute("boardInfo", boardInfo);

        vo.setCat_view_yn(boardInfo.getCat_view_yn());
        BoardVO result = boardService.getBoardDetail(vo); /*게시글 하나에대한 정보 및 내용 읽기*/
        model.addAttribute("result" , result);

        String id = SessionUtil.getinstance().getStrValue(LoginConstant.LOGIN_ID);
        if (!"".equals(id)) {
            vo.setUser_email(id);
        }
        model.addAttribute("vo" , vo);

        return new ModelAndView("/front/board/inquiry_view");
    }


    @RequestMapping(value = "/create.dm")
    public ModelAndView boardCreate(@ModelAttribute BoardVO vo,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String lang = getLocale(request);
        if(null == lang|| "".equals(lang)){
            setLocale(request,response,"en");
            lang = getLocale(request);
        }
        vo.setLang(lang);

        if (null != vo.getContent_id() || !"".equals(vo.getContent_id())) { //게시글 번호가 없다
            String cat_name = new String(vo.getCat_name().getBytes("iso-8859-1"), "UTF-8");
            vo.setCat_name(cat_name);
            BoardVO result = boardService.getBoardDetail(vo); /*게시글 하나에대한 정보 및 내용 읽기*/
            model.addAttribute("result" , result);
        }

        BoardVO boardInfo = boardService.getBoardMst(vo);
        model.addAttribute("boardInfo", boardInfo);


        if("Y".equals(boardInfo.getCat_view_yn())) {
            List<BoardVO> optList = boardService.getBoardOptionList(vo);
            model.addAttribute("optList",optList);
        }

        String id = SessionUtil.getinstance().getStrValue(LoginConstant.LOGIN_ID);
        if (!"".equals(id)) {
            vo.setUser_email(id);
        }

        model.addAttribute("vo" , vo);

        return new ModelAndView("/front/board/inquiry_wite");
    }

    @RequestMapping(value="/createAct.dm")
    public @ResponseBody
    CmeResultVO insertQna(@ModelAttribute BoardVO vo, HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
        String lang = getLocale(request);
        if(null == lang|| "".equals(lang)){
            setLocale(request,response,"en");
            lang = getLocale(request);
        }

        if (null == vo.getBoard_id() || "".equals(vo.getBoard_id())) {//게시판 번호가 없다
            ComUtil.postRedirect(response, request, null, "/front.main.index.ds/proc.go?lang=" + getLocale(request), "");
            return null;
        }

        CmeResultVO ResultVO = new CmeResultVO();
        vo.setLang(lang);
        vo.setReg_ip(ComUtil.getRemoteIP(request));
        if(!"".equals(vo.getBoard_pwd())){
            vo.setSecret_yn("Y");
        }

        int insNtt = boardService.insertBoard(vo);

        if(insNtt > 0) {
            ResultVO.setResultCode(1);
            ResultVO.setResultMsg(getLocale(request,"TEXT_250"));
        }else {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request,"TEXT_386"));
        }

        return ResultVO;
    }

    @RequestMapping(value="/updateAct.dm")
    public @ResponseBody CmeResultVO updateQna(BoardVO vo,HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
        String lang = getLocale(request);
        if(null == lang|| "".equals(lang)){
            setLocale(request,response,"en");
            lang = getLocale(request);
        }

        if (null == vo.getBoard_id() || "".equals(vo.getBoard_id())) {//게시판 번호가 없다
            ComUtil.postRedirect(response, request, null, "/front.main.index.ds/proc.go?lang=" + getLocale(request), "");
            return null;
        }

        if (null == vo.getContent_id() || "".equals(vo.getContent_id())) {//게시판 번호가 없다
            ComUtil.postRedirect(response, request, null, "/front.main.index.ds/proc.go?lang=" + getLocale(request), "");
            return null;
        }

        CmeResultVO ResultVO = new CmeResultVO();
        vo.setLang(lang);
        vo.setUpt_ip(ComUtil.getRemoteIP(request));
        int uptNtt = boardService.updateBoard(vo);

        if(uptNtt > 0) {
            ResultVO.setResultCode(1);
            ResultVO.setResultMsg(getLocale(request,"TEXT_250"));
        }else {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request,"TEXT_386"));
        }

        return ResultVO;
    }

    @RequestMapping(value="/delete.dm")
    public @ResponseBody CmeResultVO deleteBoard(BoardVO vo,HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
        String lang = getLocale(request);
        if(null == lang|| "".equals(lang)){
            setLocale(request,response,"en");
            lang = getLocale(request);
        }

        if (null == vo.getBoard_id() || "".equals(vo.getBoard_id())) {//게시판 번호가 없다
            ComUtil.postRedirect(response, request, null, "/front.main.index.ds/proc.go?lang=" + getLocale(request), "");
            return null;
        }

        if (null == vo.getContent_id() || "".equals(vo.getContent_id())) {//게시판 번호가 없다
            ComUtil.postRedirect(response, request, null, "/front.main.index.ds/proc.go?lang=" + getLocale(request), "");
            return null;
        }

        CmeResultVO ResultVO = new CmeResultVO();
        vo.setLang(lang);
        int delNtt = boardService.deleteBoard(vo);
        if(delNtt > 0) {
            ResultVO.setResultCode(1);
            ResultVO.setResultMsg(getLocale(request,"TEXT_335"));
        }else {
            ResultVO.setResultCode(-1);
            ResultVO.setResultMsg(getLocale(request,"TEXT_386"));
        }

        return ResultVO;
    }

    @RequestMapping(value="/chkBoardPw.dm")
    public @ResponseBody CmeResultVO chkBoardPw(BoardVO vo,HttpServletRequest request, HttpServletResponse response, Model model)throws Exception{
        CmeResultVO res = new CmeResultVO();
        String lang = getLocale(request);
        if(null == lang|| "".equals(lang)){
            setLocale(request,response,"en");
            lang = getLocale(request);
        }
        vo.setLang(lang);

        if (null == vo.getBoard_id() || "".equals(vo.getBoard_id())) {//게시판 번호가 없다
            ComUtil.postRedirect(response, request, null, "/front.main.index.ds/proc.go?lang=" + getLocale(request), "");
            return null;
        }

        if (null == vo.getContent_id() || "".equals(vo.getContent_id())) {//게시판 번호가 없다
            ComUtil.postRedirect(response, request, null, "/front.main.index.ds/proc.go?lang=" + getLocale(request), "");
            return null;
        }

        BoardVO boardInfo = boardService.getBoardMst(vo);

        vo.setCat_view_yn(boardInfo.getCat_view_yn());
        BoardVO result = boardService.getBoardDetail(vo); /*게시글 하나에대한 정보 및 내용 읽기*/

        if(vo.getBoard_pwd().equals(result.getBoard_pwd())) {
            res.setResultCode(1);
            res.setResultMsg(vo.getContent_id());
        }else {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request,"TEXT_34"));
        }
        return res;
    }

}
