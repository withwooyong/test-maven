package push;


public class ListObject extends BaseObject {
	
	private static final long serialVersionUID = 2331372974346840576L;
	
	// 페이지 관련
	private int count = 0;
	private int pageNo = 1;
	private int pageSize = 10;

    // 뒤로가기
    private String backUrl;

    // 검색 조건
    private String search_start_date;
    private String search_end_date;
    private String search_type;
    private String search_keyword;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartNum() {
		int pageSize = getPageSize();
		return getPageNo() * pageSize - pageSize + 1;
	}

	public int getEndNum() {
		return getPageNo() * getPageSize();
	}

    public String getSearch_start_date() {
        return search_start_date;
    }

    public void setSearch_start_date(String search_start_date) {
        this.search_start_date = search_start_date;
    }

    public String getSearch_end_date() {
        return search_end_date;
    }

    public void setSearch_end_date(String search_end_date) {
        this.search_end_date = search_end_date;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getSearch_keyword() {
        return search_keyword;
    }

    public void setSearch_keyword(String search_keyword) {
        this.search_keyword = search_keyword;
    }
}
