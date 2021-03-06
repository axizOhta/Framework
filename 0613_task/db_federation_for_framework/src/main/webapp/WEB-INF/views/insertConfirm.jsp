<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録確認画面</title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
	<p>これでよろしいですか？</p>

	<c:if test="${not empty msg}">
		<div class="message">
			<p class = "required">${msg}</p>
		</div>
	</c:if>

	<form:form action="insertConfirm" modelAttribute="command">
		<fieldset class="label-110">
			<div>
				<label>名前</label>
				<form:input path="name" value="${fn:escapeXml(insertname)}"
					readonly="true"></form:input>
			</div>
			<div>
				<label>TEL</label>
				<form:input path="tel" value="${fn:escapeXml(inserttel)}"
					readonly="true"></form:input>
			</div>
			<div>
				<label>PASS（再入力）</label>
				<form:password path="rePass"></form:password>
			</div>
		</fieldset>
		<div>
			<form:button>登録</form:button>
		</div>
	</form:form>
	<form:form action="insertBack">
		<form:button>戻る</form:button>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>