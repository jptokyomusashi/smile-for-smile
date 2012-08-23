<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>メルマガ</title>
<script type="text/javascript" src="/css/script.js"></script>
</head>
<body>

	<div id="main">
	<form:form name="form" modelAttribute="mailCustomerBean">
		<h2>メルマガ登録</h2>

		<table>
			<tr>
				<th>メールアドレス</th>
				<td><form:input path="mailAddress" maxlength="100" size="80"/></td>
			</tr>
		</table>
		<input type="button" class="normal" value="登録" onclick="moveForForm('/mailmagazine/doEntry.html'); return false;"/>
		<input type="button" class="normal" value="解除" onclick="moveForForm('/mailmagazine/doDelete.html'); return false;"/>
	</form:form>
	<h3><font color="blue"><c:out value="${MESSAGE}"/></font></h3>
	</div>
</body>
</html>