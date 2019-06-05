<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>北京尚古中和装饰设计有限公司</title>
		<meta name="keywords" content="中式, 中式设计, 中式装修, 中式设计公司, 北京中式设计公司" />
		<meta name="description" content="中式, 中式设计, 中式装修, 中式设计公司, 北京中式设计公司" />
		<link rel="styleSheet" type="text/css" href="./css/style.css" />
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
						<div class="title2">在线留言</div>
					</div>
					<div id="content_left_bottom">
						<ul>
							<li>在线留言</li>
							<li>查看留言</li>
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
								<div class="content_right_content">
									<div id="content_right_content_message">
										<div id="form_title">在线留言：</div>
						
										<div class="form_item">
											<label id="form_item_name" for="name">您的姓或姓名：</label>
										  	<input id="name" tabindex="1" />
										  	<span id="nameTip"></span>
										</div>
						
										<div class="form_item">
											<label id="form_item_telephone" for="telephone">您的联系方式（手机或固定电话或Email）：</label>
										  	<input id="telephone" style="ime-mode: disabled;" tabindex="2" />
										  	<span id="telephoneTip"></span>
										</div>
						
										<div class="form_item">
											<label id="form_item_content" for="content">您的留言内容：</label>
										  	<textarea id="content1" tabindex="3"></textarea>
										  	<span id="content1Tip"></span>
										</div>
										
										<div id="form_opt">
											<input type="submit" id="btn" value="发表留言" tabindex="4" />
										</div>
									</div>
									
									<#-- 留言部分 -->
									<#include "include/Pager.ftl">
								</div>
							</div>
							
							<#-- 留言列表 -->
							<div class="content">
								<div class="content_right_content">
									<#include "include/Pager.ftl">
								</div>
							</div>
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
		
		<#-- 底部的页脚 -->
		<#include "include/Footer.ftl">
			        
		<script language="javaScript" src="./js/jquery.js"></script>
        <script language="javascript" src="./js/jquery.maxlength.js"></script>
		<script language="javaScript" src="./js/jquery.fancybox.js"></script>
		<script language="javaScript" src="./js/project.js"></script>
		<script language="javaScript" src="./js/public.js"></script>
		<script language="javaScript" src="./js/message.js"></script>
		<script language="javaScript" src="./js/index.js"></script>
	</body>
</html>
