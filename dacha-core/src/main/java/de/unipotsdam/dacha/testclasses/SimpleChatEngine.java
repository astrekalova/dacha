package de.unipotsdam.dacha.testclasses;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import de.unipotsdam.dacha.core.ChatEngine;
import de.unipotsdam.dacha.core.Response;

@Service
public class SimpleChatEngine implements ChatEngine {

	private final Random random = new Random();
	private final List<String> patterns = Arrays.asList(
			"I hate {0}!", 
			"I love {0}!", 
			"I like {0}!", 
			"Do you like {0}?",
			"Over 9000 {0}!!!", 
			"Dieser satz kein werb.", 
			"Haven't I alredy liked you?", 
			"Ob ich Englisch kann? Woerterbuecher schlagen in mir nach!",
			"Wir brauchen heute von allem das Maximum an Geilheit",
			"Ist die Frage, ob die Frage eine gute Frage ist, eine gute Frage? Gute Frage.",
			"Zigaretten sind wie Hamster: Total harmlos, bis man sie in den Mund nimmt und anzuendet.",
			"Deine Mudder...",
			"Kann man auf einen B.Sc. in Astronomie eigentlich einen Master of the Universe setzen?",
			"Kennst du diese Angst, das Wort 'intelligent' falsch geschrieben zu haben?",
			"Wo kaemen wir hin, wenn jeder sagte, wo kaemen wir hin und keiner ginge, um zu sehen, wohin wir kaemen, wenn wir gingen?");

	@Override
	public Response getResponse(String request) {

		final List<String> result = new ArrayList<String>();
		final List<String> requestPatterns = new ArrayList<String>(patterns);
		
		Response response = new Response();
		
		for (int i = 0; i < 5; i++) {
			String pattern = requestPatterns.remove(random.nextInt(requestPatterns.size()));
			result.add(MessageFormat.format(pattern, request));
		}

		response.setResponseString(result.get(0));
		result.remove(0);
		
		return response;
	}
}
