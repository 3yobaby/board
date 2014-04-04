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
<title>답변하기</title>
</head>
<body>
<form method="post" action="BoardReplyAction.bo" name="boardForm">
<input type="hidden" name="board_num" value="<%= board.getBoard_num()%>">
<input type="hidden" name="board_re_ref" value="<%= board.getBoard_re_ref()%>">
<input type="hidden" name="board_re_lev" value="<%= board.getBoard_re_lev()%>">
<input type="hidden" name="board_re_seq" value="<%= board.getBoard_re_seq()%>">
<input type="hidden" name="board_subject" value="re: <%= board.getBoard_subject()%>"/>
	<table>
	<tr>
		<th colspan="2"><h3>답변하기</h3></th>
	</tr>
		<tr>
			<td>글쓴이</td>
			<td>
				<input type="text" name="board_name" size="10" maxlength="10"/>
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
				<input type="text" value="re: <%= board.getBoard_subject()%>" disabled/>
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="board_content" rows="15" cols="50"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2"><a href="#" onClick="boardForm.submit()">등록</a></td>
		</tr>
		<tr>
			<td colspan="2"><a href="#" onclick="history.back()">뒤로가기</a></td>
		</tr>
	</table>
</form>
</body>
</html>