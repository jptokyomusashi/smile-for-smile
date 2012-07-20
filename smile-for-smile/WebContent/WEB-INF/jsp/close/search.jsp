<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>締め情報検索</title>
<script type="text/javascript" src="/css/script.js"></script>
</head>
<body>
	<table>
	<tr>
	<td valign="top">
	<jsp:include page="/WEB-INF/jsp/menu/menu.jsp"/>
	</td>

	<td valign="top">
	<form:form name="form" modelAttribute="closeSearchConditionBean" method="post" action="/close/doSearch.html">
		<h2>締め情報検索</h2>

		<table border="1">
			<tr>
				<th>日付(yyyy-mm-dd)</th>
				<td>
					<form:input path="day" maxlength="10" size="15"/>
					<font color="red"><form:errors path="day"/></font>
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