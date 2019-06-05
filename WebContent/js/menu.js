// CopyRight 2010, Yang XinXin
// 菜单功能

$(function() {
	
	// 隐藏子菜单
	$("#menu li ul").hide();
	
	// 展开第一个背景图
	$("#firstMenu").css("background-image", "url('images/icon_minus.gif')");
	
	// 展开第一个功能菜单
	$.each($("#menu"), function() {
		$("#" + this.id + ".expandfirst ul:first").show();
	});
	
	// 展开/收起 "父菜单"
	$("#menu .subMenu").click(function() {
		$(this).next().slideToggle("normal");
		changeIcon($(this));
	});
});

// 改变主菜单前的图标
function changeIcon(node) {	
	if (node) {
		if (node.css("background-image").indexOf("images/icon_plus.gif") >= 0) {
			node.css("background-image", "url('images/icon_minus.gif')");
		} else {
			node.css("background-image", "url('images/icon_plus.gif')");
		}
	}
}

