<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>締め情報一覧</title>
<script type="text/javascript" src="/css/script.js"></script>
</head>
<body>
	<table>
	<tr>
	<td valign="top">
	<jsp:include page="/WEB-INF/jsp/menu/menu.jsp"/>
	</td>

	<td valign="top">
	<form:form name="form" modelAttribute="salesSlipSearchResultBean" method="post" action="/sales/updateEntry.html">
		<h2>締め情報一覧</h2>

		<fmt:formatDate value="${attendanceSearchConditionBean.day}" pattern="yyyy-MM-dd(E)"/>
		<c:out value="の状況"/>
		<table border="1">
			<tr>
				<th>No</th>
				<th>従業員</th>
				<th>締め</th>
			</tr>
			<c:forEach items="${attendanceSearchResultList}" var="attendanceSearchResult" varStatus="status">
				<tr>
					<td align="right"><c:out value="${status.index + 1}"/></td>
					<td><c:out value="${attendanceSearchResult.name}"/></td>				
					<c:choose>
						<c:when test="${attendanceSearchResult.closed}">
							<td>
								<input type="button" class="delete" value="取消"
								onclick="moveForFormAndAttendance('/attendance/cancelClose.html', '${attendanceSearchResult.employeeId}', '${attendanceSearchConditionBean.day}'); return false;"/>
							</td>
						</c:when>
						<c:otherwise>
							<td>
								<input type="button" class="normal" value="締め"
								onclick="moveForFormAndAttendance('/attendance/entryClose.html', '${attendanceSearchResult.employeeId}', '${attendanceSearchConditionBean.day}'); return false;"/>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
		<br/>
		<input type="button" class="normal" value="戻る" onclick="move('/attendance/search_keep.html'); return false;"/>
		<input type="hidden" name="employeeId"/>
		<input type="hidden" name="day"/>		
	</form:form>
	
	</td>
	</tr>
	</table>
</body>
</html>