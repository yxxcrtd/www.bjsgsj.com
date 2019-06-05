<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><@s.text name="system.menu.message" /></title>
        <link type="text/css" rel="styleSheet" href="./css/manage.css" />
	</head>
	
	<body>
		<@s.form action="message">
  			<table class="table" cellSpacing="1">
  				<thead>
	  				<tr>
	  					<td colSpan="2">
							<div id="header">留言信息修改</div>
						</td>
  					</tr>
  				</thead>
  			
	  			<tbody>
	  				<tr height="35">
	  					<td class="right" width="20%">
	  						<strong><@s.label value="信息ID：" /></strong>
	  					</td>
	  					<td class="left">
	  						<strong>${message.messageId}</strong>
	  					</td>
	  				</tr>
	  				<tr height="35">
	  					<td class="right">
	  						<strong><@s.label value="姓或姓名：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.textfield name="message.name" cssStyle="width: 600px;" />
	  					</td>
	  				</tr>
	  				<tr height="35">
	  					<td class="right">
	  						<strong><@s.label value="手机或固定电话：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.textfield name="message.telephone" cssStyle="width: 600px;" />
	  					</td>
	  				</tr>
	  				<tr height="35">
	  					<td class="right">
	  						<strong><@s.label value="留言内容：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.textfield name="message.content" cssStyle="width: 600px;" />
	  					</td>
	  				</tr>
	  				<tr height="35">
	  					<td class="right">
	  						<strong><@s.label value="本站回复：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.textarea name="message.reply" cssStyle="width: 600px; height: 100px; resize: none;" maxlength="200"></@s.textarea>
	  					</td>
	  				</tr>
	  				<tr height="35">
	  					<td class="right">
	  						<strong><@s.label value="留言时间：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.textfield name="message.time" cssStyle="width: 600px;" />
	  					</td>
	  				</tr>
	  				<tr height="35">
	  					<td class="right">
	  						<strong><@s.label value="留言IP：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.textfield name="message.ip" cssStyle="width: 600px;" />
	  					</td>
	  				</tr>
					<tr height="50" align="center">
						<td colSpan="2">
							<input id="btn_submit" type="submit" class="btn" value="保  存" />&nbsp;&nbsp;
							<input type="button" class="btn" onClick="javascript:history.back();" value="取  消" />
						</td>
					</tr>
	  			</tbody>
	  		</table>
	  		<@s.hidden name="cmd" value="save" />
            <@s.hidden name="message.messageId" value="${message.messageId}" />
            <@s.hidden name="k" value="${k!?html}" />
  		</@s.form>
        
        <script language="javaScript" src="./js/jquery.js"></script>
        <script language="javaScript" src="./js/public.js"></script>
	</body>
</html>
