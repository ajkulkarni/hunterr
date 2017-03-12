package hunterr.helper;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import com.owlike.genson.Genson;

public class HunterrApiCalls {
	Genson genson = new Genson();
	
	public Job[] searchJobs(String city, String company, String title, String jobType, int days) {
		Client client = ClientBuilder.newClient();
		String response = client.target("http://api.indeed.com/ads")
				.path("apisearch")
				.queryParam("publisher", "2529823375038480")
				.queryParam("l", city)
				.queryParam("q", "company:" + company + " " + "title:" + title)
				.queryParam("jt", jobType)
				.queryParam("fromage", days)
				.queryParam("v", "2")
				.queryParam("format", "json")
				.queryParam("limit", 100)
				.request()
				.get(String.class);
		IndeedResponse iResponse = genson.deserialize(response, IndeedResponse.class);
		return iResponse.getResults();
	}
}

