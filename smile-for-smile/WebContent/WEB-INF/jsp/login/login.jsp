<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>ログイン</title>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
	</div>

	<div id="main">
<%--
		<form name="form" action="<c:url value="/j_spring_security_check"/>" method="post">
			<table>
				<tr>
					<th>従業員ID</th>
					<td><input type="text" name="j_username"/></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="j_password"/></td>
				</tr>
			</table>
			<input type="submit" value="ログイン" />
		</form>
	
		<br/>
		<c:if test="${!empty param.failure}">
			<font color="red">
				<c:out value="従業員IDかパスワードに誤りがあります。"/>
			</font>
		</c:if>
--%>
		<form:form name="form" modelAttribute="employeeBean" method="post" action="/login/login.html">
			<table>
				<tr>
					<th>従業員ID</th>
					<td><form:input path="employeeId" maxlength="10"/></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><form:password path="password" maxlength="10"/></td>
				</tr>
			</table>
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

	</div>
</body>
</html>