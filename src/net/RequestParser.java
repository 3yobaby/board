package net;

import javax.servlet.http.HttpServletRequest;

public class RequestParser {
	public static String getRequestComamnd(HttpServletRequest request){
		String uri = request.getRequestURI();
		return uri.substring(uri.indexOf("/"));
	}
}
