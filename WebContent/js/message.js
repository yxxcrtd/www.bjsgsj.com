// Copyright 2011
// Author: Yang XinXin


// 发表在线留言
$(function() {
	// 姓或姓名
	var nameNode = $("#name");	
	var nameTipNode = $("#nameTip");
	
	// 手机或固定电话
	var telephoneNode = $("#telephone");
	var telephoneTipNode = $("#telephoneTip");
	
	// 内容
	var contentNode = $("#content1");
	var contentTipNode = $("#content1Tip");
	
	$(nameNode).maxlength({
		maxCharacters: 10
	});
	
	$(telephoneNode).maxlength({
		maxCharacters: 22
	});
	
	$(contentNode).maxlength({
		maxCharacters: 150
	});
	
	nameNode.bind("blur", function() {
		$(this).val($.trim($(this).val()));
		nameTipNode.html("＊").css("color", "#FF0000");
	}).bind("focus", function() {
		nameTipNode.html("");
	});
	
	telephoneNode.bind("blur", function() {
		$(this).val($.trim($(this).val()));
		telephoneTipNode.html("＊").css("color", "#FF0000");
	}).bind("focus", function() {
		telephoneTipNode.html("");
	});
	
	contentNode.bind("blur", function() {
		$(this).val($.trim($(this).val()));
		contentTipNode.html("＊").css("color", "#FF0000");
	}).bind("focus", function() {
		contentTipNode.html("");
	});

	nameTipNode.html("＊").css("color", "#FF0000");
	telephoneTipNode.html("＊").css("color", "#FF0000");
	contentTipNode.html("＊").css("color", "#FF0000");
	
	$("#btn").click(function() {
		var nameValue = $.trim(nameNode.val());
		var telephoneValue = $.trim(telephoneNode.val());
		var contentValue = $.trim(contentNode.val());
		
		// 姓或姓名不能为空
		if ("" == nameValue) {
			alert("您的姓或姓名不能为空！");
			nameNode.focus();
			return false;
		}
		
		// 手机或固定电话不能为空
		if ("" == telephoneValue) {
			alert("您的联系方式不能为空！");
			telephoneNode.focus();
			return false;
		}

		// 内容不能为空
		if ("" == contentValue) {
			alert("您的留言内容不能为空！");
			contentNode.focus();
			return false;
		}

		// Ajax提交登录信息
		$.ajax({
			type : "GET",
			url : "message.action",
			cache : false,
			data : {
				"cmd" : "save",
				"message.name" : nameValue,
				"message.telephone" : telephoneValue,
				"message.content" : contentValue
			},
			// 必须指定返回的数据类型，否则默认返回的是： [object XMLDocument]
			dataType : "text",
			success : function(response) {
				if ("success" == response) {
					// 返回提示信息
					alert("留言成功！\n\n我们会在最短的时间内与您联系！");
					
					// 清空表单
					nameNode.val("");
					telephoneNode.val("");
					contentNode.val("");
					
					// 重新加载一次页面(TODO：以后再修改)
					window.location.reload();
				}
			},
			error : function(html) {
				alert("数据提交失败，错误代码：" + html.status +"，请稍侯再试！");
			}
		});
	});
});
