package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardModifyView implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int num = Integer.parseInt(request.getParameter("board_num"));
		BoardDAO dao = new BoardDAO();
		BoardBean board = new BoardBean();
		board = dao.getDetail(num);
		request.setAttribute("board", board);
		forward.setRedirect(false);
		forward.setPath("qna_board_modify.jsp");
		return forward;
	}
}
