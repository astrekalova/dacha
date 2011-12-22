package de.unipotsdam.dacha.utterance;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.unipotsdam.dacha.types.Sentence;
import de.unipotsdam.dacha.types.Utterance;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Service("utteranceMakingService")
public class UtteranceMakingServiceImpl implements UtteranceMakingService, InitializingBean {

	@Autowired
	LinguisticInfoService linguisticInfoService;
	StanfordCoreNLP pipeline = null;
		
	@Override
	public void afterPropertiesSet() throws Exception {
		
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma, parse");
		
		pipeline = new StanfordCoreNLP(props);		
	}
	
	@Override
	public Utterance makeUtterance(String request) {

		Utterance queryUtterance = new Utterance();
		
		Annotation annotation = pipeline.process(request);
		
		List<Sentence> sentences = linguisticInfoService.addLinguisticInfo(annotation);
				
		queryUtterance.setSentences(sentences);
		queryUtterance.setValue(request);
		
		return queryUtterance;
	}
}

