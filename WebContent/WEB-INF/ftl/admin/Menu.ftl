<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="styleSheet" type="text/css" href="./css/menu.css" />
		<style type="text/css">
			li a {
				display: inline-block;
			}
			li a {
				display: block;
			}
		</style>
	</head>

	<body>
		<div id="welcome">
			<@s.text name="system.menu.welcome" /><span id="info"><@s.text name="system.administrator" /></span>
			<@s.a href="logout.action">
				<@s.text name="system.logout" />
			</@s.a>
		</div>
		
		<ul id="menu" class="menu noaccordion expandfirst">
			<li>
				<a id="firstMenu" class="subMenu" href="javascript:void(0);">
					<@s.text name="system.menu.html" />
				</a>
				<ul>
					<li>
						<a href="site.action?cmd=index" target="mainFrame">
							<@s.text name="system.site.index" />
						</a>
					</li>
					<li>
						<a href="site.action?cmd=company" target="mainFrame">
							<@s.text name="system.site.company" />
						</a>
					</li>
					<li>
						<a href="site.action?cmd=villa" target="mainFrame">
							<@s.text name="system.site.villa" />
						</a>
					</li>
					<li>
						<a href="site.action?cmd=equipment" target="mainFrame">
							<@s.text name="system.site.equipment" />
						</a>
					</li>
					<li>
						<a href="site.action?cmd=home" target="mainFrame">
							<@s.text name="system.site.home" />
						</a>
					</li>
					<li>
						<a href="site.action?cmd=garden" target="mainFrame">
							<@s.text name="system.site.garden" />
						</a>
					</li>
					<li>
						<a href="site.action?cmd=decoration" target="mainFrame">
							<@s.text name="system.site.decoration" />
						</a>
					</li>
					<li>
						<a href="site.action?cmd=message" target="mainFrame">
							<@s.text name="system.site.message" />
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a class="subMenu" href="javascript:void(0);">
					<@s.text name="system.menu.project" />
				</a>
				<ul>
					<li>
						<a href="project.action?cmd=edit" target="mainFrame">
							<@s.text name="system.menu.project.new" />
						</a>
					</li>
					<li>
						<a href="project.action?cmd=list" target="mainFrame">
							<@s.text name="system.menu.project.list" />
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a class="subMenu" href="javascript:void(0);">
					<@s.text name="system.menu.picture" />
				</a>
				<ul>
					<li>
						<a href="picture.action?cmd=edit&obj=1" target="mainFrame">
							<@s.text name="system.menu.picture.new" />
						</a>
					</li>
					<li>
						<a href="picture.action?cmd=list" target="mainFrame">
							<@s.text name="system.menu.picture.list" />
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a class="subMenu" href="javascript:void(0);">
					<@s.text name="system.menu.message" />
				</a>
				<ul>
					<li>
						<a href="message.action?cmd=list" target="mainFrame">
							<@s.text name="system.menu.message.list" />
						</a>
					</li>
				</ul>
			</li>
		</ul>
		<script type="text/javascript" src="./js/jquery.js"></script>
		<script type="text/javascript" src="./js/menu.js"></script>
	</body>
</html>
