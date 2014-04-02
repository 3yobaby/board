package net.board.controlelr;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.Action;
import net.board.action.ActionForward;
import net.board.action.BoardDeleteAction;
import net.board.action.BoardDeleteView;
import net.board.action.BoardDetailAction;
import net.board.action.BoardListAction;
import net.board.action.BoardModifyAction;
import net.board.action.BoardModifyView;
import net.board.action.BoardWriteAction;
import net.board.action.BoardWriteView;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()); // /abc.bo
		if(command.indexOf("?") != -1){
			command = command.substring(0, command.indexOf("?"));
		}
		ActionForward forward = null;
		
		if(command.equals("/BoardList.bo")){
			forward = setForward(new BoardListAction(), request, response);
		}else if(command.equals("/BoardWriteView.bo")){
			forward = setForward(new BoardWriteView(), request, response);
		}else if(command.equals("/BoardWriteAction.bo")){
			forward = setForward(new BoardWriteAction(), request, response);
		}else if(command.equals("/BoardDetailAction.bo")){
			forward = setForward(new BoardDetailAction(), request, response);
		}else if(command.equals("/BoardModifyView.bo")){
			forward = setForward(new BoardModifyView(), request, response);
		}else if(command.equals("/BoardModifyAction.bo")){
			forward = setForward(new BoardModifyAction(), request, response);
		}else if(command.equals("/BoardDeleteView.bo")){
			forward = setForward(new BoardDeleteView(),request, response);
		}else if(command.equals("/BoardDeleteAction.bo")){
			forward = setForward(new BoardDeleteAction(),request, response);
		}
		
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
	}
	
	private ActionForward setForward(Action action, HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = null;
		try{
			forward = action.execute(request, response);
			return forward;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return forward;
	}
}
