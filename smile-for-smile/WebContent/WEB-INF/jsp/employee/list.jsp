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
<title>従業員一覧</title>
<script type="text/javascript" src="/css/script.js">
</script>
<script type="text/javascript">
function toEntry(employeeId) {
	document.form.action = '/employee/toEntry.html';
	document.form.method = 'POST';
	document.form.employeeId.value = employeeId;
	document.form.submit();
}
</script>

</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
	</div>

	<div id="menu">
		<jsp:include page="/WEB-INF/jsp/menu/menu.jsp"/>
	</div>

	<div id="main">
		<form:form name="form">
			<h2>従業員一覧</h2>
	
			<table>
				<tr>
					<th>ID</th>
					<th>パスワード</th>
					<th>名前</th>
					<th>e-mail</th>
					<th>支払割合</th>
					<th>権限</th>
					<th>表示順</th>
					<th>退職</th>
				</tr>
				<c:forEach items="${employeeList}" var="employee" varStatus="status">
					<tr>
						<td><a href="" onclick="toEntry('${employee.employeeId}'); return false;"><c:out value="${employee.employeeId}"/></a></td>
						<td><c:out value="${employee.password}"/></td>
						<td><c:out value="${employee.name}"/></td>
						<td><c:out value="${employee.email}"/></td>
						<td align="right"><fmt:formatNumber value="${employee.share / 100}" type="PERCENT"/></td>				
						<td><c:out value="${employee.authorityName}"/></td>
						<td><c:out value="${employee.sort}"/></td>
						<td><c:out value="${employee.resignedName}"/></td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" name="employeeId"/>
		</form:form>
	</div>
</body>
</html>