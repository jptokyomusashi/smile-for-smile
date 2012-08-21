<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>アドレス情報変更</title>
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
	<form:form name="form" modelAttribute="mailCustomerBean" method="post" action="/mail/doEntry.html">
		<h2>アドレス情報変更</h2>

		<table>
			<tr>
				<th>ID</th>
				<td>
					<c:out value="${mailCustomerBean.id}"/>
					<form:hidden path="id"/>
				</td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td>
					<c:out value="${mailCustomerBean.mailAddress}"/>
				</td>
			</tr>
			<tr>
				<th>名前</th>
				<td><form:input path="name" maxlength="20"/></td>
			</tr>
			<tr>
				<th>解除</th>
				<td>
					<c:choose>
						<c:when test="${empty mailCustomerBean.deleted}">
							<c:out value=""/>
						</c:when>
						<c:otherwise>
							<c:out value="解除"/>
						</c:otherwise>
					</c:choose>
					<form:hidden path="deleted"/>
				</td>
			</tr>
		</table>

		<input class="normal" type="submit" value="登録"/>
		<input type="button" class="normal" value="戻る" onclick="moveForForm('/mail/list.html'); return false;"/>
	</form:form>
	</div>
</body>
</html>