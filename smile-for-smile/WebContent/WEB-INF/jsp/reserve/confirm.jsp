<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>インターネット予約</title>
<script type="text/javascript" src="/css/script.js"></script>
</head>
<body>

	<div id="main">
		<h2>インターネット予約 確認フォーム</h2>
		<form:form name="form" modelAttribute="reserveBean" action="/reserve/doReserve.html">
			<table>
				<tr>
					<th>お名前</th>
					<td>
						<form:input disabled="true" path="name" size="30"/>様
						<form:hidden path="name"/>
					</td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td><form:input disabled="true" path="mail" size="60"/>
						<form:hidden path="mail"/>
					</td>
				</tr>
				<tr>
					<th>お電話番号</th>
					<td>
						<form:input disabled="true" path="phone" size="20"/>
						<form:hidden path="phone"/>
					</td>
				</tr>
				<tr>
					<th>ご予約人数</th>
					<td>
						<form:select disabled="true" path="amount">
							<form:option value=""/>
							<form:options items="${amount}" itemValue="value" itemLabel="label"/>
						</form:select>
						<form:hidden path="amount"/>
					</td>
				</tr>
				<tr>
					<th>ご予約日</th>
					<td>
						<form:select disabled="true" path="day">
							<form:option value=""/>
							<form:options items="${day}" itemValue="value" itemLabel="label"/>
						</form:select>
						<form:hidden path="day"/>
					</td>
				</tr>
				<tr>
					<th>ご予約時間</th>
					<td>
						<form:select disabled="true" path="time">
							<form:option value=""/>
							<form:options items="${time}" itemValue="value" itemLabel="label"/>
						</form:select>
						<form:hidden path="time"/>
					</td>
				</tr>
				<tr>
					<th>ご要望 <br/>(ご希望コース、ご希望スタッフ等<br/>はこちらに記入をお願いします)</th>
					<td>
						<form:textarea disabled="true" rows="5" cols="60" path="notes"/>
						<form:hidden path="notes"/>
					</td>
				</tr>
	
			</table>
			<input type="submit" class="normal" value="予約"/>
			<br/>
			予約内容の修正を行う場合は、ブラウザの戻るボタンでお戻り下さい
		</form:form>
	</div>
</body>
</html>