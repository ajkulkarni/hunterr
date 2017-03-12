package hunterr.helper;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.owlike.genson.Genson;

import hunterr.HunterrResponseUtil;

public class HunterrApiCalls {
	static Genson genson = new Genson();
	static Job[] jobs;
	
	public static int searchJobs(String city, String company, String title, String jobType, String days) {

		Client client = ClientBuilder.newClient();

		String companyCondition = "";
		if(!(HunterrResponseUtil.isNull(company) || company.equalsIgnoreCase("skip"))) {
			companyCondition = "company:" + company+ " ";
		}
		
		if(days == null || days.equalsIgnoreCase("skip") || days.equalsIgnoreCase("0")) {
			days = "30";
		}
		
		String response = client.target("http://api.indeed.com/ads")
				.path("apisearch")
				.queryParam("publisher", "2529823375038480")
				.queryParam("q", companyCondition + "title:" + title)
				.queryParam("l", city)
				.queryParam("jt", jobType)
				.queryParam("fromage", Integer.parseInt(days))
				.queryParam("v", "2")
				.queryParam("format", "json")
				.queryParam("limit", 100)
				.request()
				.get(String.class);

		IndeedResponse iResponse = genson.deserialize(response, IndeedResponse.class);
		jobs = iResponse.getResults();
		return jobs.length;
	}

	public static void writeToSheet() throws IOException {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("range", "Sheet1!A1:F1");
		jsonObject.put("majorDimension", "ROWS");
		for (Job j : jobs) {
			jsonArray.put(new JSONArray().put(j.getCity()).put(j.getCompany()).put(j.getFormattedRelativeTime())
					.put(j.getJobtitle()).put(j.getState()).put(j.getUrl()));
		}
		jsonObject.put("values", jsonArray);
		System.out.println(jsonObject.toString());
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
			HttpPost request = new HttpPost(
					"https://sheets.googleapis.com/v4/spreadsheets/1JLzHOBy-JLkdWbDdwgUNKQDxkC5q7RC2MAw3M6GsPLU/values/Sheet1!A1:F1:append?valueInputOption=USER_ENTERED");
			StringEntity params = new StringEntity(jsonObject.toString());
			request.addHeader("content-type", "application/json");
			request.addHeader("Authorization",
					"Bearer ya29.GlsMBILl4Ko-2bAn6_fssvq4fnDfdRBJd6zD3LuP2E4cRrazTh10GuZQkr_TBb1cTZarMCiPO3mRnnG3dxmv2OJ6e9hfdoWwFfT82JGLokXjQSf8OX012meIcrdS");
			request.setEntity(params);
			httpClient.execute(request);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			httpClient.close();
		}

	}

	/*
	 * public static void main(String args[]) throws IOException {
	 * searchJobs("seattle", "microsoft", "software engineer", "fulltime", 30);
	 * writeToSheet(); }
	 */
}
