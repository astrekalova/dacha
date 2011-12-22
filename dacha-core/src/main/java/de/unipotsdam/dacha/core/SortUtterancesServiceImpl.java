package de.unipotsdam.dacha.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.unipotsdam.dacha.scoring.ScoredUtterance;
import de.unipotsdam.dacha.scoring.ScoringService;
import de.unipotsdam.dacha.types.Utterance;
import de.unipotsdam.dacha.utterance.UtteranceMakingService;

@Service("sortUtterancesService")
public class SortUtterancesServiceImpl implements SortUtterancesService {

	@Autowired
	ScoringService scoringService;
	
	final int utteranceNumber = 20;

	@Override
	public List<ScoredUtterance> doSort(Utterance request,
			List<Utterance> utterances, 
			Utterance lastRequestUtterance,
			Utterance lastResponseUtterance) {

		List<ScoredUtterance> scoredUtterances = new ArrayList<ScoredUtterance>();

		for (int i = 0; i < utterances.size(); i++) {

			Utterance utterance = utterances.get(i);
			ScoredUtterance scoredUtterance = new ScoredUtterance(utterance);		
			ScoredUtterance resultUtterance = null;
			if (i < utterances.size() - 1) {
				resultUtterance = scoringService.scoreUtterance(request, scoredUtterance, utterances.get(i+1));
			} else {
				resultUtterance = scoringService.scoreUtterance(request, scoredUtterance, null);
			}
			double historyScore = 0;
			// if current utterance has two preceding utterances to assess the context
			if (i > 1) {
				historyScore = scoringService.scoreHistory(
						utterances.get(i - 2), utterances.get(i - 1),
						lastRequestUtterance, lastResponseUtterance);
			}
			
			resultUtterance.setHistoryScore(historyScore);
			resultUtterance.setOverallScore(resultUtterance.getOverallScore() + historyScore);
			scoredUtterances.add(resultUtterance);
		}

		Collections.sort(scoredUtterances, UtteranceComparator.INSTANCE);
		scoredUtterances = filterUtterances(scoredUtterances);

		return scoredUtterances;
	}

	private List<ScoredUtterance> filterUtterances(List<ScoredUtterance> comparedUtterances) {

		List<ScoredUtterance> result = new ArrayList<ScoredUtterance>();

		int n = 0;
		if (comparedUtterances.size() >= utteranceNumber) {
			n = utteranceNumber;
		} else {
			n = comparedUtterances.size();
		}

		for (int i = 0; i < n; i++) {
			if (!comparedUtterances.get(i).getUtterance().getNext().equals("")) {
				result.add(comparedUtterances.get(i));
			}
		}

		return result;
	}
}
