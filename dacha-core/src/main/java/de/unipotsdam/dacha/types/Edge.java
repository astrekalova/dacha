package de.unipotsdam.dacha.types;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Edge {

	private String startNode;
	private String endNode;

	public String getStartNode() {
		return startNode;
	}

	public void setStartNode(String startNode) {
		this.startNode = startNode;
	}

	public String getEndNode() {
		return endNode;
	}

	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}

	@Override
	public String toString() {
		return "Edge [" + startNode +"_" + endNode + "]";
	}
}
