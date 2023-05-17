package com.web.front.board.dao;

import com.web.config.dao.CmeComAbstractDAO;
import com.web.front.board.vo.BoardVO;
import com.web.front.main.vo.PopupVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAO extends CmeComAbstractDAO {

    public int getBoardListCnt(BoardVO vo) throws Exception {
        return (int)selectByPk("BoardDAO.getBoardListCnt" , vo);
    }

    public List<BoardVO> getBoardList(BoardVO vo) throws Exception {
        return list("BoardDAO.getBoardList", vo);
    }

    public BoardVO getBoardMst(BoardVO vo) throws Exception {
        return (BoardVO) selectByPk("BoardDAO.getBoardMst" , vo);
    }

    public List<BoardVO> getBoardOptionList(BoardVO vo) throws Exception {
        return list("BoardDAO.getBoardOptionList", vo);
    }

    public BoardVO getBoardDetail(BoardVO vo) throws Exception {
        return (BoardVO) selectByPk("BoardDAO.getBoardDetail" , vo);
    }

    public int insertBoard(BoardVO vo) throws Exception {
        return (int)insert("BoardDAO.insertBoard" , vo);
    }

    public int updateBoard(BoardVO vo) throws Exception {
        return update("BoardDAO.updateBoard" , vo);
    }

    public int deleteBoard(BoardVO vo) throws Exception {
        return update("BoardDAO.deleteBoard" , vo);
    }

    public List<PopupVO> selectPopupList(PopupVO fvo)throws Exception{
        return list("BoardDAO.selectPopupList",fvo);
    }

    public int selectPopupListCnt(PopupVO fvo)throws Exception{
        return (int)selectByPk("BoardDAO.selectPopupListCnt", fvo);
    }

    public PopupVO selectPopup(PopupVO pvo)throws Exception{
        return (PopupVO) selectByPk("BoardDAO.selectPopup", pvo);
    }

    public String selectFileSn(String atch_file_id)throws Exception{
        return getSqlSession().selectOne("BoardDAO.selectFileSn", atch_file_id);
    }
}
