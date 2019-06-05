// Copyright 2011
// Author: Yang XinXin


$(function() {
	
	// 去除链接虚线
	$("#menu ul li a").bind("focus", function() {
		if (this.blur) {
			this.blur();
		}
	});
	
	
	// 向下箭头的动画不显示
	$(".menu_img").css({
		"opacity" : "0"
	});
	
	// 下边框动画不显示
	$(".borders").css({
		"opacity" : "0"
	});

	// 菜单激活时
	$("#menu ul li .active").css({
		"opacity" : "1"
	});

	// 鼠标进入或离开菜单
	$("#menu ul li a").mouseenter(show).mouseleave(hide);

	// 显示
	function show() {
		$(this).siblings(".borders").stop().animate({
			"opacity" : "1"
		}, 1000);

		$(this).siblings(".menu_img").stop().animate({
			"opacity" : "1"
		}, 1000);
	}

	// 隐藏
	function hide() {
		$(".borders").stop().animate({
			"opacity" : "0"
		}, 1000);

		$(".menu_img").stop().animate({
			"opacity" : "0"
		}, 1000);
		
		$(".active").stop().animate({
			"opacity" : "1"
		}, 1000);
	}
	
	// Fancybox 的属性设置
	$("a[rel=show_group], a[rel=villa_group], a[rel=equipment_group], a[rel=home_group], a[rel=garden_group], a[rel=decoration_group], a[rel=project_group]").fancybox({
		'padding'			: 10, // 图片与边框的距离，显示为：白色
		'margin'			: 18,
		'speedIn'			: 1000, // 显示的速度
		'speedOut'			: 1300, // 消失的速度
		'changeSpeed'		: 1000, // 图片大小改变的切换速度
		'autoDimensions'	: true, // 自动设置尺寸
		'centerOnScroll'	: true, // 页面滚动时，内容是否一直居中显示
		'hideOnOverlayClick': false, // 遮罩层的点击，true：表示点击就关闭，false：不关闭（在内容上点击，不关闭）
		'hideOnContentClick': false, // 在内容上点击就可以关闭
		'transitionIn'		: 'elastic',
		'transitionOut'		: 'elastic',
		'titleShow' 		: false
//		'titlePosition' 	: 'over',
//		'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
//			return '<span id="fancybox-title-over">第 ' + (currentIndex + 1) + ' 张 / 共 ' + currentArray.length + ' 张，' + (title.length ? '&nbsp; ' + title : '') + '</span>';
//		}
	});
	
	
//	// 滚动广告部分
//	
//	// 滚动速度
//	var speed = 40;
//	
//	// 滚动区域的div
//	var slideNode = $("#slide");
//	
//	
//	var _slideli1 = $(".slideli1");
//	
//	
//	var _slideli2 = $(".slideli2");
//	
//	// 两秒后调用
//	var sliding = setInterval(doMarquee, speed);
//	slideNode.hover(function() {
//		// 鼠标移动DIV上停止
//		clearInterval(sliding);
//	}, function(){
//		// 离开继续调用
//		sliding = setInterval(doMarquee, speed);
//	});
//	_slideli2.html(_slideli1.html());
//	
//	function doMarquee() {
//		if(slideNode.scrollLeft() >= _slideli1.width()) {
//			slideNode.scrollLeft(0);
//		} else {
//			slideNode.scrollLeft(slideNode.scrollLeft() + 1);
//		}
//	}

});
