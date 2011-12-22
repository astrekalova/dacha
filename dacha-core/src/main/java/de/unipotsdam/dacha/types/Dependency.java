package de.unipotsdam.dacha.types;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Dependency {

	private String type;
	private String governor;
	private String dependent;
	
	@XmlAttribute
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGovernor() {
		return governor;
	}
	public void setGovernor(String governor) {
		this.governor = governor;
	}
	public String getDependent() {
		return dependent;
	}
	public void setDependent(String dependent) {
		this.dependent = dependent;
	}
	@Override
	public String toString() {
		return "Dependency [" + type + "_" + governor
				+ "_" + dependent + "]";
	}	
}
