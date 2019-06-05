<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><@s.text name="system.menu.picture" /></title>
        <link type="text/css" rel="styleSheet" href="./css/manage.css" />
        <script language="javaScript" src="./js/jquery.js"></script>
        <script language="javaScript" src="./js/public.js"></script>
	</head>
	
	<body>
		<div class="tableSearch">
			<@s.form name="search_form" action="picture" method="get">
				<@s.hidden name="cmd" value="list" />
				<@s.textfield name="k" value="${k!?html}" onmouseover="this.select();" /> 
				<@s.submit cssClass="btn" value="%{getText('system.button.search')}" />
			</@s.form>
		</div>
		
  		<table class="table" cellSpacing="1">
  			<thead>
	  			<tr>
					<th width="15%">图片ID</th>
					<th width="30%">缩略图</th>
					<th width="30%" class="left">项目分类/图片分类/图片介绍</th>
					<th width="10%">是否作为案例展示</th>
					<th width="15%">操&nbsp;作</th>
  				</tr>
  			</thead>
  			
  			<tbody>
				<#if pictureList?size == 0>
            		<tr>
            			<td colSpan="9" class="tip">没有记录！</td>
            		</tr>
				</#if>
				<#list pictureList as picture>
					<tr bgColor="#FFFFFF" onMouseOver="changeBgColor(this, '#F4F4F4');" onMouseOut="changeBgColor(this, '#FFFFFF');">
						<td class="center">
							<strong>${picture.pictureId}</strong>
						</td>
						<td class="center">
							<img src="upload/c_${picture.link}" />
						</td>
						<td class="left">
							项目分类：
							<#switch picture.project.parent>
								<#case "villa"><font style="color: #FF0000;">中式别墅</font><#break>
								<#case "equipment"><font style="color: #0000FF;">中式公装</font><#break>
								<#case "home"><font style="color: #00FF00;">中式家装</font><#break>
								<#case "garden"><font style="color: #FF00FF;">古建园林</font><#break>
								<#case "decoration"><font style="color: #00FFFF;">中式配饰</font><#break>
								<#default><font color="#FF0000"><h3>出错了！</h3></font>
							</#switch>
							<br /><br />
							图片分类：<font style="background-color: #E6DBC0;">${picture.project.name}</font>
							<br /><br />
							图片介绍：<font style="background-color: #E6DBC0;">${picture.describe!}</font>
						</td>
						<td class="center">
							<#if picture.cases>是
							<#else>否
							</#if>
						</td>
						<td class="center">
							<@s.a href="?cmd=edit&amp;picture.pictureId=${picture.pictureId}" title="编辑">编辑</@s.a>&nbsp;&nbsp;&nbsp;&nbsp;
							<@s.a href="?cmd=del&amp;picture.pictureId=${picture.pictureId}" title="删除" onclick="return confirm('确定要删除当前信息吗？');">删除</@s.a>
						</td>
					</tr>
				</#list>
  			</tbody>
  		</table>
        
        <#if pictureList?? && pictureList?size != 0>
            <@s.div id="pager">
                <#include "../Pager.ftl">
            </@s.div>
        </#if>
	</body>
</html>
