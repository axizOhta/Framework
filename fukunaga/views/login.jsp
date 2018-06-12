<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri ="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
<!-- メッセージがあればメッセージを表示 -->

	<form:form action="login" modelAttribute="login">
		ID<form:input path="id" /><br>
		PASS<form:password path="pass" />
		<form:button>ログイン</form:button>
	</form:form>
	<div>
		<a href="index.jsp">TOP画面へ</a>
	</div>
</body>
</html>
