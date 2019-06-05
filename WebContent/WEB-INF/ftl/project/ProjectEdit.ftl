<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><@s.text name="system.menu.project" /></title>
        <link type="text/css" rel="styleSheet" href="./css/manage.css" />
	</head>
	
	<body>
		<@s.form action="project">
  			<table class="table" cellSpacing="1">
  				<thead>
	  				<tr>
	  					<td colSpan="2">
							<div id="header">${(project.projectId == 0)?string('新建', '修改')}项目</div>
						</td>
  					</tr>
  				</thead>
	  			<tbody>
	  				<tr height="35">
	  					<td class="right" width="25%">
	  						<strong><@s.label for="parent" value="项目分类：" /></strong>
	  					</td>
	  					<td class="left">
			  				<@s.radio id="parent" name="project.parent" value="%{project.parent}" list=r"#{'villa':'中式别墅', 'equipment':'中式公装', 'home':'中式家装', 'garden':'古建园林', 'decoration':'中式配饰'}" required="false" theme="simple" />
	  					</td>
	  				</tr>
	  				<tr height="35">
	  					<td class="right">
	  						<strong><@s.label for="name" value="项目中文名称：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.textfield id="name" name="project.name" maxlength="15" cssStyle="width: 500px;" />
	  					</td>
	  				</tr>
	  				<tr height="35">
	  					<td class="right">
	  						<strong><@s.label for="describe" value="项目描述：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.textarea id="describe" name="project.describe" cols="61" rows="7" maxlength="150"></@s.textarea>
	  					</td>
	  				</tr>
					<tr height="50" align="center">
						<td colSpan="2">
							<input type="submit" id="btn_submit" class="btn" value="<#if project.projectId == 0>保  存<#else>修  改</#if>" />&nbsp;&nbsp;
							<input type="button" class="btn" onClick="javascript:history.back();" value="取  消" />
						</td>
					</tr>
	  			</tbody>
	  		</table>
	  		<@s.hidden name="cmd" value="save" />
            <@s.hidden name="project.projectId" value="${project.projectId}" />
            <@s.hidden name="k" value="${k!?html}" />
  		</@s.form>
        
        <script language="javaScript" src="./js/jquery.js"></script>
        <script language="javaScript" src="./js/public.js"></script>
	</body>
</html>
