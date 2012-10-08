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
		<h2>メルマガ登録</h2>
		<form:form name="form" modelAttribute="mailCustomerBean" action="/mailmagazine/doEntry.html">
			<fieldset>
			<legend>登録</legend>
			<table>
				<tr>
					<th>メールアドレス</th>
					<td><form:input path="mailAddress" maxlength="100" size="80"/></td>
				</tr>
			</table>
			<input type="submit" value="登録"/>
			</fieldset>
		</form:form>

		<br/>
		<form:form name="form" modelAttribute="mailCustomerBean" action="/mailmagazine/doDelete.html">
			<fieldset>
			<legend>解除</legend>
			<table>
				<tr>
					<th>メールアドレス</th>
					<td><form:input path="mailAddressForDelete" maxlength="100" size="80"/></td>
				</tr>
			</table>
			<input type="submit" value="解除"/>
			</fieldset>
		</form:form>
	</div>
	<h3><font color="blue"><c:out value="${MESSAGE}"/></font></h3>
</body>
</html>