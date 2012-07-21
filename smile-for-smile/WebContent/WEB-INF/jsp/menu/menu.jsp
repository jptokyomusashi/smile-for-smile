<div>
	&nbsp;&nbsp;&nbsp;&nbsp;
	<c:out value="メニュー"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<br/>
	<br/>

	<form:form name="menuform" method="post">
		<table>
			<tr><td><c:out value="売上伝票"/></td></tr>
			<tr><td>&nbsp;&nbsp;<a href="" onclick="move('/sales/newEntry.html'); return false;">新規登録</a></td></tr>
			<tr><td>&nbsp;&nbsp;<a href="" onclick="move('/sales/search.html'); return false;">検索／修正</a></td></tr>
			<tr><td><hr/></td></tr>
			<tr><td><c:out value="締め登録"/></td></tr>
			<tr><td>&nbsp;&nbsp;<a href="" onclick="move('/close/search.html'); return false;">締め登録</a></td></tr>
			<tr><td><hr/></td></tr>
			<tr><td><c:out value="支払伝票"/></td></tr>
			<tr><td>&nbsp;&nbsp;<a href="" onclick="move('/payment/newEntry.html'); return false;">新規登録</a></td></tr>
			<tr><td>&nbsp;&nbsp;<a href="" onclick="move('/payment/search.html'); return false;">検索／修正</a></td></tr>
			<tr><td><hr/></td></tr>
			<tr><td><c:out value="売上集計表"/></td></tr>
			<tr><td>&nbsp;&nbsp;<a href="" onclick="move('/salessummary/search.html'); return false;">売上集計表</a></td></tr>
		</table>
	</form:form>
</div>
