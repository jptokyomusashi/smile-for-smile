<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>支払伝票一覧</title>
<script type="text/javascript" src="/css/script.js"></script>
</head>
<body>
	<table>
	<tr>
	<td valign="top">
	<jsp:include page="/WEB-INF/jsp/menu/menu.jsp"/>
	</td>

	<td valign="top">
	<form:form name="form" modelAttribute="paymentSlipSearchResultBean" method="post" action="/payment/updateEntry.html">
		<h2>支払伝票一覧</h2>

		<c:out value="${size}件"/>
		<table border="1">
			<tr>
				<th>No</th>
				<th>伝票番号</th>
				<th>日付</th>
				<th>支払先</th>
				<th>金額</th>
			</tr>
			<c:forEach items="${paymentSlipSearchResultList}" var="paymentSlipSearchResult" varStatus="status">
				<tr>
					<td align="right"><c:out value="${status.index + 1}"/></td>
					<td>
						<a href="/payment/updateEntry.html?slipId=${paymentSlipSearchResult.slipId}">
							<fmt:formatNumber value="${paymentSlipSearchResult.slipId}" pattern="000000"/>
						</a>
					</td>
					<td><fmt:formatDate value="${paymentSlipSearchResult.day}" pattern="yyyy-MM-dd(E)"/></td>
					<td><c:out value="${paymentSlipSearchResult.payee}"/></td>
					<td align="right"><fmt:formatNumber value="${paymentSlipSearchResult.totalCharge}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>					
				</tr>
			</c:forEach>
		</table>
		<c:out value="${size}件"/>
		<br/>
		<br/>
		<input type="button" class="normal" value="戻る" onclick="move('/payment/search_keep.html'); return false;"/>
	</form:form>
	
	</td>
	</tr>
	</table>
</body>
</html>