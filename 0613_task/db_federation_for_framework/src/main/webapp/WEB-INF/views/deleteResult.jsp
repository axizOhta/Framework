<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri ="http://www.springframework.org/tags/form" prefix="form"%>

	<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除結果確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
<p>実行者：${controller}</p>
<p>正常に削除されました</p>
<form action="selectIn">
  <input type="submit" value="検索">
</form>
<div>
  <a href="menu">メニューに戻る</a>
</div>
</body>
</html>
