package de.unipotsdam.dacha.utils;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import org.junit.Before;
import org.junit.Test;


public class OpenNLPtoolsTest {

	private SentenceModel modelSD = null;
	private TokenizerModel modelTok = null;	
	private TokenNameFinderModel modelPerson = null;
	private POSModel modelPOS = null;
	private ChunkerModel modelChunker = null;
	private ParserModel parserModel = null;
	
//	@Test
	public void testOpenNLPTools() {		
		
		SentenceDetectorME sentenceDetector = new SentenceDetectorME(modelSD);
		List<String> sentences = Arrays.asList(sentenceDetector.sentDetect("  First sentence. Second sentence. "));		
		assertEquals(Arrays.asList("First sentence.", "Second sentence."), sentences);
		
		
		Tokenizer tokenizer = new TokenizerME(modelTok);
		List<String> tokens = Arrays.asList(tokenizer.tokenize("First sentence."));
		assertEquals(Arrays.asList("First", "sentence", "."), tokens);
		
		NameFinderME nameFinder = new NameFinderME(modelPerson);	
		String[] sentence = new String[]{
			    "Anna",
			    "Mueller",
			    "is",
			    "61",
			    "years",
			    "old",
			    "."
			    };
		List<Span> nameSpans = Arrays.asList(nameFinder.find(sentence));
		
		assertEquals(0, nameSpans.get(0).getStart());
		assertEquals("person", nameSpans.get(0).getType());
		
		POSTaggerME tagger = new POSTaggerME(modelPOS);
		String sent[] = new String[]{"Most", "large", "cities", "in", "the", "US", "had",
                "morning", "and", "afternoon", "newspapers", "."};		  
		List<String> tags = Arrays.asList(tagger.tag(sent));
//		System.out.println(tags.toString());
		
		ChunkerME chunker = new ChunkerME(modelChunker);
		String[] chunks = chunker.chunk(sent, tagger.tag(sent));
//		System.out.println(Arrays.asList(chunks).toString());		
	}
	
//	@Test
	public void testOpenNLPParser() {
		
		Parser parser = ParserFactory.create(parserModel);
		String parserInput = "The quick brown fox jumps over the lazy dog .";
		Parse topParses[] = ParserTool.parseLine(parserInput, parser, 1);
		System.out.println(Arrays.asList(topParses).toString());
	}
	
//	@Before
	public void setUp() {
		
		InputStream modelInSD = null;
		
		try {
			modelInSD = new FileInputStream("models//en-sent.bin");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
				
		try {
			modelSD = new SentenceModel(modelInSD);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (modelInSD != null) {
				try {
					modelInSD.close();
				}
				catch (IOException e) {
				}
			}
		}
		
		InputStream modelInTok = null;
		try {
			modelInTok = new FileInputStream("models//en-token.bin");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
		  modelTok = new TokenizerModel(modelInTok);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelInTok != null) {
		    try {
		      modelInTok.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		
		InputStream modelInPerson = null;
		try {
			modelInPerson = new FileInputStream("models//en-ner-person.bin");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
		  modelPerson = new TokenNameFinderModel(modelInPerson);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelInPerson != null) {
		    try {
		      modelInPerson.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		
		InputStream modelInPOS = null;		
		try {
		  modelInPOS = new FileInputStream("models//en-pos-maxent.bin");
		  modelPOS = new POSModel(modelInPOS);
		}
		catch (IOException e) {
		  // Model loading failed, handle the error
		  e.printStackTrace();
		}
		finally {
		  if (modelInPOS != null) {
		    try {
		      modelInPOS.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		
		InputStream modelInChunker = null;		
		try {
		  modelInChunker = new FileInputStream("models//en-chunker.bin");
		  modelChunker = new ChunkerModel(modelInChunker);
		}
		catch (IOException e) {
		  // Model loading failed, handle the error
		  e.printStackTrace();
		}
		finally {
		  if (modelInChunker != null) {
		    try {
		      modelInChunker.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		
		InputStream modelInParse = null;
		try {
			modelInParse = new FileInputStream("models//en-parser-chunking.bin");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
		  parserModel = new ParserModel(modelInParse);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelInParse != null) {
		    try {
		      modelInParse.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
	}
}
