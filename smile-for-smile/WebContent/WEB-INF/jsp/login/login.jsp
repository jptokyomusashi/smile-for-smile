<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>ログイン</title>
</head>
<body>
	<h2>SFSログイン画面</h2>
	<form:form name="form" modelAttribute="employeeBean" method="post" action="/login/login.html">
		<table>
			<tr>
				<td>従業員ID</td>
				<td><form:input path="employeeId" maxlength="10"/></td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td><form:password path="password" maxlength="10"/></td>
			</tr>
		</table>
		<br/>
		<input class="normal" type="submit" value="ログイン"/>
		<br/>
		<br/>
		<font color="red">
			<spring:hasBindErrors name="employeeBean">
					<c:forEach items="${errors.globalErrors}" var="error">
						<spring:message code="${error.code}"/>
					</c:forEach>
			</spring:hasBindErrors>
			<form:errors path="employeeId"/>
			<form:errors path="password"/>
		</font>

	</form:form>
</body>
</html>