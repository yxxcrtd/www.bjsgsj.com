package com.bjsgsj.util;

/**
 * 分页对象
 * 
 * @author Yang XinXin
 * @version 2.0.0, 2010-09-02 10:09:22
 */
public class Pager {

	/**
	 * 总行数
	 */
	private int totalRows;

	/**
	 * 每页默认显示的行数
	 */
	private int pageSize = 20;

	/**
	 * 当前页号
	 */
	private int currentPage;

	/**
	 * 当前页在数据库中的起始行
	 */
	private int startRow;

	/**
	 * 当前项目的默认名称
	 */
	private String itemName = "项目";

	/**
	 * 当前项目的默认单位
	 */
	private String itemUnit = "个";

	/**
	 * 页面的 Url Pattern, 内部的 '{page}' 将被替换为当时的页次
	 */
	private String url_pattern = "?page={page}";

	/**
	 * 首页的链接地址
	 */
	private String firstPageUrl;

	/**
	 * 上一页的链接地址
	 */
	private String prevPageUrl;

	/**
	 * 下一页的链接地址
	 */
	private String nextPageUrl;

	/**
	 * 尾页的链接地址
	 */
	private String lastPageUrl;

	/**
	 * Default Constructor
	 */
	public Pager() {

	}

	/**
	 * @param _totalRows
	 */
	public Pager(int _totalRows) {
		totalRows = _totalRows;
		currentPage = 1;
		startRow = 0;
	}

	/**
	 * 得到当前页在数据库中的起始行
	 * 
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * 得到总页数（根据总记录数和分页大小动态计算出来的）
	 * 
	 * @return
	 */
	public int getTotalPages() {
		if (this.totalRows <= 0)
			return 0;
		if (this.pageSize <= 0)
			return 0;
		int i = this.totalRows / this.pageSize;
		if ((this.totalRows % this.pageSize) != 0)
			++i;
		return i;
	}

	/**
	 * 得到当前页号
	 * 
	 * @return
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 得到每页显示的行数
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 总行数
	 * 
	 * @param totalRows
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void first() {
		currentPage = 1;
		startRow = 0;
	}

	public void previous() {
		if (currentPage == 1) {
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize;
	}

	public void next() {
		if (currentPage < getTotalPages()) {
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize;
	}

	public void last() {
		currentPage = getTotalPages();
		startRow = (currentPage - 1) * pageSize;
	}

	public void refresh(int _currentPage) {
		currentPage = _currentPage;
		if (currentPage > getTotalPages()) {
			last();
		}
	}

	/**
	 * 获取当前的项目单位
	 * 
	 * @return
	 */
	public String getItemUnit() {
		return itemUnit;
	}

	/**
	 * 设置当前的项目名称
	 * 
	 * @param itemName
	 */
	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	/**
	 * 获取当前的项目名称
	 * 
	 * @return
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * 设置当前的项目名称
	 * 
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 设置项目名称和单位
	 * 
	 * @param itemName
	 * @param itemUnit
	 */
	public void setItemNameAndUnit(String itemName, String itemUnit) {
		this.itemName = itemName;
		this.itemUnit = itemUnit;
	}

	/**
	 * 获取页面的 UrlPattern，例如 'list_{page}.html'
	 * 
	 * @return
	 */
	public String getUrlPattern() {
		return url_pattern;
	}

	/**
	 * 设置页面的Url（比如：'list_{page}.html'）
	 * 
	 * @param url
	 */
	public void setUrlPattern(String url) {
		this.url_pattern = url;
	}

	/**
	 * 获取第一页的链接地址
	 * 
	 * @return
	 */
	public String getFirstPageUrl() {
		if (null == this.firstPageUrl || this.firstPageUrl.length() == 0)
			return this.internalGetPageUrl(1);
		return firstPageUrl;
	}

	/**
	 * 设置第一页的链接地址
	 * 
	 * @param firstPageUrl
	 */
	public void setFirstPageUrl(String firstPageUrl) {
		this.firstPageUrl = firstPageUrl;
	}

	/**
	 * 获取最后一页的链接地址
	 * 
	 * @return
	 */
	public String getLastPageUrl() {
		if (null == this.lastPageUrl || this.lastPageUrl.length() == 0)
			return this.internalGetPageUrl(getTotalPages());
		return lastPageUrl;
	}

	/**
	 * 设置最后一页的链接地址
	 * 
	 * @param firstPageUrl
	 */
	public void setLastPageUrl(String lastPageUrl) {
		this.lastPageUrl = lastPageUrl;
	}

	/**
	 * 获取下一页的链接地址
	 * 
	 * @return
	 */
	public String getNextPageUrl() {
		if (null == this.nextPageUrl || this.nextPageUrl.length() == 0)
			return this.internalGetPageUrl(this.currentPage + 1);
		return nextPageUrl;
	}

	/**
	 * 获取下一页的链接地址
	 * 
	 * @param firstPageUrl
	 */
	public void setNextPageUrl(String nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}

	/**
	 * 获取最后一页的链接地址
	 * 
	 * @return
	 */
	public String getPrevPageUrl() {
		if (null == this.prevPageUrl || this.prevPageUrl.length() == 0)
			return this.internalGetPageUrl(this.currentPage - 1);
		return prevPageUrl;
	}

	/**
	 * 获取最后一页的链接地址
	 * 
	 * @param firstPageUrl
	 */
	public void setPrevPageUrl(String prevPageUrl) {
		this.prevPageUrl = prevPageUrl;
	}

	/**
	 * 获得指定页号的地址
	 * 
	 * @param page
	 * @return
	 */
	private String internalGetPageUrl(int page) {
		if (page <= 1)
			page = 1;

		if (page >= getTotalPages())
			page = getTotalPages();

		if (null == this.url_pattern)
			return "";
		return this.url_pattern.replace("{page}", String.valueOf(page));
	}

}
