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
	<form:form name="form" method="post" modelAttribute="mailSendBean" action="/mail/send.html">
		<h2>メルマガ作成</h2>
		<table>
			<tr>
				<th>件名</th>
				<td><form:input path="title" size="50"/><font color="red"><form:errors path="title"/></font></td>
			</tr>
			<tr>
				<th>本文</th>
				<td><form:textarea path="body" cols="70" rows="20"/><font color="red"><form:errors path="body"/></font></td>
			</tr>
		</table>
		<input class="normal" type="submit" value="送信"/>
	</form:form>
	</div>
</body>
</html>