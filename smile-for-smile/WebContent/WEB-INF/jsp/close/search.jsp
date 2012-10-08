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
						<form:input class="date" path="day" maxlength="10" size="15"/>
						<font color="red"><form:errors path="day"/></font>
					</td>
				</tr>
			</table>
			<input class="normal" type="submit" value="検索"/>
		</form:form>
	</div>
</body>
</html>