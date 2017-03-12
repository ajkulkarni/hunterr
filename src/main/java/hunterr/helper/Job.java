package hunterr.helper;

class Job {
	String company;
	String jobtitle;
	String formattedRelativeTime;
	String city;
	String state;
	String url;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getFormattedRelativeTime() {
		return formattedRelativeTime;
	}
	public void setFormattedRelativeTime(String formattedRelativeTime) {
		this.formattedRelativeTime = formattedRelativeTime;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String toString() {
		return company + " " + jobtitle + " " + formattedRelativeTime + " " + city + " " + state + "\n" + url;
	}
}
