<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/board.css"/>
<title>글쓰기</title>
<script>
	function addBoard(){
		//document.forms[0].submit();
		document.boardForm.submit();
	}
</script>
</head>
<body>
<form method="post" action="./BoardWriteAction.bo" enctype="multipart/form-data" name="boardForm">
	<table>
	<tr>
		<th colspan="2"><h3>글쓰기</h3></th>
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
				<input type="text" name="board_subject"/>
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="board_content" rows="15" cols="50"></textarea>
			</td>
		</tr>
		<tr>
			<td>파일첨부</td>
			<td>
				<input type="file" name="board_file"/>
			</td>
		</tr>
		<tr>
			<td colspan="2"><a href="#" onClick="addBoard()">등록</a></td>
		</tr>
		<tr>
			<td colspan="2"><a href="#" onclick="history.back()">뒤로가기</a></td>
		</tr>
	</table>
</form>
</body>
</html>