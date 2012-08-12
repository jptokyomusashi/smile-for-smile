<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>締め情報検索</title>
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
		<form:form name="form" modelAttribute="closeSearchConditionBean" method="post" action="/close/doSearch.html">
			<h2>締め情報検索</h2>
	
			<table>
				<tr>
					<th>日付(yyyy-mm-dd)</th>
					<td>
						<form:input path="day" maxlength="10" size="15"/>
						<font color="red"><form:errors path="day"/></font>
					</td>
				</tr>
			</table>
			<input class="normal" type="submit" value="検索"/>
		</form:form>
	</div>
</body>
</html>