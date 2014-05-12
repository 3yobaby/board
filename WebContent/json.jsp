<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String json1 = "{'id':'admin','pass':'1234'}";
	String json2 = "{'name':'kim','addr':'busan'}";
	String reqInfo = request.getParameter("cmd");
	if(reqInfo != null){
		if(reqInfo.equals("a")){
			out.print(json1);
		}else if(reqInfo.equals("b")){
			out.print(json2);
		}
	}else{
		out.print("command is null");
	}
%>
