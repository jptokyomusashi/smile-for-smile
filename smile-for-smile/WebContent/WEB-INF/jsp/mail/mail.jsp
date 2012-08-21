<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>メルマガ作成</title>
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
	<form:form name="form" method="post" action="/mail/send.html">
		<h2>メルマガ作成</h2>

		<textarea cols="50" rows="20"></textarea>

		<%--<input class="normal" type="submit" value="送信"/>--%>
	</form:form>
	</div>
</body>
</html>