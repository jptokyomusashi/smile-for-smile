<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/blitzer/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript">
$(function(){
	$(".date").datepicker({dateFormat:"yy-mm-dd", changeYear:true, changeMonth:true});
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>従業員情報</title>
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
	<form:form name="form" modelAttribute="employeeBean" method="post" action="/employee/doEntry.html">
		<c:choose>
			<c:when test="${empty employeeBean.upEmployeeId}"><h2>従業員情報新規登録</h2></c:when>
			<c:otherwise><h2>従業員情報修正</h2></c:otherwise>
		</c:choose>

		<table>
			<tr>
				<th>ID※</th>
				<td>
					<c:choose>
						<c:when test="${empty employeeBean.upEmployeeId}">
							<form:input path="employeeId" maxlength="10"/>
							<font color="red"><form:errors path="employeeId"/></font>
						</c:when>
						<c:otherwise>
							<c:out value="${employeeBean.employeeId}"/>
							<form:hidden path="employeeId"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>パスワード※</th>
				<td>
					<form:input path="password" maxlength="10"/>
					<font color="red"><form:errors path="password"/></font>
				</td>
			</tr>
			<tr>
				<th>名前※</th>
				<td>
					<form:input path="name" maxlength="10"/>
					<font color="red"><form:errors path="name"/></font>
				</td>
			</tr>
			<tr>
				<th>e-mail</th>
				<td>
					<form:input path="email" maxlength="50" size="50"/>
					<font color="red"><form:errors path="email"/></font>
				</td>
			</tr>
			<tr>
				<th>支払割合※</th>
				<td>
					<form:input path="share" maxlength="5"/>
					<font color="red"><form:errors path="share"/></font>
				</td>
			</tr>
			<tr>
				<th>権限※</th>
				<td>
					<form:select path="authority">
						<form:options items="${authorityList}" itemValue="code" itemLabel="label"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th>表示順</th>
				<td>
					<form:input path="sort" maxlength="5"/>
					<font color="red"><form:errors path="sort"/></font>
				</td>
			</tr>
			<tr>
				<th>退職</th>
				<td>
					<form:select path="resigned">
						<form:options items="${resignedList}" itemValue="code" itemLabel="label"/>
					</form:select>
				</td>
			</tr>
		</table>

		<form:hidden path="upEmployeeId"/>
		<input class="normal" type="submit" value="登録"/>
		<c:if test="${!empty employeeBean.upEmployeeId}">
			<%--<input type="button" class="normal" value="削除" onclick="moveForForm('/employee/doDelete.html'); return false;"/>--%>
			<input type="button" class="normal" value="戻る" onclick="moveForForm('/employee/list.html'); return false;"/>
		</c:if>
	</form:form>
	</div>
</body>
</html>