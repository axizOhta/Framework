<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
	<c:if test="${not empty error}">
		<div class="message">
			<p class="required">${error}</p>
		</div>
	</c:if>
	<form:form action="login" modelAttribute="login">
		<div>
			ID
			<form:input path="adminId" />
			<form:errors path="adminId" cssStyle="color: red" />
		</div>
		<div>
			password
			<form:password path="password" />
			<form:errors path="password" cssStyle="color: red" />
		</div>
		<div>
			<form:button>送信</form:button>
		</div>
	</form:form>
	<div>
		<a href="start">TOP画面へ</a>
	</div>
</body>
</html>