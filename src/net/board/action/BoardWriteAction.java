package net.board.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.MyFileRenamePolicy;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardBean board = new BoardBean();
		BoardDAO dao = new BoardDAO();
		// forward
		String realFolder = request.getSession().getServletContext().getRealPath("");
		File f = new File(realFolder);
		if(!f.exists()){
			f.mkdir();
		}
		boolean result =  false;
		int size = 5 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, realFolder, size, "utf-8", new MyFileRenamePolicy());
		//
		board.setBoard_subject(multi.getParameter("board_subject"));
		board.setBoard_name(multi.getParameter("board_name"));
		board.setBoard_pass(multi.getParameter("board_pass"));
		board.setBoard_content(multi.getParameter("board_content"));
		String fileName = (String)multi.getFileNames().nextElement();
		board.setBoard_file(fileName);
		result = dao.boardInsert(board);
		if(result){
			forward.setRedirect(true);
			forward.setPath("./");
			return forward;
		}else{
			
			return forward;
		}
	}

}
