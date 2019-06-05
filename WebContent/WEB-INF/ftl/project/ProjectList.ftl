<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><@s.text name="system.menu.project" /></title>
        <link type="text/css" rel="styleSheet" href="./css/manage.css" />
        <script language="javaScript" src="./js/jquery.js"></script>
        <script language="javaScript" src="./js/public.js"></script>
	</head>
	
	<body>
		<div class="tableSearch">
			<@s.form name="search_form" action="project" method="get">
				<@s.hidden name="cmd" value="list" />
				<@s.textfield name="k" value="${k!?html}" onmouseover="this.select();" /> 
				<@s.submit cssClass="btn" value="%{getText('system.button.search')}" />
			</@s.form>
		</div>
		
  		<table class="table" cellSpacing="1">
  			<thead>
	  			<tr>
					<th width="10%">项目ID</th>
					<th width="12%" class="left">项目父分类</th>
					<th width="18%" class="left">项目中文名称</th>
					<th width="50%" class="left">项目描述</th>
					<th width="10%" class="center">操&nbsp;作</th>
  				</tr>
  			</thead>
  			
  			<tbody>
				<#if projectList?size == 0>
            		<tr>
            			<td colSpan="9" class="tip">没有记录！</td>
            		</tr>
				</#if>
				<#list projectList as project>
					<tr bgColor="#FFFFFF" onMouseOver="changeBgColor(this, '#F4F4F4')" onMouseOut="changeBgColor(this, '#FFFFFF')">
						<td class="center">
							<strong>${project.projectId}</strong>
						</td>
						<td class="left">
							<#switch project.parent>
								<#case "villa"><font style="color: #FF0000;">中式别墅</font><#break>
								<#case "equipment"><font style="color: #0000FF;">中式公装</font><#break>
								<#case "home"><font style="color: #00FF00;">中式家装</font><#break>
								<#case "garden"><font style="color: #FF00FF;">古建园林</font><#break>
								<#case "decoration"><font style="color: #00FFFF;">中式配饰</font><#break>
								<#default><font color="#FF0000"><h3>出错了！</h3></font>
							</#switch>
						</td>
						<td class="left">
							${project.name!}
						</td>
						<td class="left">
							${project.describe}
						</td>
						<td class="center">
							<@s.a href="?cmd=edit&amp;project.projectId=${project.projectId}" title="编辑">编辑</@s.a>
						</td>
					</tr>
				</#list>
  			</tbody>
  		</table>
        
        <#if projectList?? && projectList?size != 0>
            <@s.div id="pager">
                <#include "../Pager.ftl">
            </@s.div>
        </#if>
	</body>
</html>
