<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>選択画面</title>
</head>
<body>
		<p>${controller}さん、こんにちは</p>
	<p>
		<a href="selectIn">検索</a>
	</p>
	<p>
		<a href="insertIn">登録</a>
	</p>
	<p>
	<a href="updateIn">更新</a>
	</p>
	<p>
		<a href="deleteIn">削除</a>
	</p>
	<form action="logout" method="post">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>