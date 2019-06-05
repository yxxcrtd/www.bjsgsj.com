<div id="message">
	<#if messageList?? && 0 != messageList?size>
		<div class="gap_row">&nbsp;</div>
		<div class="gap_row">&nbsp;</div>
		<div class="gap_row">&nbsp;</div>
		<div id="form_title">最新留言：</div>
	</#if>

	<#if messageList?? && 0 != messageList?size>
		<#list messageList as message>
			<div class="form_item">
				<div class="message_people">
					<span class="post"></span>留言人：${message.name!}&nbsp;&nbsp;(发表于：${message.time?string("yyyy-MM-dd HH:mm:ss")})
				</div>
				<div class="message_content">
					${message.content!}
				</div>
				<#if message.reply??>
					<div class="message_reply">
						${message.reply!}
					</div>
				</#if>
			</div>
			<#if message_has_next>
				<div class="gap_row">&nbsp;</div>
			</#if>
		</#list>
	</#if>
</div>