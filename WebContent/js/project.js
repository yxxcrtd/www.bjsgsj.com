// Copyright 2011
// Author: Yang XinXin


$(function() {
	// 滑动门效果
	$("#content_left_bottom li").each(function(index) {
		// 有了index的值之后，就可以找到当前标签对应的内容区域
		$(this).click(function() {
			var liNode = $(this);
			// 将当前显示的区域隐藏
			$("div.contentin").removeClass("contentin").addClass("content");
			// 显示鼠标移上的区域
			$("div.content").eq(index).removeClass("content").addClass("contentin");
			
			$("#content_left_bottom li.tabin").removeClass("tabin");
			liNode.addClass("tabin");
		});
	});
	
	// 左边随页面向下而滚动
	var offset = $("#content_left").offset();
	$(window).scroll(function() {
		// 当滚动条拖动的长度大于div距离顶部的高度时
		if ($(window).scrollTop() > offset.top) {
			$("#content_left").stop().animate({
				marginTop: $(window).scrollTop() - offset.top + 2
			});
		} else {
			$("#content_left").stop().animate({
				marginTop: 0
			});
		}
	});
});
