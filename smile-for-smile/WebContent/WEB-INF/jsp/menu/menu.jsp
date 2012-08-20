<form:form name="menuform" method="post">
	<ul>
		<li><a href="#"><c:out value="売上伝票"/></a>
			<ul>
				<li><a href="" onclick="move('/sales/newEntry.html'); return false;">新規登録</a></li>
				<li><a href="" onclick="move('/sales/search.html'); return false;">検索／修正</a></li>
			</ul>
		</li>

		<li><a href="#"><c:out value="締め登録"/></a>
			<ul>
				<li><a href="" onclick="move('/close/search.html'); return false;">締め登録</a></li>
			</ul>
		</li>

		<li><a href="#"><c:out value="支払伝票"/></a>
			<ul>
				<li><a href="" onclick="move('/payment/newEntry.html'); return false;">新規登録</a></li>
				<li><a href="" onclick="move('/payment/search.html'); return false;">検索／修正</a></li>
			</ul>
		</li>

		<li><a href="#"><c:out value="売上集計表"/></a>
			<ul>
				<li><a href="" onclick="move('/salessummary/search.html'); return false;">売上集計表</a></li>
			</ul>
		</li>

		<li><a href="#"><c:out value="メルマガ"/></a>
			<ul>
				<li><a href="" onclick="move('/mail/setting.html'); return false;">メルマガ作成</a></li>
				<li><a href="" onclick="move('/mail/setting.html'); return false;">登録アドレス確認</a></li>
				<li><a href="" onclick="move('/mail/setting.html'); return false;">設定</a></li>
			</ul>
		</li>

		<li><a href="#"><c:out value="マスタ管理"/></a>
			<ul>
				<li><a href="" onclick="move('/employee/newEntry.html'); return false;">従業員新規登録</a></li>
				<li><a href="" onclick="move('/employee/list.html'); return false;">従業員一覧</a></li>
			</ul>
		</li>

	</ul>
</form:form>
