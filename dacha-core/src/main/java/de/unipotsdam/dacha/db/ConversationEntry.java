package de.unipotsdam.dacha.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class ConversationEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String request;
	private String response;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "positive_id")
	private List<MatchResponsePair> positivePairs = new ArrayList<MatchResponsePair>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "negative_id")
	private List<MatchResponsePair> negativePairs = new ArrayList<MatchResponsePair>();

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public List<MatchResponsePair> getPositivePairs() {
		return positivePairs;
	}
	public void setPositivePairs(List<MatchResponsePair> positivePairs) {
		this.positivePairs = positivePairs;
	}
	public List<MatchResponsePair> getNegativePairs() {
		return negativePairs;
	}
	public void setNegativePairs(List<MatchResponsePair> negativePairs) {
		this.negativePairs = negativePairs;
	}
}
