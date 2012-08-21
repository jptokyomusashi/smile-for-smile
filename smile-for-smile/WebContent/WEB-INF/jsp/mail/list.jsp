<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>登録アドレス一覧</title>
<script type="text/javascript" src="/css/script.js">
</script>
<script type="text/javascript">
function toEntry(id) {
	document.form.action = '/mail/toEntry.html';
	document.form.method = 'POST';
	document.form.id.value = id;
	document.form.submit();
}
</script>

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
			<h2>登録アドレス一覧</h2>
	
			<c:out value="${size}件"/>
			<table>
				<tr>
					<th>ID</th>
					<th>メールアドレス</th>
					<th>名前</th>
					<th>解除</th>
				</tr>
				<c:forEach items="${mailCustomerList}" var="mailCustomerBean" varStatus="status">
					<tr>
						<td><a href="" onclick="toEntry('${mailCustomerBean.id}'); return false;"><c:out value="${mailCustomerBean.id}"/></a></td>
						<td><c:out value="${mailCustomerBean.mailAddress}"/></td>
						<td><c:out value="${mailCustomerBean.name}"/></td>
						<td>
							<c:choose>
								<c:when test="${empty mailCustomerBean.deleted}">
									<c:out value=""/>
								</c:when>
								<c:otherwise>
									<c:out value="解除"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</table>
			<c:out value="${size}件"/>
			<input type="hidden" name="id"/>
		</form:form>
	</div>
</body>
</html>