package hunterr;

import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class HunterrResponseUtil {

	public SpeechletResponse getLaunchResponse(LaunchRequest request, Session session) {
		// TODO Auto-generated method stub
		String speechText = "Welcome to Hunter! The job hunting assistant";

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getSearchJobIntentResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Default intent response";

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getSearchJobIntentResponse(Session session, Slot location) {
		// TODO Auto-generated method stub
		String speechText = "Are you searching for jobs in " + location.getValue() + "?";
		session.setAttribute("location", location.getValue());
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getYesIntentResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Great!";

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getStopIntentResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Great Job Today! Good-bye";

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return SpeechletResponse.newTellResponse(speech);
	}

	public static SpeechletResponse getStartJobTitleResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Sure, what job title should I look for?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Job Title");
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getCompanyResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Ok, any preference for company?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Company");
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getLocationResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "Awesome, what location are you looking for?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Location");
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getJobTypeResponse(Session session) {
		// TODO Auto-generated method stub
		String speechText = "So what Job Type are you interested in?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Job Type");
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getStopIntentResponse(Session session, String result) {
		// TODO Auto-generated method stub
		String speechText = result;

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		return SpeechletResponse.newTellResponse(speech);
	}

	public static SpeechletResponse getCompanyResponse(Session session, String response) {
		// TODO Auto-generated method stub
		String speechText = "You said " + response + ". Ok, any preference for company?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Company");
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getLocationResponse(Session session, String response) {
		// TODO Auto-generated method stub
		String speechText = "You said " + response + ". Awesome, what location are you looking for?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Location");
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

	public static SpeechletResponse getJobTypeResponse(Session session, String response) {
		// TODO Auto-generated method stub
		String speechText = "You said " + response + "So what Job Type are you interested in?";
		session.removeAttribute("lastQuestion");
		session.setAttribute("lastQuestion", "Job Type");
		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		// Create reprompt
		Reprompt reprompt = new Reprompt();
		reprompt.setOutputSpeech(speech);

		return SpeechletResponse.newAskResponse(speech, reprompt);
	}

}
