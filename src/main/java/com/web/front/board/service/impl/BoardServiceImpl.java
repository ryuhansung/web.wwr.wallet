package com.web.front.board.service.impl;

import com.web.config.annotation.CommonDataSource;
import com.web.config.dbinfo.DataSourceType;
import com.web.front.board.dao.BoardDAO;
import com.web.front.board.service.BoardService;
import com.web.front.board.vo.BoardVO;
import com.web.front.main.vo.PopupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDAO boardDAO;

    @CommonDataSource(DataSourceType.BOARD)
    public int getBoardListCnt(BoardVO vo) throws Exception {
        return boardDAO.getBoardListCnt(vo);
    }

    @CommonDataSource(DataSourceType.BOARD)
    public List<BoardVO> getBoardList(BoardVO vo) throws Exception {
        return boardDAO.getBoardList(vo);
    }

    @CommonDataSource(DataSourceType.BOARD)
    public BoardVO getBoardMst(BoardVO vo) throws Exception {
        return boardDAO.getBoardMst(vo);
    }

    @CommonDataSource(DataSourceType.BOARD)
    public List<BoardVO> getBoardOptionList(BoardVO vo) throws Exception {
        return boardDAO.getBoardOptionList(vo);
    }

    @CommonDataSource(DataSourceType.BOARD)
    public BoardVO getBoardDetail(BoardVO vo) throws Exception {
        return boardDAO.getBoardDetail(vo);
    }

    @CommonDataSource(DataSourceType.BOARD)
    public int insertBoard(BoardVO vo) throws Exception {
        return boardDAO.insertBoard(vo);
    }

    @CommonDataSource(DataSourceType.BOARD)
    public int updateBoard(BoardVO vo) throws Exception {
        return boardDAO.updateBoard(vo);
    }

    @CommonDataSource(DataSourceType.BOARD)
    public int deleteBoard(BoardVO vo) throws Exception {
        return boardDAO.deleteBoard(vo);
    }

    public List<PopupVO> selectPopupList(PopupVO fvo) throws Exception {
        return boardDAO.selectPopupList(fvo);
    }

    public int selectPopupListCnt(PopupVO fvo) throws Exception {
        return boardDAO.selectPopupListCnt(fvo);
    }

    public PopupVO selectPopup(PopupVO pvo) throws Exception {
        return boardDAO.selectPopup(pvo);
    }

    @CommonDataSource(DataSourceType.BOARD)
    public String selectFileSn(String atch_file_id) throws Exception {
        return boardDAO.selectFileSn(atch_file_id);
    }
}
