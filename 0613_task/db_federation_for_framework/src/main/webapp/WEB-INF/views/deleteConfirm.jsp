<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri ="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>これでよろしいですか？</p>

	<form:form action="deleteConfirm" modelAttribute="delete">
		ID<form:input path="id" value="${fn:escapeXml(deleteUser.userId)}" /><br>
		名前<form:input path="name" value="${fn:escapeXml(deleteUser.userName)}" /><br>
		TEL<form:input path="tel" value="${fn:escapeXml(deleteUser.telephone)}" />
		<form:button>確認</form:button>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
