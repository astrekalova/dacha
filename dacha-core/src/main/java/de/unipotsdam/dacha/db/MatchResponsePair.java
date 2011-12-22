package de.unipotsdam.dacha.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MatchResponsePair {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "match_str") // "match" is reserved word for MySQL
	private String match;
	
	private String response;
	
	@Column(name = "positive_id")
	private Long positiveId;

	@Column(name = "negative_id")
	private Long negativeId;

	private int ranking;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getRanking() {
		return ranking;
	}	
}
