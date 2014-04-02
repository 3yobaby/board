<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./BoardDeleteAction.bo" method="post">
글 비밀번호 <input type="text" name="board_pass"/><br>
<input type="text" value="<%= request.getParameter("board_num")%>" name="board_num"/>
<input type="submit" value="삭제">
</form>
</body>
</html>