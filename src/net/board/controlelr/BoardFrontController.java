package net.board.controlelr;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.action.Action;
import net.board.action.ActionForward;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.bo")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	public BoardFrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length()); // /abc.bo
		if (command.indexOf("?") != -1) {
			command = command.substring(0, command.indexOf("?"));
		}
		ActionForward forward = null;
		forward = setForward((Action)commandMap.get(command), request, response);

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(forward
						.getPath());
				rd.forward(request, response);
			}
		}
	}

	private ActionForward setForward(Action action, HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = null;
		try {
			forward = action.execute(request, response);
			return forward;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return forward;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		loadProperties("net.board.properties/Command");
	}

	private void loadProperties(String path) {
		ResourceBundle bundle = ResourceBundle.getBundle(path);
		Enumeration<String> actionEnum = bundle.getKeys();
		while (actionEnum.hasMoreElements()) {
			String command = actionEnum.nextElement();
			String className = bundle.getString(command);
			try{
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
}
