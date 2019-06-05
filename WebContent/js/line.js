// Copyright 2010, Yang Xinxin


// 收起/展开 之间的切换
$(function() {
	var parentFrame = parent.document.getElementById("framesetId");
	$("#tdBar").click(function() {
		if (parentFrame.cols == "150, 8, *") {
			parentFrame.cols = "0, 8, *";
			$("#switchBar").html("&gt;");
		} else {
			parentFrame.cols = "150, 8, *";
			$("#switchBar").html("&lt;");
		}
	});
});

