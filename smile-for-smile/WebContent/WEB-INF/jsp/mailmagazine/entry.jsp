<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<title>メルマガ</title>
<script type="text/javascript" src="/css/script.js"></script>
</head>
<body>

	<div id="main">
	<form:form name="form">
		<h2>メルマガ登録</h2>

		<table>
			<tr>
				<th>メールアドレス</th>
				<td><input type="text"/></td>
			</tr>
		</table>
		<input type="button" class="normal" value="登録" onclick="move('/mailmagazine/doEntry.html'); return false;"/>
		<input type="button" class="normal" value="解除" onclick="move('/mailmagazine/doEelete.html'); return false;"/>
		<input type="button" class="normal" value="送信テスト" onclick="move('/mailmagazine/doTest.html'); return false;"/>

	</form:form>
	</div>
</body>
</html>