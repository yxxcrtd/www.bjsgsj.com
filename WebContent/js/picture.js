// Copyright 2011
// Author: Yang XinXin


var imgNode = $("#img");
$(function() {
	// 目标节点
	var targetNode = $("#first");
	
	// 添加图片地址
	$("#more").click(function() {
//		// 方法1：
//	 	var fileObj = $("<div><input type='file' name='file' size='63' style='width: 518px;' /><a href='#' title='关闭'>&nbsp;<img class='morefile' src='./images/icon_minus.gif' /></a></div>");
//	 	var firstNode = $("#first");
//	 	fileObj.appendTo(firstNode);
		
		// 文件对象
		var fileObj = $("<input type='file' name='file' size='80' style='width: 638px;' />");
		// 图片超连接对象
		var aObj = $("<a href='#' title='关闭'>&nbsp;<img class='morefile' src='./images/icon_minus.gif' /></a>");
		// 将：文件对象和图片超连接对象 追加到 目标节点
		targetNode.append(fileObj).append(aObj);


	 	// 关闭
//	 	$(".morefile").click(function() {
//	 		console.info("111111");
//	 		fileObj.remove();
//	 		console.info("222222");
//	 	});
	 });
});

//function addMore() {
//	var td = document.getElementById("more");	
//	var br = document.createElement("br");
//	var input = document.createElement("input");
//	var button = document.createElement("input");
//	
//	input.type = "file";
//	input.name = "file";
//	input.size = "35";
//	
//	button.type = "button";
//	button.value = " 取  消 ";
//	
//	button.onclick = function() {
//		td.removeChild(br);
//		td.removeChild(input);
//		td.removeChild(button);
//	}				
//	td.appendChild(br);
//	td.appendChild(input);
//	td.appendChild(button);	
//}


