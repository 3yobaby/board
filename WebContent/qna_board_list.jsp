<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="net.board.db.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
	List<?> list = (ArrayList<?>) request.getAttribute("boardList");
	int listCount = (Integer) request.getAttribute("listCount");
	if (list != null) {
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=application.getContextPath()%>/css/board.css" />
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td colspan="5"><%=listCount%></td>
		</tr>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<%
			BoardBean bean;
				for (Object obj : list) {
					bean = (BoardBean) obj;
		%>
		<tr>
			<td id="listNum"><%=bean.getBoard_num()%></td>
			<td id="listSubject"><a
				href="./BoardDetailAction.bo?num=<%=bean.getBoard_num()%>"><%=bean.getBoard_subject()%></a>
			</td>
			<td id="listName"><%=bean.getBoard_name()%></td>
			<td id="listDate"><%=bean.getBoard_date()%></td>
			<td id="listReadCount"><%=bean.getBoard_readcount()%></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="5"><a href="BoardWriteView.bo">글쓰기</a></td>
		</tr>
		<tr>
			<td colspan="5">페이지 : <%
				int pageCount = listCount / 10 + 1;
					for (int i = 1; i <= pageCount; i++) {
			%> <a href="BoardList.bo?page=<%=i%>"> <%=i%>
			</a> <%
 	}
 %>
			</td>
		</tr>
	</table>
	<%
		} else {
			out.print("BoardList를 불러오지 못했습니다");
		}
	%>
</body>
</html>