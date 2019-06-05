<div id="show_content_left">
	<div class="title_16_b">公司介绍：</div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;北京尚古中和装饰设计有限公司（简称：尚古中式设计）是一家集：中式别墅、中式样板、中式酒店、中式茶楼、中式会所、中式家装以及中式四合院于一体的综合性的专业设计公司。
	<br />
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本公司始终坚持以设计引领施工，用设计来体现中式的灵魂。公司本着对自己负责、对客户负责、严谨治学，以文化体现设计，得自然与和谐，产生更美、更舒适的生活空间及生活品质！ 
	<br />
	<div id="company_detail">
		<a href="company.html">查看详细信息</a>
	</div>
</div>

<div class="gap_column">&nbsp;</div>

<div id="show_content_middle">
	<script language="javascript">
	<!--
		// 超连接数组
		linkarr = new Array();
		
		// 图片数组
		picarr = new Array();
		
		// 文字介绍数组
		textarr = new Array();
		
		// 图片宽
		var focus_width = 538;
		
		// 图片高（300 - 3*2 - 16）
		var focus_height = 278;
		
		// 文字介绍高度
		var text_height = 16;
		
		// Flash的高度
		var swf_height = focus_height + text_height;
		
		var links = "";
		var pics = "";
		var texts = "";
		
		linkarr[1] = "equipment.html";
		picarr[1] = "./upload/1.jpg";
		textarr[1] = "北京中式酒店 - 大堂";
		linkarr[2] = "equipment.html";
		picarr[2] = "./upload/2.jpg";
		textarr[2] = "北京市昌平区南口别墅 －客厅前";
		linkarr[3] = "equipment.html";
		picarr[3] = "./upload/3.jpg";
		textarr[3] = "北京市顺义区四合院鸟瞰图";
		linkarr[4] = "equipment.html";
		picarr[4] = "./upload/4.jpg";
		textarr[4] = "经典会所 －二层自助餐厅之主题背景墙";
		linkarr[5] = "equipment.html";
		picarr[5] = "./upload/5.jpg";
		textarr[5] = "经典会所 －四层展厅";
		linkarr[6] = "equipment.html";
		picarr[6] = "./upload/6.jpg";
		textarr[6] = "青岛某私人会所 －茶室";
		
		<#--if showList?? && 0 != showList?size>
			<#list showList as show>
				linkarr[${show_index + 1}] = "${show.project.parent}.html";
				picarr[${show_index + 1}] = "./upload/s_${show.link!}";
				textarr[${show_index + 1}] = "${show.describe!}";
				<#if (5 == show_index)>
					<#break>
				</#if>
			</#list>
		</#if-->
		
		for (i = 1; i < linkarr.length; i++) {
			if (links == "")
				links = linkarr[i];
			else
				links += "|" + linkarr[i];
		}
		
		for(i = 1; i < picarr.length; i++) {
			if (pics == "")
				pics = picarr[i];
			else
				pics += "|" + picarr[i];
		}
		
		for (i = 1; i < textarr.length; i++) {
			if (texts == "")
				texts = textarr[i];
			else
				texts += "|" + textarr[i];
		}
		
		document.write('<object type="application/x-shockwave-flash" data="./images/slide.swf" width="' + focus_width + '" height="' + swf_height + '">');
		document.write('<param name="movie" value="./images/slide.swf" />');
		document.write('<param name="allowScriptAcess" value="sameDomain" />');
		document.write('<param name="quality" value="best" />');
		document.write('<param name="bgcolor" value="#EEEEEE" />');
		document.write('<param name="scale" value="noScale" />');
		document.write('<param name="menu" value="false">');
		document.write('<param name="wmode" value="opaque" />');
		document.write('<param name="FlashVars" value="playerMode=embedded&pics=' + pics + '&links=' + links + '&texts=' + texts + '&borderwidth=' + focus_width + '&borderheight=' + focus_height + '&textheight=' + text_height + '" />');
		document.write('</object>');
	-->
	</script>
</div>

<div class="gap_column">&nbsp;</div>

<div id="show_content_right">
	<div class="title_16_b">最新案例：</div>
	<#if pictureList?? && 0 != pictureList?size>
		<#list pictureList as picture>
			<div class="show_content_right_info">
				<a href="./upload/${picture.link!}" rel="show_group">
					${picture.describe!}
				</a>
			</div>
		</#list>
	</#if>
</div>
