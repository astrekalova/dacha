package de.unipotsdam.dacha.utterance;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import de.unipotsdam.dacha.types.Utterance;
import de.unipotsdam.dacha.types.Utterances;

@Service("utteranceDaoSmall")
public class UtteranceDaoSmall implements UtteranceDao, InitializingBean {
	
	private static final Logger log = LoggerFactory.getLogger(UtteranceDaoSmall.class);
		
	private List<Utterance> data = new ArrayList<Utterance>();	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
	
	private void init() {
		
		InputStream is = null;
		Utterances utterances = null;

		is = getClass().getResourceAsStream(	
				"/data/season02.xml");	
		try {
			JAXBContext context = JAXBContext.newInstance(Utterances.class);
			utterances = (Utterances) context.createUnmarshaller().unmarshal(is);
		} catch(JAXBException e) {
			System.out.println("error parsing xml: ");
			e.printStackTrace();
			System.exit(1);
		}
		data.addAll(utterances.getUtterances());
		log.info("data reading was compelted");
	}
	
	@Override
	public List<Utterance> findAll() {
		return Collections.unmodifiableList(data);	
	}
}