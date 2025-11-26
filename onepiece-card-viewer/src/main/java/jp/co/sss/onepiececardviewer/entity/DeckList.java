package jp.co.sss.onepiececardviewer.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "decks")
public class DeckList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "user_name_id")
	private Integer userNameId;
	
	@Column (name = "deck_name")
	private String deckName;
	
	@Column (name = "publish_deck")
	private boolean publishDeck;
	
	@Column (name = "leader_card_id")
	private Integer leaderCardId;
	
	@Column (name = "deck_card_id")
	private Integer[] deckCardId;
	
	@Column (name = "created_at")
	private LocalDate createdDate;
	
	@Column (name = "deleted")
	private boolean deleted;
	
	@Column (name = "deleted_at")
	private LocalDate deletedDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserNameId() {
		return userNameId;
	}

	public void setUserNameId(Integer userNameId) {
		this.userNameId = userNameId;
	}

	public String getDeckName() {
		return deckName;
	}

	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	public boolean isPublishDeck() {
		return publishDeck;
	}

	public void setPublishDeck(boolean publishDeck) {
		this.publishDeck = publishDeck;
	}

	public Integer getLeaderCardId() {
		return leaderCardId;
	}

	public void setLeaderCardId(Integer leaderCardId) {
		this.leaderCardId = leaderCardId;
	}

	public Integer[] getDeckCardId() {
		return deckCardId;
	}

	public void setDeckCardId(Integer[] deckCardId) {
		this.deckCardId = deckCardId;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public LocalDate getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(LocalDate deletedDate) {
		this.deletedDate = deletedDate;
	}

}
