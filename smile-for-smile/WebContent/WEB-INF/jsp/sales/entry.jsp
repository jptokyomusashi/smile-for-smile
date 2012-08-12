<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>売上伝票登録</title>
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
	<form:form name="form" modelAttribute="salesSlipBean" method="post" action="/sales/doEntry.html">
		<c:choose>
			<c:when test="${empty salesSlipBean.salesSlipHeadBean.slipId}"><h2>売上伝票新規登録</h2></c:when>
			<c:otherwise><h2>売上伝票修正</h2></c:otherwise>
		</c:choose>

		<form:hidden path="salesSlipHeadBean.slipId"/>
		<table border="1">
			<c:if test="${!empty salesSlipBean.salesSlipHeadBean.slipId}">
				<tr>
					<th colspan="1">伝票番号</th>
					<td colspan="3"><fmt:formatNumber value="${salesSlipBean.salesSlipHeadBean.slipId}" pattern="000000"/></td>
				</tr>
			</c:if>
			<tr>
				<th>日付※</th>
				<td colspan="3">
					<form:input path="salesSlipHeadBean.day" maxlength="10"/>
					<font color="red"><form:errors path="salesSlipHeadBean.day"/></font>
				</td>
			</tr>
			<tr>
				<th>従業員※</th>
				<td>
					<form:select path="salesSlipHeadBean.employeeId">
						<form:option value=""/>
						<form:options items="${staff}" itemValue="employeeId" itemLabel="name"/>
					</form:select>
					<font color="red"><form:errors path="salesSlipHeadBean.employeeId"/></font>
				</td>
				<th>会員ID</th>
				<td><form:input path="salesSlipHeadBean.memberId" maxlength="10"/></td>
			</tr>
			<tr>
				<th>イン※</th>
				<td>
					<form:input path="salesSlipHeadBean.startTime" maxlength="5"/>
					<font color="red"><form:errors path="salesSlipHeadBean.startTime"/></font>
				</td>
				<th>アウト※</th>
				<td>
					<form:input path="salesSlipHeadBean.endTime" maxlength="5"/>
					<font color="red"><form:errors path="salesSlipHeadBean.endTime"/></font>
				</td>
			</tr>
		</table>
		<br/>
		
		<table border="1">
			<tr>
				<th>コース分類※</th>
				<td colspan="2">
					<form:select path="salesSlipHeadBean.courseClassId" onchange="moveForForm('/sales/changeCourseClass.html')">
						<form:option value=""/>
						<form:options items="${courseClass}" itemValue="courseClassId" itemLabel="name"/>
					</form:select>
					<font color="red"><form:errors path="salesSlipHeadBean.courseClassId"/></font>
				</td>
			</tr>
			<tr>
				<th>コース※</th>
				<td>
					<form:select path="salesSlipHeadBean.courseId" onchange="moveForForm('/sales/changeCourse.html')">
						<form:option value=""/>
						<form:options items="${course}" itemValue="courseId" itemLabel="name"/>
					</form:select>
					<font color="red"><form:errors path="salesSlipHeadBean.courseId"/></font>
				</td>
				<td align="right">
					<fmt:formatNumber value="${salesSlipBean.salesSlipHeadBean.courseCharge}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/>
					<form:hidden path="salesSlipHeadBean.courseCharge"/>
				</td>
			</tr>
			<tr>
				<th>延長</th>
				<td>
					<form:select path="salesSlipHeadBean.courseExtensionId" onchange="moveForForm('/sales/changeCourseExtension.html')">
						<form:option value=""/>
						<form:options items="${courseExtension}" itemValue="courseExtensionId" itemLabel="name"/>
					</form:select>
					<font color="red"><form:errors path="salesSlipHeadBean.courseExtensionId"/></font>
				</td>
				<td align="right">
					<fmt:formatNumber value="${salesSlipBean.salesSlipHeadBean.courseExtensionCharge}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/>
					<form:hidden path="salesSlipHeadBean.courseExtensionCharge"/>
				</td>
			</tr>
			<tr>
				<th>指名</th>
				<td>
					<form:select path="salesSlipHeadBean.appointId" onchange="moveForForm('/sales/changeAppoint.html')">
						<form:option value=""/>
						<form:options items="${appoint}" itemValue="appointId" itemLabel="name"/>
					</form:select>
					<font color="red"><form:errors path="salesSlipHeadBean.appointId"/></font>
				</td>
				<td align="right">
					<fmt:formatNumber value="${salesSlipBean.salesSlipHeadBean.appointCharge}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/>
					<form:hidden path="salesSlipHeadBean.appointCharge"/>
				</td>
			</tr>
		</table>
		<br/>

		<table border="1">
			<tr>
				<th>No</th>
				<th>オプション</th>
				<th>料金</th>
			</tr>
			<c:forEach begin="0" end="1" varStatus="status">
				<tr>
					<%--No--%>
					<td align="right"><c:out value="${status.index + 1}"/></td>
					<%--オプション--%>
					<td>
						<form:select path="salesSlipOptionmenuList[${status.index}].optionmenuId" onchange="moveForForm('/sales/changeOptionmenu${status.index}.html')">
							<form:option value=""/>
							<form:options items="${optionmenuList[status.index]}" itemValue="optionmenuId" itemLabel="name"/>
						</form:select>
					</td>
					<%--料金--%>
					<td align="right">
						<fmt:formatNumber value="${salesSlipBean.salesSlipOptionmenuList[status.index].charge}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/>
						<form:hidden path="salesSlipOptionmenuList[${status.index}].charge"/>
					</td>
				</tr>
			</c:forEach>
		</table>

		<c:out value="売上合計"/>	&nbsp;
		<fmt:formatNumber value="${salesSlipBean.salesTotal}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/>
		<br/>
		<br/>	
		
		<table border="1">
			<tr>
				<th>No</th>
				<th>割引</th>
				<th>料金</th>
			</tr>
			<c:forEach begin="0" end="3" varStatus="status">
				<tr>
					<%--No--%>
					<td align="right"><c:out value="${status.index + 1}"/></td>
					<%--割引--%>
					<td>
						<form:select path="salesSlipDiscountList[${status.index}].discountId" onchange="moveForForm('/sales/changeDiscount${status.index}.html')">
							<form:option value=""/>
							<form:options items="${discountList[status.index]}" itemValue="discountId" itemLabel="name"/>
						</form:select>
					</td>
					<%--料金--%>
					<td align="right">
						<fmt:formatNumber value="${salesSlipBean.salesSlipDiscountList[status.index].charge}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/>
						<form:hidden path="salesSlipDiscountList[${status.index}].charge"/>
					</td>
				</tr>
			</c:forEach>
		</table>

		<c:out value="割引合計"/>&nbsp;
		<fmt:formatNumber value="${salesSlipBean.discountTotal}" type="CURRENCY" currencySymbol="" maxFractionDigits="0" minFractionDigits="0"/>

		<font color="red">
			<spring:hasBindErrors name="salesSlipBean">
					<c:forEach items="${errors.globalErrors}" var="error">
						<spring:message code="${error.code}"/><br/>
					</c:forEach>
			</spring:hasBindErrors>
		</font>
		<br/>
		<br/>

		<c:choose>
			<c:when test="${empty salesSlipBean.salesSlipHeadBean.slipId}">
				<input class="normal" type="submit" value="登録"/>
			</c:when>
			<c:otherwise>
				<c:if test="${empty closeHeadBean}"><input class="normal" type="submit" value="修正"/></c:if>
				<input type="button" class="normal" value="戻る" onclick="move('/sales/list.html'); return false;"/>
			</c:otherwise>
		</c:choose>
	</form:form>
	</div>
</body>
</html>