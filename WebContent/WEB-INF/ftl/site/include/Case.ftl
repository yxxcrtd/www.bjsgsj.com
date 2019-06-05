<#assign sequence = ["villa", "equipment", "home", "garden", "decoration"]>
<#list sequence as seq>
	<div class="div_title">
		<div class="title1">案例展示[<#if "villa" == seq>中式别墅<#elseif "equipment" == seq>中式公装<#elseif "home" == seq>中式家装<#elseif "garden" == seq>古建园林<#elseif "decoration" == seq>中式配饰</#if>]:</div>
		<div class="more">
			<#if "villa" == seq><a href="villa.html" title="更多中式别墅效果图">更多>></a>
			<#elseif "equipment" == seq><a href="equipment.html" title="更多中式公装效果图">更多>></a>
			<#elseif "home" == seq><a href="home.html" title="更多中式家装效果图">更多>></a>
			<#elseif "garden" == seq><a href="garden.html" title="更多古建园林效果图">更多>></a>
			<#elseif "decoration" == seq><a href="decoration.html" title="更多中式配饰效果图">更多>></a>
			</#if>
		</div>
	</div>
	<div class="div_body">
		<#-- 如果是两排图片，则需要增加图片标题的尺寸 -->
		<#if ("villa" == seq)>
			<div class="block_case_head2">
		<#elseif ("equipment" == seq)>
			<div class="block_case_head1">
		<#else>
			<div class="block_case_head1">
		</#if>
			<#if "villa" == seq><#-- 中式别墅 -->
				<#if villa??>
					<#list villa as case>
						<div class="block_case_sub_info">
							<a href="./upload/${case.link!}" rel="villa_group" title="点击查看大图">
								${case.describe!}
							</a>
						</div>
						<#if (19 == case_index)>
							<#break>
						</#if>
					</#list>
				</#if>
			<#elseif "equipment" == seq><#-- 中式公装 - 标题 -->
				<#if equipment??>
					<#list equipment as case>
						<div class="block_case_sub_info">
							<a href="./upload/${case.link!}" rel="equipment_group" title="点击查看大图">
								<#if (case.describe?length > 18)>
									${case.describe[0..17]!}..
								<#else>
									${case.describe!}
								</#if>
							</a>
						</div>
						<#if (12 == case_index)>
							<#break>
						</#if>
					</#list>
				</#if>
			<#elseif "home" == seq><#-- 中式家装 -->
				<#if home??>
					<#list home as case>
						<div class="block_case_sub_info">
							<a href="./upload/${case.link!}" rel="home_group" title="点击查看大图">
								<#if (case.describe?length > 18)>
									${case.describe[0..17]!}..
								<#else>
									${case.describe!}
								</#if>
							</a>
						</div>
						<#if (12 == case_index)>
							<#break>
						</#if>
					</#list>
				</#if>
			<#elseif "garden" == seq><#-- 古建园林 -->
				<#if garden??>
					<#list garden as case>
						<div class="block_case_sub_info">
							<a href="./upload/${case.link!}" rel="garden_group" title="点击查看大图">
								<#if (case.describe?length > 18)>
									${case.describe[0..17]!}..
								<#else>
									${case.describe!}
								</#if>
							</a>
						</div>
						<#if (12 == case_index)>
							<#break>
						</#if>
					</#list>
				</#if>
			<#elseif "decoration" == seq><#-- 中式配饰 -->
				<#if decoration??>
					<#list decoration as case>
						<div class="block_case_sub_info">
							<a href="./upload/${case.link!}" rel="decoration_group" title="点击查看大图">
								<#if (case.describe?length > 18)>
									${case.describe[0..17]!}..
								<#else>
									${case.describe!}
								</#if>
							</a>
						</div>
						<#if (12 == case_index)>
							<#break>
						</#if>
					</#list>
				</#if>
			</#if>
		</div>
		
		<div class="gap_column">&nbsp;</div>
		
		<!-- 以下是图片 -->
		<#if "villa" == seq>
			<div class="block_case_body2">
				<#if villa??>
					<#list villa as case>
						<div class="block_case">
							<a href="./upload/${case.link!}" rel="villa_group" title="点击查看大图">
								<img src="./upload/c_${case.link!}" width="245" height="150" /><br />
								${case.describe!}
							</a>
						</div>
						<#if (8 == case_index)>
							<#break>
						<#elseif (2 == case_index || 5 == case_index)>
							<div class="gap_row"></div>
						<#else>
							<div class="gap_column">&nbsp;</div>
						</#if>
					</#list>
				</#if>
			</div>
		<#elseif "equipment" == seq><#-- 中式公装 - 图片 -->
			<div class="block_case_body1">
				<#if equipment??>
					<#list equipment as case>
						<div class="block_case">
							<a href="./upload/${case.link!}" rel="equipment_group" title="点击查看大图">
								<img src="./upload/c_${case.link!}" width="245" height="150" /><br />
								${case.describe!}
							</a>
						</div>
						<#if (5 == case_index)>
							<#break>
						<#elseif (2 == case_index)>
							<div class="gap_row"></div>
						<#else>
							<div class="gap_column">&nbsp;</div>
						</#if>
					</#list>
				</#if>
			</div>
		<#elseif "home" == seq>
			<div class="block_case_body1">
				<#if home??>
					<#list home as case>
						<div class="block_case">
							<a href="./upload/${case.link!}" rel="home_group" title="点击查看大图">
								<img src="./upload/c_${case.link!}" width="245" height="150" /><br />
								${case.describe!}
							</a>
						</div>
						<#if (5 == case_index)>
							<#break>
						<#elseif (2 == case_index)>
							<div class="gap_row"></div>
						<#else>
							<div class="gap_column">&nbsp;</div>
						</#if>
					</#list>
				</#if>
			</div>
		<#elseif "garden" == seq>
			<div class="block_case_body1">
				<#if garden??>
					<#list garden as case>
						<div class="block_case">
							<a href="./upload/${case.link!}" rel="garden_group" title="点击查看大图">
								<img src="./upload/c_${case.link!}" width="245" height="150" /><br />
								${case.describe!}
							</a>
						</div>
						<#if (5 == case_index)>
							<#break>
						<#elseif (2 == case_index)>
							<div class="gap_row"></div>
						<#else>
							<div class="gap_column">&nbsp;</div>
						</#if>
					</#list>
				</#if>
			</div>
		<#elseif "decoration" == seq>
			<div class="block_case_body1">
				<#if decoration??>
					<#list decoration as case>
						<div class="block_case">
							<a href="./upload/${case.link!}" rel="decoration_group" title="点击查看大图">
								<img src="./upload/c_${case.link!}" width="245" height="150" /><br />
								${case.describe!}
							</a>
						</div>
						<#if (5 == case_index)>
							<#break>
						<#elseif (2 == case_index)>
							<div class="gap_row"></div>
						<#else>
							<div class="gap_column">&nbsp;</div>
						</#if>
					</#list>
				</#if>
			</div>
		</#if>
	</div>
		
	<#-- 空白行 -->
	<div class="gap_row"></div>
</#list>
