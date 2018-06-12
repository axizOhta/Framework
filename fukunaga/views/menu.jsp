<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>${fn:escapeXml(loginUser.admin_name)}さん、こんにちは</p>

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
