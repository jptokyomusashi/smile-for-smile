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
<title>売上伝票検索</title>
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
	<form:form name="form" modelAttribute="salesSlipSearchConditionBean" method="post" action="/sales/doSearch.html">
		<h2>売上伝票検索</h2>

		<table border="1">
			<tr>
				<th>日付(yyyy-mm-dd)</th>
				<td><form:input class="date" path="dayFrom" maxlength="10" size="15"/>～<form:input class="date" path="dayTo" maxlength="10" size="15"/></td>
			</tr>
			<%--
			<tr>
				<th>曜日</th>
				<td>
					<form:select path="weekday">
						<form:option value=""/>
						<form:options items="${weekday}" itemValue="code" itemLabel="label"/>
					</form:select>
				</td>
			</tr>
			--%>
			<tr>
				<th>従業員</th>
				<td>
					<form:select path="employeeId">
						<form:option value=""/>
						<form:options items="${staff}" itemValue="employeeId" itemLabel="name"/>
					</form:select>
				</td>
			</tr>
		</table>
		<input class="normal" type="submit" value="検索"/>
		<br/>
		<br/>
		<font color="red">
			<spring:hasBindErrors name="salesSlipSearchConditionBean">
					<c:forEach items="${errors.globalErrors}" var="error">
						<spring:message code="${error.code}"/><br/>
					</c:forEach>
			</spring:hasBindErrors>
			<form:errors path="dayFrom"/><br/>
			<form:errors path="dayTo"/>
		</font>
	</form:form>
	</div>
	<br/>
</body>
</html>