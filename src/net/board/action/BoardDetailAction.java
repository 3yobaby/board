package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		BoardBean board = dao.getDetail(Integer.parseInt(request.getParameter("num")));
		// readcount
		dao.setReadCount(board.getBoard_num()); // db
		board.setBoard_readcount(board.getBoard_readcount()+1); // 현재 board
		//
		request.setAttribute("boardData", board);
		forward.setRedirect(false);
		forward.setPath("qna_board_view.jsp");
		return forward;
	}

}
