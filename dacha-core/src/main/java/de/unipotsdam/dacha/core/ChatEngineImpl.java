package de.unipotsdam.dacha.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.unipotsdam.dacha.logging.LoggingService;
import de.unipotsdam.dacha.scoring.ScoredUtterance;
import de.unipotsdam.dacha.types.Utterance;
import de.unipotsdam.dacha.utterance.UtteranceDao;
import de.unipotsdam.dacha.utterance.UtteranceMakingService;

@Service("chatEngine")
public class ChatEngineImpl implements ChatEngine {

	@Autowired
	private LoggingService loggingService;	
	@Autowired
	private UtteranceDao utteranceDao;	
	@Autowired
	private SortUtterancesService sortUtterancesService;	
	@Autowired
	private UtteranceMakingService utteranceMakingService;
			
	private Utterance lastRequestUtterance;
	private Utterance lastResponseUtterance;
	
	@Override
	@Transactional
	public Response getResponse(String request) {
		
		List<Utterance> utterances = utteranceDao.findAll();
		Utterance requestUtterance = utteranceMakingService.makeUtterance(request);
		List<ScoredUtterance> sortedUtterances = 
			sortUtterancesService.doSort(requestUtterance, utterances, lastRequestUtterance, lastResponseUtterance);
		
		Response result = prepareResponse(sortedUtterances);

		loggingService.logMessage(request, result.getResponseString());
		loggingService.setResponse(result);
		
		lastRequestUtterance = requestUtterance;
		lastResponseUtterance = utteranceMakingService.makeUtterance(sortedUtterances.get(0).getUtterance().getNext());
		
		return result;
	}
	
	private Response prepareResponse(List<ScoredUtterance> sortedUtterances) {

		Response response = new Response();
		List<ScoredUtterance> responseStrings = new ArrayList<ScoredUtterance>();
		
		for (ScoredUtterance utterance : sortedUtterances) {
			if (utterance.getUtterance().getNext() != null) {
				responseStrings.add(utterance);
			}
		}
		
		if (responseStrings.isEmpty()) {
			makeNullResponse(response);
		} else {
			response.setResponseString(responseStrings.get(0).getUtterance().getNext());
			response.setResponseUtterances(responseStrings);			
		}
		
		return response;
	}

	private void makeNullResponse(Response response) {
		response.setResponseString("Sorry, can't think of anything to answer.");
		Utterance emptyUtterance = new Utterance();
		emptyUtterance.setValue("No similar sentences availbale.");
		emptyUtterance.setNext("");
		ScoredUtterance emptyRespUtterance = new ScoredUtterance(emptyUtterance);
		response.setResponseUtterances(Arrays.asList(emptyRespUtterance));
	}

	public void setUtteranceRepository(UtteranceDao utteranceRepository) {
		this.utteranceDao = utteranceRepository;
	}
}
