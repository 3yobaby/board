package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardReplyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// dao
		BoardDAO dao = new BoardDAO();
		BoardBean board = new BoardBean();
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		board = dao.getDetail(board_num);
		request.setAttribute("board", board);
		//
		ActionForward forward = new ActionForward();
		forward.setPath("qna_board_reply.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
