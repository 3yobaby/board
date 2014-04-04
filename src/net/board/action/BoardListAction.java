package net.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BoardDAO dao = new BoardDAO();
		int page = 1;
		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		int limit = 10;
		List<BoardBean> list = dao.getBoardList(page,limit);
		System.out.println(this + "... listsize"+list.size());
		request.setAttribute("boardList", list);
		int listCount = dao.getListCount();
		request.setAttribute("listCount", listCount);
		//
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("qna_board_list.jsp");
		return forward;
	}

}
