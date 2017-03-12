package hunterr;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;

import hunterr.helper.HunterrApiCalls;

public class HunterrSpeechlet implements Speechlet {
	private static final Logger log = LoggerFactory.getLogger(HunterrSpeechlet.class);

	// private AmazonDynamoDBClient amazonDynamoDBClient;

	private HunterrResponseUtil responseUtil;

	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
		// TODO Auto-generated method stub
		log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		// if user said a one shot command that triggered an intent event,
		// it will start a new session, and then we should avoid speaking too
		// many words.
	}

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
		// TODO Auto-generated method stub
		log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		Intent intent = request.getIntent();

		if ("SearchJobIntent".equals(intent.getName())) {
			session.setAttribute("lastQuery", "");
			return HunterrResponseUtil.getStartJobTitleResponse(session);
		} else if ("ResponseIntent".equals(intent.getName())) {
			return processQuestion(intent, session, true);
		} else if ("AMAZON.YesIntent".equals(intent.getName())) {
			if(((String)session.getAttribute("lastQuery")).equals("writeToFile")) {
				try {
					HunterrApiCalls.writeToSheet();
				} catch (IOException e) {
					e.printStackTrace();
				}
				session.setAttribute("lastQuery", "searchDone");
			}
			
			return HunterrResponseUtil.getYesIntentResponse(session);
		} else if ("AMAZON.NoIntent".equals(intent.getName())) {
			if(((String)session.getAttribute("lastQuery")).equals("searchDone")) {
				return HunterrResponseUtil.generateTellResponse("Alright! Later");
			} else if(((String)session.getAttribute("lastQuery")).equals("writeToFile")) {
				return HunterrResponseUtil.generateTellResponse("Alright! Later");
			}
			return processQuestion(intent, session, false);
		} else if ("AMAZON.StopIntent".equals(intent.getName())) {
			return HunterrResponseUtil.getStopIntentResponse(session);
		} else if ("AMAZON.HelpIntent".equals(intent.getName())) {
			return HunterrResponseUtil.getHelpResponse(session);
		} else if ("RepeatSearchJobIntent".equals(intent.getName())) {
			session.setAttribute("lastQuery", "");
			String response = intent.getSlot("jobtitle").getValue();
			session.setAttribute("Job Title", response);
			return HunterrResponseUtil.getStopIntentResponse(session);
		} else {
			throw new IllegalArgumentException("Unrecognized intent: " + intent.getName());
		}
	}

	private SpeechletResponse processQuestion(Intent intent, Session session, boolean b) {
		String response;
		String lastQuestion = (String) session.getAttribute("lastQuestion");
		switch (lastQuestion) {
			case "Job Title":
				if(b) response = intent.getSlot("jobtitle").getValue();
				else response = "skip";
				return HunterrResponseUtil.getCompanyResponse(session, response);
			case "Company":
				if(b) response = intent.getSlot("company").getValue();
				else response = "skip";
				return HunterrResponseUtil.getLocationResponse(session, response);
			case "Location":
				if(b) {
					response = "";
					String city = intent.getSlot("locationcity").getValue();
					if (!HunterrResponseUtil.isNull(city)) {
						response = city;
					}
			
					String state = intent.getSlot("locationstate").getValue();
					if (!HunterrResponseUtil.isNull(state)) {
						response += " " + state;
					}
				}
				else response = "skip";
				return HunterrResponseUtil.getJobTypeResponse(session, response);
			case "Job Type":
				if(b) response = intent.getSlot("jobtype").getValue();
				else response = "skip";
				return HunterrResponseUtil.getHistoryResponse(session, response);
			default:
				if(b) response = intent.getSlot("history").getValue();
				else response = "skip";
				return HunterrResponseUtil.getStopIntentResponse(session, response);
			}
	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
		// TODO Auto-generated method stub
		log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		return responseUtil.getLaunchResponse(request, session);
	}

	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
		// TODO Auto-generated method stub
		log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		// any cleanup logic goes here
	}
}
