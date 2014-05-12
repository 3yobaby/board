package net;

import javax.servlet.http.HttpServletRequest;

public class RequestParser {
	public RequestParser() {
	}
	public static String getRequestComamnd(HttpServletRequest request){
		String uri = request.getRequestURI();
		return uri.substring(uri.indexOf("/"));
	}
//	public static void main(String args[]){
//		String uri = "abc.com/abc/efg.do";
//		System.out.println(uri.substring(uri.lastIndexOf('/')));
//	}
}
