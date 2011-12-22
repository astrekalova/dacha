package de.unipotsdam.dacha.utterance;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import de.unipotsdam.dacha.types.Dependency;
import de.unipotsdam.dacha.types.Edge;
import de.unipotsdam.dacha.types.Sentence;
import de.unipotsdam.dacha.types.Token;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NormalizedNamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.semgraph.SemanticGraph;
import edu.stanford.nlp.trees.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;

@Service("linguisticInfoService")
public class LinguisticInfoServiceImpl implements LinguisticInfoService {

	public List<Sentence> addLinguisticInfo(Annotation annotation) {
		
		List<Sentence> sentences = new ArrayList<Sentence>();
				
		if (annotation.get(CoreAnnotations.SentencesAnnotation.class) != null) {
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				
				Sentence newSentence = new Sentence();
				
				List<Token> tokens = new ArrayList<Token>();
				List<CoreLabel> toks = sentence.get(CoreAnnotations.TokensAnnotation.class);
				for (CoreLabel tok : toks) {
					Token token = addWordInfo(tok);
					tokens.add(token);
				}
				
				newSentence.setTokens(tokens);
				
				Tree tree = sentence.get(CoreAnnotations.TreeAnnotation.class);
				
				if (tree != null) {
					List<Edge> edges = addParseTreeInfo(tree);
					
					newSentence.setEdges(edges);
					
					List<Dependency> dependencies = addDependencyTreeInfo(
							sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class),
							toks);
					
					newSentence.setDependencies(dependencies);
				}				
				sentences.add(newSentence);
			}
		}
		
		return sentences;
	}
	
	private Token addWordInfo(CoreMap tok) {

		Token token = new Token();
		token.setWord(tok.get(TextAnnotation.class));
		token.setLemma(tok.get(LemmaAnnotation.class));
		token.setPos(tok.get(PartOfSpeechAnnotation.class));
		token.setNer(tok.get(NormalizedNamedEntityTagAnnotation.class));		
		
		return token;
	}

	private List<Edge> addParseTreeInfo(Tree tree) {
		
		List<Edge> result = new ArrayList<Edge>();

		for (Tree child : tree.getChildrenAsList()) {
			if (!child.isLeaf()) {
				Edge edge = new Edge();
				edge.setStartNode(tree.label().value());
				edge.setEndNode(child.label().value());
				result.add(edge);
				result.addAll(addParseTreeInfo(child));
			}
		}
		
		return result;
	}

	private List<Dependency> addDependencyTreeInfo(SemanticGraph graph, List<CoreLabel> tokens) {

		List<Dependency> dependencies = new ArrayList<Dependency>();
		
		if (graph != null) {
			for (SemanticGraphEdge edge : graph.edgeList()) {
				Dependency dependency = new Dependency();
				String rel = edge.getRelation().toString();
				rel = rel.replaceAll("\\s+", "");
				int source = edge.getSource().index();
				int target = edge.getTarget().index();

				dependency.setType(rel);
				dependency.setGovernor(tokens.get(source - 1).word());				
				dependency.setDependent(tokens.get(target - 1).word());
				
				dependencies.add(dependency);
			}
		}
		
		return dependencies;
	}	
}
