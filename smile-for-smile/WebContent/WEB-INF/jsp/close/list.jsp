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
<title>締め情報一覧</title>
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
		<form:form name="form">
			<h2>締め情報一覧</h2>
	
			<fmt:formatDate value="${closeSearchConditionBean.day}" pattern="yyyy-MM-dd(E)"/>
			<table border="1">
				<tr>
					<th>No</th>
					<th>従業員</th>
					<th>締め</th>
				</tr>
				<c:forEach items="${closeSearchResultList}" var="closeSearchResult" varStatus="status">
					<tr>
						<td align="right"><c:out value="${status.index + 1}"/></td>
						<td><c:out value="${closeSearchResult.name}"/></td>				
						<c:choose>
							<c:when test="${closeSearchResult.closed}">
								<td>
									<input type="button" class="afterclose" value="取消"
									onclick="moveForFormAndAttendance('/close/cancelClose.html', '${closeSearchResult.employeeId}', '${closeSearchConditionBean.day}'); return false;"/>
								</td>
							</c:when>
							<c:otherwise>
								<td>
									<input type="button" class="beforeclose" value="締め"
									onclick="moveForFormAndAttendance('/close/entryClose.html', '${closeSearchResult.employeeId}', '${closeSearchConditionBean.day}'); return false;"/>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
			<input type="button" class="normal" value="戻る" onclick="move('/close/search_keep.html'); return false;"/>
			<input type="hidden" name="employeeId"/>
			<input type="hidden" name="day"/>		
		</form:form>
	</div>
</body>
</html>