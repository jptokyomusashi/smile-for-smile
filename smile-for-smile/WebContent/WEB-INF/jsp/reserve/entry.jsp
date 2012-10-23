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
		<h2>インターネット予約 受付フォーム</h2>
		<font color="#777777">
		7日先までのご予約が可能です。(当日のご予約はお電話にてお願い致します。)<br/>
		フォーム送信後、当店予約担当スタッフよりご返信致します。この返信でご予約成立となります。<br/>
		フォームを送信して頂いた段階ではご予約は成立しておりませんのでご注意願います。<br/>
		万一24時間以内に当店予約担当スタッフから返信が無い場合はお手数ですが、お電話にてご確認頂くか、再度フォームの記入をお願い致します。
		</font>
		<br/><br/>
		<form:form name="form" modelAttribute="reserveBean" action="/reserve/doConfirm.html">
			<font color="red">
				<form:errors path="*"/>
			</font>

			<table>
				<tr>
					<th>お名前<font color="red">(必須)</font><br/><font color="#777777">※匿名でも可</font></th>
					<td><form:input path="name" size="30"/>様</td>
				</tr>
				<tr>
					<th>メールアドレス<font color="red">(必須)</font></th>
					<td><form:input path="mail" size="60"/></td>
				</tr>
				<tr>
					<th>お電話番号</th>
					<td><form:input path="phone" size="20"/></td>
				</tr>
				<tr>
					<th>ご予約人数<font color="red">(必須)</font></th>
					<td>
						<form:select path="amount">
							<form:option value=""/>
							<form:options items="${amount}" itemValue="value" itemLabel="label"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<th>ご予約日<font color="red">(必須)</font></th>
					<td>
						<form:select path="day">
							<form:option value=""/>
							<form:options items="${day}" itemValue="value" itemLabel="label"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<th>ご予約時間<font color="red">(必須)</font></th>
					<td>
						<form:select path="time">
							<form:option value=""/>
							<form:options items="${time}" itemValue="value" itemLabel="label"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<th>ご希望コース<font color="red">(必須)</font></th>
					<td>
						<form:select path="course">
							<form:option value=""/>
							<form:options items="${course}" itemValue="value" itemLabel="label"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<th>ご要望 <br/><font color="#777777">※スタッフ指名はこちらで</font></th>
					<td>
						<form:textarea rows="5" cols="60" path="notes"/>
					</td>
				</tr>
	
			</table>
			<input type="submit" value="確認"/>

		</form:form>
	</div>
</body>
</html>