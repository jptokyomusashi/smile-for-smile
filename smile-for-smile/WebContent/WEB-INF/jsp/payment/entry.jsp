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
<title>支払伝票登録</title>
<script type="text/javascript" src="/css/script.js"></script>
</head>
<body onload="calcChargeForPaymentSlipAll()">

	<div>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
	</div>

	<div id="menu">
		<jsp:include page="/WEB-INF/jsp/menu/menu.jsp"/>
	</div>

	<div id="main">
	<form:form name="form" modelAttribute="paymentSlipBean" method="post" action="/payment/doEntry.html">
		<c:choose>
			<c:when test="${empty paymentSlipBean.paymentSlipHeadBean.slipId}"><h2>支払伝票新規登録</h2></c:when>
			<c:otherwise><h2>支払伝票修正</h2></c:otherwise>
		</c:choose>

		<form:hidden path="paymentSlipHeadBean.slipId"/>
		<table border="1">
			<c:if test="${!empty paymentSlipBean.paymentSlipHeadBean.slipId}">
				<tr>
					<th>伝票番号</th>
					<td><fmt:formatNumber value="${paymentSlipBean.paymentSlipHeadBean.slipId}" pattern="000000"/></td>
				</tr>
			</c:if>
			<tr>
				<th>日付※</th>
				<td>
					<form:input class="date" path="paymentSlipHeadBean.day" maxlength="10"/>
					<font color="red"><form:errors path="paymentSlipHeadBean.day"/></font>
				</td>
			</tr>
			<tr>
				<th>支払先※</th>
				<td>
					<form:input path="paymentSlipHeadBean.payee" maxlength="20" size="30"/>
					<font color="red"><form:errors path="paymentSlipHeadBean.payee"/></font>
				</td>
			</tr>
		</table>
		<br/>

		<table border="1">
			<tr>
				<th rowspan="2">No</th>
				<th>品目※</th>
				<th>単価※</th>
				<th>数量※</th>
				<th>金額</th>
			</tr>
			<tr>
				<th colspan="1">勘定科目※</th>
				<th colspan="3">摘要</th>
			</tr>
			<c:forEach begin="0" end="4" varStatus="status">
				<tr>
					<%--No--%>
					<td rowspan="2" align="right"><c:out value="${status.index + 1}"/></td>
					<%--品目--%>
					<td>
						<form:input path="paymentSlipDetailList[${status.index}].name" maxlength="20"/>
						<font color="red"><form:errors path="paymentSlipDetailList[${status.index}].name"/></font>
					</td>
					<%--単価--%>
					<td>
						<form:input path="paymentSlipDetailList[${status.index}].unitPrice" size="10" style="text-align: right;" onchange="moveForForm('/payment/calc.html')"/>
						<font color="red"><form:errors path="paymentSlipDetailList[${status.index}].unitPrice"/></font>
					</td>
					<%--数量--%>
					<td>
						<form:input path="paymentSlipDetailList[${status.index}].amount" size="10" style="text-align: right;" onchange="moveForForm('/payment/calc.html')"/>
						<font color="red"><form:errors path="paymentSlipDetailList[${status.index}].amount"/></font>
					</td>
					<%--金額--%>
					<td align="right">
						<fmt:formatNumber value="${paymentSlipBean.paymentSlipDetailList[status.index].charge}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/>
					</td>
				</tr>
				<tr>
					<%--勘定科目--%>
					<td colspan="1">
						<form:select path="paymentSlipDetailList[${status.index}].account">
							<form:option value=""/>
							<form:options items="${account}" itemValue="id" itemLabel="name"/>
						</form:select>
						<font color="red"><form:errors path="paymentSlipDetailList[${status.index}].account"/></font>
					</td>
					<%--摘要--%>
					<td colspan="3">
						<form:input path="paymentSlipDetailList[${status.index}].comment" maxlength="20" size="30"/>
					</td>
				</tr>
			</c:forEach>
		</table>
		<font color="red">
			<spring:hasBindErrors name="paymentSlipBean">
					<c:forEach items="${errors.globalErrors}" var="error">
						<spring:message code="${error.code}"/><br/>
					</c:forEach>
			</spring:hasBindErrors>
		</font>

		<br/>

		<c:choose>
			<c:when test="${empty paymentSlipBean.paymentSlipHeadBean.slipId}">
				<input class="normal" type="submit" value="登録"/>
			</c:when>
			<c:otherwise>
				<c:if test="${empty closeHeadBean}"><input class="normal" type="submit" value="修正"/></c:if>
				<input type="button" class="normal" value="戻る" onclick="move('/payment/list.html'); return false;"/>
			</c:otherwise>
		</c:choose>
		<br/>
		<br/>

	</form:form>
	</div>
</body>
</html>