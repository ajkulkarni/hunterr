package hunterr;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import hunterr.helper.HunterrApiCalls;
import hunterr.helper.Job;

public class HunterrResponseUtil {

	public SpeechletResponse getLaunchResponse(LaunchRequest request, Session session) {
		// TODO Auto-generated method stub
		String speechText = "Welcome to Hunter! The job hunting assistant";

		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getSearchJobIntentResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Default intent response";

		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getSearchJobIntentResponse(Session session, Slot location) {
		// TODO Auto-generated method stub
		String speechText = "Are you searching for jobs in " + location.getValue() + "?";
		session.setAttribute("location", location.getValue());

		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getYesIntentResponse(Session session) {
		String speechText = "Great! Now what?";

		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getStopIntentResponse(Session session) {
		// TODO Auto-generated method stub
		//Job[] joblist;
		//joblist = HunterrApiCalls.searchJobs((String)session.getAttribute("Location"), (String)session.getAttribute("Company"), (String)session.getAttribute("Job Title"), (String)session.getAttribute("Job Type"), 7);
		
		String speechText = "Great Job Today! Good-bye";
		//String speechText = "Great! Based on your search I found " + joblist.length + " jobs. Good-Bye!";
		return generateTellResponse(speechText);
	}

	public static SpeechletResponse getStartJobTitleResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Sure, what job title should I look for?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Job Title");

		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getCompanyResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Ok, any preference for company?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Company");

		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getLocationResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Awesome, what location are you looking for?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Location");

		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getJobTypeResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "So what Job Type are you interested in?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Job Type");

		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getStopIntentResponse(Session session, String result) {
		// TODO Auto-generated method stub
		String speechText = result;
		session.setAttribute((String)session.getAttribute("lastQuestion"), result);
		Job[] joblist;
		joblist = HunterrApiCalls.searchJobs((String)session.getAttribute("Location"), (String)session.getAttribute("Company"), (String)session.getAttribute("Job Title"), (String)session.getAttribute("Job Type"), 7);
		
		//String speechText = "Great Job Today! Good-bye";
		speechText = "Great! Based on your search I found " + joblist.length + " jobs. Good-Bye!";
		return generateTellResponse(speechText);
	}

	public static SpeechletResponse getCompanyResponse(Session session, String response) {
		// TODO Auto-generated method stub
		session.setAttribute((String)session.getAttribute("lastQuestion"), response);
		String speechText = "You said " + response + ". Ok, any preference for company?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Company");
		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getLocationResponse(Session session, String response) {
		// TODO Auto-generated method stub
		session.setAttribute((String)session.getAttribute("lastQuestion"), response);
		String speechText = "You said " + response + ". Awesome, what location are you looking for?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Location");
		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getJobTypeResponse(Session session, String response) {
		// TODO Auto-generated method stub
		session.setAttribute((String)session.getAttribute("lastQuestion"), response);
		String speechText = "You said " + response + "So what Job Type are you interested in?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Job Type");
		return generateAskResponse(speechText);
	}

	private static SpeechletResponse generateAskResponse(String speechText) {
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}
	
	private static SpeechletResponse generateTellResponse(String speechText) {
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return SpeechletResponse.newTellResponse(speech);
	}

	public static SpeechletResponse askQuestion(Session session, String speechText) {
		return generateAskResponse(speechText);
	}

	public static SpeechletResponse getNoIntentResponse(Session session) {
		String speechText = "Apologies! What now?";

		return generateAskResponse(speechText);
	}

}
