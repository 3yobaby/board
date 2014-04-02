package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

public class BoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		try{
			String pass = request.getParameter("board_pass");
			int num = Integer.parseInt(request.getParameter("board_num"));
			BoardDAO dao = new BoardDAO();
			response.setContentType("text/html;charset=utf-8");
			dao.deleteBoard(num);
			PrintWriter out = response.getWriter();
			if(dao.isBoardWriter(num, pass)){ // 비밀번호가 참이면
				out.print("<script>");
				out.print("alert('삭제되었습니다');");
				out.print("location.href='./';");
				out.print("</script>");
			}else{
				out.print("<script>");
				out.print("alert('삭제실패');");
				out.print("location.href='./BoardList.bo'");
				out.print("</script>");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}
