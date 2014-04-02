<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.board.db.BoardBean"%>
<%
	BoardBean board = (BoardBean)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/board.css"/>
<title>글쓰기</title>
</head>
<body>
<form method="post" action="BoardModifyAction.bo" name="boardForm">
	<input type="hidden" name="board_num" value="<%= board.getBoard_num()%>"/>
	<table>
	<tr>
		<th colspan="2"><h3>글쓰기</h3></th>
	</tr>
		<tr>
			<td>글쓴이</td>
			<td>
				<input type="text" name="board_name" size="10" maxlength="10" value="<%= board.getBoard_name()%>"/>
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="board_pass" size="10" maxlength="10"/>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<input type="text" name="board_subject" value="<%= board.getBoard_subject()%>"/>
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="board_content" rows="15" cols="50"><%= board.getBoard_content()%></textarea>
			</td>
		</tr>
		<tr>
			<td>파일첨부(수정불가)</td>
			<td>
				<%=board.getBoard_file() %>
			</td>
		</tr>
		<tr>
			<td colspan="2"><a href="#" onClick="boardForm.submit();">수정</a></td>
		</tr>
		<tr>
			<td colspan="2"><a href="#" onclick="boardList.bo">목록</a></td>
		</tr>
	</table>
</form>
</body>
</html>