<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>売上集計表検索</title>
<script type="text/javascript" src="/css/script.js"></script>
</head>
<body>
	<table>
	<tr>
	<td valign="top">
	<jsp:include page="/WEB-INF/jsp/menu/menu.jsp"/>
	</td>

	<td valign="top">
	<form:form name="form" modelAttribute="salesSummarySearchConditionBean" method="post" action="/salessummary/doSearch.html">
		<h2>売上集計表検索</h2>

		<table border="1">
			<tr>
				<th>日付(yyyy-mm-dd)</th>
				<td>
					<form:input path="dayFrom" maxlength="10" size="15"/>～<form:input path="dayTo" maxlength="10" size="15"/>
					<font color="red">
						<form:errors path="dayFrom"/>
						<form:errors path="dayTo"/>
					</font>
				</td>
			</tr>
		</table>
		<br/>
		<input class="normal" type="submit" value="検索"/>
		<br/>
		<br/>
	</form:form>
	
	</td>
	</tr>
	</table>
	<br/>
</body>
</html>