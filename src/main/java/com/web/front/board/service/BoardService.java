package com.web.front.board.service;

import com.web.front.board.vo.BoardVO;
import com.web.front.main.vo.PopupVO;

import java.util.List;

public interface BoardService {

    int getBoardListCnt(BoardVO vo)throws Exception;

    List<BoardVO> getBoardList(BoardVO vo)throws Exception;

    BoardVO getBoardMst(BoardVO vo)throws Exception;

    List<BoardVO> getBoardOptionList(BoardVO vo)throws Exception;

    BoardVO getBoardDetail(BoardVO vo)throws Exception;

    int insertBoard(BoardVO vo)throws Exception;

    int updateBoard(BoardVO vo)throws Exception;

    int deleteBoard(BoardVO vo)throws Exception;

    List<PopupVO> selectPopupList(PopupVO fvo)throws Exception;

    int selectPopupListCnt(PopupVO fvo) throws Exception;

    PopupVO selectPopup(PopupVO pvo) throws Exception;

    String selectFileSn(String atch_file_id)throws Exception;
}
