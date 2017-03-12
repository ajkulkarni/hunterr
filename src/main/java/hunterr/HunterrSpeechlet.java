package hunterr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;

import scorekeeper.ScoreKeeperSpeechlet;
import scorekeeper.SkillContext;

public class HunterrSpeechlet implements Speechlet{
	 private static final Logger log = LoggerFactory.getLogger(ScoreKeeperSpeechlet.class);

	    //private AmazonDynamoDBClient amazonDynamoDBClient;

	    private HunterrResponseUtil responseUtil;

	    private SkillContext skillContext;
	    @Override
		public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
			// TODO Auto-generated method stub
	        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
	                session.getSessionId());

	        //initializeComponents();

	        // if user said a one shot command that triggered an intent event,
	        // it will start a new session, and then we should avoid speaking too many words.
	        skillContext.setNeedsMoreHelp(false);
		}
	    
		@Override
		public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
			// TODO Auto-generated method stub
	        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
	                session.getSessionId());

	        skillContext.setNeedsMoreHelp(true);
	        return responseUtil.getLaunchResponse(request, session);
		}

		@Override
		public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
			// TODO Auto-generated method stub
	        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
	                session.getSessionId());
	        // any cleanup logic goes here
		}
		
		

}
