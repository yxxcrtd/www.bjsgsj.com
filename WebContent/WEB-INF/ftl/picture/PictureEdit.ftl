<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><@s.text name="system.menu.picture" /></title>
        <link type="text/css" rel="styleSheet" href="./css/manage.css" />
	</head>
	
	<body>
		<@s.form action="picture" enctype="multipart/form-data">
  			<table class="table" cellSpacing="1">
  				<thead>
	  				<tr>
	  					<td colSpan="2">
							<div id="header">${(picture.pictureId == 0)?string('发布', '修改')}项目图片</div>
						</td>
  					</tr>
  				</thead>
	  			<tbody>
	  				<tr height="40">
	  					<td class="right" width="20%">
	  						<strong><@s.label value="图片地址：" /></strong>
	  					</td>
	  					<td id="first" class="left">
	  						<#if (picture.pictureId == 0)>
								<@s.file name="file" size="75" cssStyle="width: 605px;" onchange="priview(this);" />
								<img id="img" src="./images/blank.gif" />
							<#else>
								<img src="upload/c_${picture.link!}" />
								<@s.hidden name="picture.link" />
							</#if>
	  					</td>
	  				</tr>
	  				<tr height="40">
	  					<td class="right">
	  						<strong><@s.label for="describe" value="图片描述：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.textfield id="describe" name="picture.describe" cssStyle="width: 600px;" maxlength="22" />
	  					</td>
	  				</tr>
	  				<tr height="40">
	  					<td class="right">
	  						<strong><@s.label for="parent" value="图片分类：" /></strong>
	  					</td>
	  					<td class="left">
			  				<@s.radio name="picture.project.projectId" listKey="projectId" listValue="name" list="projectList" />
	  					</td>
	  				</tr>
  					<tr height="40">
	  					<td class="right">
	  						<strong><@s.label for="cases" value="是否作为案例展示：" /></strong>
	  					</td>
	  					<td class="left">
	  						<@s.checkbox id="cases" name="picture.cases"></@s.checkbox>
	  					</td>
  					</tr>
					<tr height="50" align="center">
						<td colSpan="2">
							<input type="submit" id="btn_submit" class="btn" value="${(picture.pictureId == 0)?string('保  存', '修  改')}" />&nbsp;&nbsp;
							<input type="button" class="btn" onClick="javascript:history.back();" value="取  消" />
						</td>
					</tr>
	  			</tbody>
	  		</table>
	  		<@s.hidden name="cmd" value="save" />
            <@s.hidden name="k" value="${k!?html}" />
            <@s.hidden name="picture.pictureId" value="${picture.pictureId}" />
  		</@s.form>
        
        <script language="javaScript" src="./js/jquery.js"></script>
        <script language="javaScript" src="./js/public.js"></script>
        <script language="javaScript" src="./js/picture.js"></script>
	</body>
</html>
