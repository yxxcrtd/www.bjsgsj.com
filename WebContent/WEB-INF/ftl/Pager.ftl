<span>
	共&nbsp;<font style="color: #FF0000; font-weight: bold;">${pager.totalRows}</font>&nbsp;${pager.itemUnit + pager.itemName}

	<#if pager.currentPage == 1>首页
	<#else><a href="${pager.firstPageUrl}">首页</a>
	</#if>

	<#if (pager.currentPage > 1)><a href="${pager.prevPageUrl}">上一页</a>
	<#else>上一页
	</#if>

	<#if (pager.currentPage < pager.totalPages)><a href="${pager.nextPageUrl}">下一页</a>
	<#else>下一页
	</#if>

	<#if (pager.currentPage != pager.totalPages) && (pager.totalPages != 0)><a href="${pager.lastPageUrl}">尾页</a>
	<#else>尾页
	</#if>

	页次：${pager.currentPage}/${pager.totalPages}&nbsp;页

	#{pager.pageSize}${pager.itemUnit + pager.itemName}/页

	<#if (pager.totalPages > 0)>
		转到：<select name="page" size="1" onChange="javascript:window.location='${pager.urlPattern}'.replace('{page}', this.options[this.selectedIndex].value);">
				<#list 1..pager.totalPages as i>
					<option value="${i}" <#if i == pager.currentPage>selected</#if> >${i}</option>
				</#list>
			</select>
	</#if>
</span>
