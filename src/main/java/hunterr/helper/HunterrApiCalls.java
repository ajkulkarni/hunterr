package hunterr.helper;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import com.owlike.genson.Genson;

import hunterr.HunterrResponseUtil;

public class HunterrApiCalls {
	static Genson genson = new Genson();
	
	public static Job[] searchJobs(String city, String company, String title, String jobType, String days) {
		Client client = ClientBuilder.newClient();
		
		if(HunterrResponseUtil.isNull(company) || company.equalsIgnoreCase("skip")) {
			company = "";
		}
		
		if(days == null || days.equalsIgnoreCase("skip") || days.equalsIgnoreCase("0")) {
			days = "7";
		}
		
		String response = client.target("http://api.indeed.com/ads")
				.path("apisearch")
				.queryParam("publisher", "2529823375038480")
				.queryParam("q", "company:" + company + " " + "title:" + title)
				.queryParam("l", city)
				.queryParam("jt", jobType)
				.queryParam("fromage", Integer.parseInt(days))
				.queryParam("v", "2")
				.queryParam("format", "json")
				.queryParam("limit", 100)
				.request()
				.get(String.class);
		IndeedResponse iResponse = genson.deserialize(response, IndeedResponse.class);
		return iResponse.getResults();
	}
}

