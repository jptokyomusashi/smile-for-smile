<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>設定</title>
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
		<form:form name="form" modelAttribute="mailSettingBean" method="post" action="/mail/doSetting.html">
			<h2>設定</h2>
			<table>
				<tr>
					<th>SMTPサーバ※</th>
					<td><form:input path="smtp" maxlength="50" size="50"/></td>
				</tr>
				<tr>
					<th>ポート番号※</th>
					<td><form:input path="port" maxlength="5"/></td>
				</tr>
				<tr>
					<th>送信メールアドレス※</th>
					<td><form:input path="sendAddress" maxlength="50" size="50"/></td>
				</tr>
				<tr>
					<th>送信者名※</th>
					<td><form:input path="sendName" maxlength="20"/></td>
				</tr>
			</table>
			<input class="normal" type="submit" value="登録"/>
		</form:form>
		<c:out value="${MESSAGE}"/>
	</div>
</body>
</html>