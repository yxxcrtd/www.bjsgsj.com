/**
 * jQuery Maxlength plugin
 * @version		$Id: jquery.maxlength.js 18 2009-05-16 15:37:08Z emil@anon-design.se $
 * @package		jQuery maxlength 1.0.5
 * @copyright	Copyright (C) 2009 Emil Stjerneman / http://www.anon-design.se
 * @license		GNU/GPL, see LICENSE.txt
 * 
 * 我的改造记录：
 * 1，将 div 改成了 span，并修改了显示位置和显示颜色
 * 2，...
 */

(function($) {
	$.fn.maxlength = function(options) {
		var settings = jQuery.extend( {
			events:				[], // 被触发的事件数组
			maxCharacters:		10, // 最大值限制
			status:				true, // 是否显示状态提示符
			statusClass:		"charsLeft", // 信息提示的class
			statusText:			"个字符剩余", // 信息提示文本内容
			notificationClass:	"notification",	// 达到最大值的时候显示
			showAlert:			false, // 是否弹出信息提示框
			alertText:			"不能超过最大输入值！", // 在信息提示框中显示
			slider:				true // 是否使用滑动效果
		}, options );
		
		// 添加默认的事件
		$.merge(settings.events, ['keyup']);

		// 
		return this.each(function() {
			var item = $(this);
			var charactersLength = $(this).val().length;
			
			// 修改文本状态
			function updateStatus() {
				// 剩余字符串数量 = 用户设置的字符串的长度 - 当前字符串的长度
				var charactersLeft = settings.maxCharacters - charactersLength;
				
				// 如果剩余字符串数量 < 0（即：用户的输入超过了最大值，相减为负！），直接将剩余值设为：0
				if(charactersLeft <= 0) {
					charactersLeft = 0;
					item.next("span").html(" 只能输入" + settings.maxCharacters + "个字符！");
				} else {
					item.next("span").html(" " + charactersLeft + settings.statusText);
				}
			}

			// 检查字符数
			function checkChars() {
				var valid = true;
				
				// 当前输入框中的长度 >= 设置的最大长度
				if(charactersLength >= settings.maxCharacters) {
					
					// 设置变量为：false
					valid = false;
					
					// 添加达到警告最大值的样式
					item.addClass(settings.notificationClass);
					
					// 切割多余的字符串
					item.val(item.val().substr(0, settings.maxCharacters));
					
					// 显示警告对话框，如果设为：true 的话
					showAlert();
				} else {
					// 移除最大值的警告样式
					if(item.hasClass(settings.notificationClass)) {
						item.removeClass(settings.notificationClass);
					}
				}
				
				// 修改文本状态
				if(settings.status) {
					updateStatus();
				}
			}
						
			// 弹出信息提示框
			function showAlert() {
				if(settings.showAlert) {
					alert(settings.alertText);
				}
			}

			// 验证
			function validateElement() {
				var ret = false;
				
				if(item.is('textarea')) {
					ret = true;
				} else if(item.filter("input[type=text]")) {
					ret = true;
				} else if(item.filter("input[type=password]")) {
					ret = true;
				}
				return ret;
			}

			// 验证
			if(!validateElement()) {
				return false;
			}
			
			// Loop through the events and bind them to the element
			$.each(settings.events, function(i, n) {
				item.bind(n, function(e) {
					charactersLength = item.val().length;
					checkChars();
				});
			});

			// 插入：状态提示的div
			if(settings.status) {
				item.after($("<span/>").addClass(settings.statusClass).html('-'));
				updateStatus();
			}

			// 删除：状态提示的div
			if(!settings.status) {
				var removeThisDiv = item.next("span." + settings.statusClass);
				if(removeThisDiv) {
					removeThisDiv.remove();
				}
			}

			// div 以 "滑动" 的方式显示， span 淡入/淡出
			if(settings.slider) {
				item.next().hide();
				
				item.focus(function(){
					// item.next().slideDown('fast');
					item.next().fadeIn("slow");
				});

				item.blur(function(){
					// item.next().slideUp('fast');
					item.next().fadeOut("slow");
				}); 
			}

		});
	};
})(jQuery);

