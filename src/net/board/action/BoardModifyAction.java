package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String board_pass = request.getParameter("board_pass");

		// dao
		BoardDAO dao = new BoardDAO();
		if (!dao.isBoardWriter(board_num, board_pass)) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호가 틀립니다');");
			out.print("location.href='./';");
			out.print("</script>");
			return null;
		}
		BoardBean board = new BoardBean();
		board.setBoard_name(request.getParameter("board_name"));
		board.setBoard_content(request.getParameter("board_subject"));
		board.setBoard_subject(request.getParameter("board_content"));
		board.setBoard_num(board_num);
		// forward
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("BoardList.bo");
		dao.boardModify(board);
		return forward;
	}
}