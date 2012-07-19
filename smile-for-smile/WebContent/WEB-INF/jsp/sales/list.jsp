<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>売上伝票一覧</title>
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
		<h2>売上伝票一覧</h2>

		<c:out value="${size}件"/>
		<table border="1">
			<tr>
				<th>No</th>
				<th>伝票番号</th>
				<th>日付</th>
				<th>従業員</th>
				<th>会員ID</th>
				<th>イン</th>
				<th>アウト</th>
			</tr>
			<c:forEach items="${salesSlipSearchResultList}" var="salesSlipSearchResult" varStatus="status">
				<tr>
					<td align="right"><c:out value="${status.index + 1}"/></td>
					<td>
						<a href="/sales/updateEntry.html?slipId=${salesSlipSearchResult.slipId}">
							<fmt:formatNumber value="${salesSlipSearchResult.slipId}" pattern="000000"/>
						</a>
					</td>
					<td><fmt:formatDate value="${salesSlipSearchResult.day}" pattern="yyyy-MM-dd(E)"/></td>
					<td><c:out value="${salesSlipSearchResult.name}"/></td>
					<td><c:out value="${salesSlipSearchResult.memberId}"/></td>
					<td><fmt:formatDate value="${salesSlipSearchResult.startTime}" pattern="HH:mm"/></td>
					<td><fmt:formatDate value="${salesSlipSearchResult.endTime}" pattern="HH:mm"/></td>
				</tr>
			</c:forEach>
		</table>
		<c:out value="${size}件"/>
		<br/>
		<br/>
		<input type="button" class="normal" value="戻る" onclick="move('/sales/search_keep.html'); return false;"/>
	</form:form>
	
	</td>
	</tr>
	</table>
</body>
</html>