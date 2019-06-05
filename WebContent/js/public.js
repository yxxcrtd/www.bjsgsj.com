// Copyright 2011
// Author: Yang XinXin


$(function() {
	
	// 使用户名的输入框 获得焦点
	$("#username").focus();
	
	// 屏蔽右键
	$(document).bind("contextmenu", function(e) {
		return false;
	});
	
	// 去除 radio 在 IE 和 Firefox 下单击后的虚线框
	$("input[type='radio']").focus(function() {
		this.blur();
	});
	
	// 关闭验证码的自动完成
	$("#verifyCode").attr("autocomplete", "off");
	
	// 刷新验证码
	$("#verifyImage").click(function() {
		this.src = "verifyCode?now=" + new Date();
	});

});


//图片预览
function priview(obj) {
	// 验证图片格式
	if (!obj.value.match(/.jpg|.jpeg|.gif|.png|.bmp/i)) {
		alert("图片格式不正确！");
		pictureNode.attr("value", "");
		imgNode.attr("src", "./images/blank.gif").attr("width", 0).attr("height", 0);
		return false;
	}
	
	// IE浏览器验证
	if ($.browser.msie) {
		// IE 6.0
		if (6.0 == $.browser.version) {
			imgNode.attr("src", obj.value).attr("width", 250).attr("height", 180);
		} else { // TODO：IE 6.0 以上版本待验证！
			var objPreview = document.getElementById('preview');
			var objPreviewFake = document.getElementById('preview_fake');
			var objPreviewSizeFake = document.getElementById('preview_size_fake');
			obj.select();
			var imgSrc =document.selection.createRange().text;
			objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src= imgSrc;
			objPreviewSizeFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src= imgSrc;
			autoSizePreview(objPreviewFake,objPreviewSizeFake.offsetWidth,objPreviewSizeFake.offsetHeight);
			objPreview.style.display ='none';
		}
	}
	
	// Mozilla Firefox
	if ($.browser.mozilla) {
		imgNode.attr("src", obj.files[0].getAsDataURL()).attr("width", 250).attr("height", 180);
	}
}

// TODO 需要重新改写！
// 鼠标移动到列表上之后，改变颜色 
function changeBgColor(obj, colors) {
	obj.style.backgroundColor = colors;
}
