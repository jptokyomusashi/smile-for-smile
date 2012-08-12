<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>支払伝票登録完了</title>
<script type="text/javascript" src="/css/script.js"></script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
	</div>

	<div id="menu">
		<jsp:include page="/WEB-INF/jsp/menu/menu.jsp"/>
	</div>

	<div id="main">
	<form:form name="form" method="post">
		<h2>支払伝票登録完了</h2>

		<spring:message code="message.payment.entryfinish"/>
		<a href="" onclick="move('/payment/newEntry.html'); return false;">戻る</a>
	</form:form>
	</div>
</body>
</html>