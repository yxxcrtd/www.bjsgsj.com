<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/xml; charset=UTF-8" />
		<title>北京尚古中和装饰设计有限公司</title>
		<meta name="keywords" content="中式, 中式设计, 中式装修, 中式设计公司, 北京中式设计公司" />
		<meta name="description" content="中式, 中式设计, 中式装修, 中式设计公司, 北京中式设计公司" />
		<link rel="icon" href="./images/home.ico" />
		<link rel="styleSheet" type="text/css" href="./css/style.css" />
		<link rel="styleSheet" type="text/css" href="./css/jquery.fancybox.css" />
		<script language="javaScript" src="./js/jquery.js"></script>
		<script language="javaScript" src="./js/jquery.fancybox.js"></script>
		<script language="javaScript" src="./js/index.js"></script>
	</head>

	<body>
		<#-- 整个页面容器 -->
		<div id="container">
		
			<#-- 顶部的背景图片 -->
			<div id="top"></div>
			
			<#-- 导航菜单 -->
			<#include "include/Menu.ftl">
			
			<div class="gap_row"></div>
			
			<#-- 主要内容区域（公司介绍、精品图片展示和最新(图片)动态） -->
			<div id="content">
				<#include "include/Show.ftl">
			</div>
			
			<div class="gap_row"></div>
			
			<#-- 公司的服务宗旨 -->
			<div id="banner"></div>
			
			<div class="gap_row"></div>
			
			<#-- 案例展示 -->
			<#include "include/Case.ftl">
			
			<#-- 我们的节奏 -->
			<#include "include/Flow.ftl">
			
			<div class="gap_row"></div>
			
			<#-- 合作伙伴 -->
			<#include "include/Corperation.ftl">
		</div>
		
		<div class="gap_row"></div>
		
		<#-- 页脚的版权信息 -->
		<#include "include/Footer.ftl">
	</body>
</html>
