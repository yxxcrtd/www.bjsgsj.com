<html>
	<head>
		<link rel="styleSheet" type="text/css" href="./css/manage.css" />
	</head>

	<body>
		<div style="padding: 100px; line-height: 30px; font-size: 14px;">
			<div>本系统使用内存情况：</div>
			<div>Java虚拟机(JVM)的最大可用内存：${max!} <font color="#F00">M</font></div>
			<div>Java虚拟机(JVM)占用的内存总数：${total!} <font color="#F00">M</font></div>
			<div>Java虚拟机(JVM)中的空闲内存：${free!} <font color="#F00">M</font></div>
			<div>Java虚拟机(JVM)中实际可用的内存：${valid!} <font color="#F00">M</font></div>
			<div>JVM中实际可用的内存 = JVM最大可用内存 - JVM占用的内存总数 + JVM中的空闲内存</div>
			<div>JVM只有在需要内存时才占用物理内存，所以<b>空闲内存</b>一般很小！</div>
		</div>
	</body>
</html>

