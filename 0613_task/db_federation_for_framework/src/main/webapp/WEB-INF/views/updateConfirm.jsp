<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新内容確認画面</title>
<link href="/css/commons.css" rel="stylesheet">
</head>
<body>
</head>
<body>
	<p>これでよろしいですか？</p>

	<c:if test="${not empty error}">
		<div class="message">
			<p class="required">${error}</p>
		</div>
	</c:if>

	<form:form action="updateResult" modelAttribute="command">
		<fieldset>
			<div>
				<label>ID</label><input type="text" name="id"
					value="${beforeUser.userId}" readonly>
			</div>
		</fieldset>

		<fieldset class="col">
			<legend>変更前</legend>
			<div>
				<label>名前</label><input type="text" value="${beforeUser.userName}"
					disabled>
			</div>
			<div>
				<label>TEL</label><input type="text" value="${beforeUser.telephone}"
					disabled>
			</div>
			<div>
				<label>PASS</label><input type="password"
					value="${beforeUser.password}" disabled>
			</div>
		</fieldset>

		<div id="arrow" class="col">⇒</div>

		<fieldset class="col label-110">
			<legend>変更後</legend>
			<div>
				<label>名前</label>
				<form:input path="newName" value="${newUser.userName}"
					readonly="true" />
			</div>
			<div>
				<label>TEL</label>
				<form:input path="newTel" value="${newUser.telephone}"
					readonly="true" />
			</div>
			<div>
				<label>PASS(再入力)</label>
				<form:password path="rePass" value="${Pass}" />
			</div>
		</fieldset>

		<div class="col-clear">
			<form:button>更新</form:button>
			<input type="submit" name="button" value="戻る"
				onclick="location.href='updateinput'; return false;">
		</div>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>