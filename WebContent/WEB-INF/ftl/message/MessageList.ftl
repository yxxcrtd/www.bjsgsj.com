<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><@s.text name="system.menu.message" /></title>
        <link type="text/css" rel="styleSheet" href="./css/manage.css" />
	</head>
	
	<body>
		<div class="tableSearch">
			<@s.form name="search_form" action="message" method="get">
				<@s.hidden name="cmd" value="list" />
				<@s.textfield name="k" value="${k!?html}" onmouseover="this.select();" /> 
				<@s.submit cssClass="btn" value="%{getText('system.button.search')}" />
			</@s.form>
		</div>
		
  		<table class="table" cellSpacing="1">
  			<thead>
	  			<tr>
					<th width="6%">ID</th>
					<th width="12%" class="left">留言姓名</th>
					<th width="12%" class="left">联系方式</th>
					<th width="23%" class="left">留言内容</th>
					<th width="23%" class="left">本站回复</th>
					<th width="14%">IP/留言时间</th>
					<th width="10%">操作</th>
  				</tr>
  			</thead>
  			
  			<tbody>
				<#if messageList?size == 0>
            		<tr>
            			<td colSpan="9" class="tip">没有记录！</td>
            		</tr>
				</#if>
				<#list messageList as message>
					<tr bgColor="#FFFFFF" onMouseOver="changeBgColor(this, '#F4F4F4')" onMouseOut="changeBgColor(this, '#FFFFFF')">
						<td class="center">
							<strong>${message.messageId!}</strong>
						</td>
						<td class="left">
							${message.name!}
						</td>
						<td class="left">
							${message.telephone!}
						</td>
						<td class="left">
							<div class="message_title">
								${message.title!}
							</div>
							${message.content!}
						</td>
						<td class="left">
							${message.reply!}
						</td>
						<td class="left">
							${message.ip}
							<br />
							${message.time}
						</td>
						<td class="center">
							<@s.a href="?cmd=edit&amp;message.messageId=${message.messageId}" title="编辑">编辑</@s.a>
							<@s.a href="?cmd=del&amp;message.messageId=${message.messageId}" title="删除" onclick="return confirm('确定要删除当前信息吗？');">删除</@s.a>
						</td>
					</tr>
				</#list>
  			</tbody>
  		</table>
        
        <#if messageList?? && messageList?size != 0>
            <@s.div id="pager">
                <#include "../Pager.ftl">
            </@s.div>
        </#if>
        
        <script language="javaScript" src="./js/jquery.js"></script>
        <script language="javaScript" src="./js/public.js"></script>
	</body>
</html>
