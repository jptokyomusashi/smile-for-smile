<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>売上集計表</title>
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
		<h2>売上集計表</h2>

		<c:out value="${size}件"/>
		<table border="1">
			<tr>
				<th rowspan="2">No</th>
				<th rowspan="2">日付</th>
				<th rowspan="2">従業員</th>
				<th rowspan="2">コース分類</th>
				<th rowspan="2">コース</th>
				<th rowspan="2">イン</th>
				<th rowspan="2">アウト</th>
				<th colspan="2">従業員</th>
				<th colspan="2">店舗</th>
				<th rowspan="2">税額</th>
				<th rowspan="2">指名</th>
				<th rowspan="2">会員</th>
			</tr>
			<tr>
				<th>売上</th>
				<th>内割引</th>
				<th>売上</th>
				<th>内割引</th>
			</tr>
			<c:forEach items="${salesSummarySearchResultList}" var="salesSummarySearchResult" varStatus="status">
				<tr>
					<td align="right"><c:out value="${status.index + 1}"/></td>
					<td><fmt:formatDate value="${salesSummarySearchResult.day}" pattern="yyyy-MM-dd"/></td>
					<td><c:out value="${salesSummarySearchResult.employeeName}"/></td>
					<td><c:out value="${salesSummarySearchResult.courseClassName}"/></td>
					<td><c:out value="${salesSummarySearchResult.courseName}"/></td>
					<td><fmt:formatDate value="${salesSummarySearchResult.startTime}" pattern="HH:mm"/></td>
					<td><fmt:formatDate value="${salesSummarySearchResult.endTime}" pattern="HH:mm"/></td>
					<td align="right"><fmt:formatNumber value="${salesSummarySearchResult.chargeEmployee}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
					<td align="right"><fmt:formatNumber value="${salesSummarySearchResult.discountEmployee}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
					<td align="right"><fmt:formatNumber value="${salesSummarySearchResult.chargeShop}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
					<td align="right"><fmt:formatNumber value="${salesSummarySearchResult.discountShop}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
					<td align="right"><fmt:formatNumber value="${salesSummarySearchResult.tax}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
					<c:choose>
						<c:when test="${empty salesSummarySearchResult.appointId}">
							<td><c:out value=""/></td>
						</c:when>
						<c:otherwise>
							<td align="center"><c:out value="○"/></td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${empty salesSummarySearchResult.memberId}">
							<td><c:out value=""/></td>
						</c:when>
						<c:otherwise>
							<td align="center"><c:out value="○"/></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
			<tr>
				<td align="right" colspan="7"><c:out value="合計"/></td>
				<td align="right"><fmt:formatNumber value="${totalChargeEmployee}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
				<td align="right"><fmt:formatNumber value="${totalDiscountEmployee}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
				<td align="right"><fmt:formatNumber value="${totalChargeShop}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
				<td align="right"><fmt:formatNumber value="${totalDiscountShop}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
				<td colspan="3"></td>
			</tr>
			<tr>
				<td align="right" colspan="7"><c:out value="合計(割引考慮)"/></td>
				<td align="right" colspan="2"><fmt:formatNumber value="${totalChargeEmployee - totalDiscountEmployee}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
				<td align="right" colspan="2"><fmt:formatNumber value="${totalChargeShop - totalDiscountShop}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/></td>
				<td colspan="3"></td>
			</tr>
		</table>
		<c:out value="${size}件"/>
		<br/><br/>
		<input type="button" class="normal" value="戻る" onclick="move('/salessummary/search_keep.html'); return false;"/>
	</form:form>
	</div>
</body>
</html>