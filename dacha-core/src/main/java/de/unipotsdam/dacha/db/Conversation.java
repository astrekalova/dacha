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
import javax.persistence.OrderBy;

@Entity
public class Conversation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("date")
	@JoinColumn(name = "conversation_id")
	private List<ConversationEntry> conversationEntries = new ArrayList<ConversationEntry>();
	
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
	public List<ConversationEntry> getConversationEntries() {
		return conversationEntries;
	}
	public void setConversationEntries(List<ConversationEntry> conversationEntries) {
		this.conversationEntries = conversationEntries;
	}
}
