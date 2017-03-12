package hunterr.helper;

class IndeedResponse {
	
	int version;
	String query;
	String location;
	String paginationPayload;
	int radius;
	boolean dupefilter;
	boolean highlight;
	int totalResults;
	int start;
	int end;
	int pageNumber;
	Job[] results;
	public Job[] getResults() {
		return results;
	}
	public void setResults(Job[] results) {
		this.results = results;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPaginationPayload() {
		return paginationPayload;
	}
	public void setPaginationPayload(String paginationPayload) {
		this.paginationPayload = paginationPayload;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public boolean isDupefilter() {
		return dupefilter;
	}
	public void setDupefilter(boolean dupefilter) {
		this.dupefilter = dupefilter;
	}
	public boolean isHighlight() {
		return highlight;
	}
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	public String toString() {
		return results + ""+ totalResults;
	}
}
