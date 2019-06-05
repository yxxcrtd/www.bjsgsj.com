<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/xml; charset=UTF-8" />
		<title>北京尚古中和装饰设计有限公司</title>
		<meta name="keywords" content="<#if "villa" == cmd>中式别墅设计<#elseif "equipment" == cmd>中式公装设计 | 中式会所设计 | 中式茶楼设计 | 中式酒店设计 | 中式公馆设计 | 中式大厦设计 | 中式办公室设计<#elseif "home" == cmd>中式家装设计 | 中式复式设计<#elseif "garden" == cmd>中式四合院设计 | 古建园林设计<#elseif "decoration" == cmd>中式配饰设计</#if>" />
		<meta name="description" content="中式, 中式设计, 中式装修, 中式设计公司, 北京中式设计公司" />
		<link rel="styleSheet" type="text/css" href="./css/style.css" />
		<link rel="styleSheet" type="text/css" href="./css/jquery.fancybox.css" />
	</head>

	<body>
		<div id="container">
		
			<#-- 顶部的背景图片 -->
			<div id="top"></div>
			
			<#include "include/Menu.ftl">
			
			<div class="gap_row">&nbsp;</div>
			
			<div id="content">
				<div id="content_left">
					<div id="content_left_top">
						<div class="title2"><#if "villa" == cmd>中式别墅<#elseif "equipment" == cmd>中式公装<#elseif "home" == cmd>中式家装<#elseif "garden" == cmd>古建园林<#elseif "decoration" == cmd>中式配饰</#if></div>
					</div>
					<div id="content_left_bottom">
						<ul>
							<li class="tabin">
								<strong>案例精选</strong>
							</li>
							<#if projectList?? && 0 != projectList?size>
								<#list projectList as project>
									<li>
										${project.name!}
									</li>
								</#list>
							</#if>
						</ul>
					</div>
				</div>
				
				<div class="gap_column">&nbsp;</div>
				
				<div id="content_right">
					<div class="div_top">
						<span class="corner_left_top"></span>
						<span class="div_middle_top"></span>
						<span class="corner_right_top"></span>
					</div>
					
					<div class="div_middle">
						<span class="div_middle_middle">
							<div class="contentin">
								<#if pictureList?? && 0 != pictureList?size>
									<#list pictureList as picture>
										<br />
										<a href="./upload/${picture.link!}" rel="project_group" title="点击查看大图">
											<img class="img" src="upload/n_${picture.link}" alt="${picture.describe!}" /><br /><br />
											${picture.describe!}<br /><br /><br /><br />
										</a>
									</#list>
								</#if>
							</div>
					
							<#-- 分类循环 -->
							<#if projectList?? && 0 != projectList?size>
								<#list projectList as project>
									<div class="content">
										<div class="project_describe">${project.describe!}</div>
										<#if allPictureList?? && 0 != allPictureList?size>
											<#list allPictureList as all>
												<#if (project.projectId == all.projectId)>
													<br />
													<a href="./upload/${all.link!}" rel="project_group" title="点击查看大图">
														<img class="img" src="upload/n_${all.link}" alt="${all.describe!}" /><br /><br />
														${all.describe!}<br /><br /><br />
													</a>
												</#if>
											</#list>
										</#if>
									</div>
								</#list>
							</#if>
						</span>
					</div>
					
					<div class="div_bottom">
						<span class="corner_left_bottom"></span>
						<span class="div_middle_bottom"></span>
						<span class="corner_right_bottom"></span>
					</div>
				</div>
			</div>
		</div>
			
		<div class="gap_row"></div>
		
		<#include "include/Footer.ftl">
		
		<script language="javaScript" src="./js/jquery.js"></script>
		<script language="javaScript" src="./js/jquery.mousewheel.js"></script>
		<script language="javaScript" src="./js/jquery.fancybox.js"></script>
		<script language="javaScript" src="./js/project.js"></script>
		<script language="javaScript" src="./js/index.js"></script>
	</body>
</html>
