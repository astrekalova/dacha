package de.unipotsdam.dacha.types;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Sentence {

	private List<Token> tokens = new ArrayList<Token>();
	private List<Dependency> dependencies = new ArrayList<Dependency>();
	private List<Edge> edges = new ArrayList<Edge>();

	@XmlElementWrapper(name = "tokens")
	@XmlElement(name = "token")
	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	@XmlElementWrapper(name = "ccprocessed-dependencies")
	@XmlElement(name = "dep")
	public List<Dependency> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<Dependency> basicDependencies) {
		this.dependencies = basicDependencies;
	}
	
	@XmlElementWrapper(name = "parse-tree")
	@XmlElement(name = "edge")
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
}
