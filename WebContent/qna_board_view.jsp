<%@page import="net.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardBean board = (BoardBean)request.getAttribute("boardData");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/board.css"/>
</head>
<body>
<table>
	<tr>
		<th colspan="2">
			<h3>상세보기</h3>
		</th>
	</tr>
	<tr>
		<td>글번호</td>
		<td><%= board.getBoard_num() %></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><%= board.getBoard_name() %></td>
	</tr>
	<tr>
		<td>제목</td>
		<td><%= board.getBoard_subject() %></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><%= board.getBoard_content() %></td>
	</tr>
	<tr>
		<td>조회수</td>
		<td><%= board.getBoard_readcount() %></td>
	</tr>
	<tr>
		<td>날짜</td>
		<td><%= board.getBoard_date() %></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td><a href="./BoardFileDown.bo"><%= board.getBoard_file() %></a></td>
	</tr>
	<tr>
		<td colspan="2">
			<a href="./">목록으로</a>
			<a href="./BoardModifyView.bo?board_num=<%= board.getBoard_num() %>">수정하기</a>
			<a href="./BoardDeleteView.bo?board_num=<%= board.getBoard_num() %>">삭제하기</a>
		</td>
	</tr>
</table>
</body>
</html>