package jp.co.sss.onepiececardviewer.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sss.onepiececardviewer.entity.DeckList;
import jp.co.sss.onepiececardviewer.repository.DeckListRepository;

@Service
public class KeepDeckService {
	
	@Autowired
	DeckListRepository deckListRepository;
	
	/**
	 * デッキ保存
	 * @param userId
	 * @param deckName
	 * @param publishDeck
	 * @param leaderId
	 * @param deckCardId
	 */
	@Transactional
	public void keepDeck(Integer userId, String deckName, boolean publishDeck, Integer leaderId, List<Integer> deckCardId) {
		Integer[] deckCardIds = deckCardId.toArray(new Integer[0]);
		LocalDate createdDate = LocalDate.now();
		
		DeckList keepDeck = new DeckList();
		keepDeck.setUserNameId(userId);
		keepDeck.setDeckName(deckName);
		keepDeck.setPublishDeck(publishDeck);
		keepDeck.setLeaderCardId(leaderId);
		keepDeck.setDeckCardId(deckCardIds);
		keepDeck.setCreatedDate(createdDate);
		keepDeck.setDeleted(false);
		keepDeck.setDeletedDate(null);
		
		deckListRepository.save(keepDeck);
	}
	
	/**
	 * デッキIDからデッキ情報を取得
	 * @param deckId
	 * @return
	 */
	public DeckList getProductByDeckId(Integer deckId) {
		return deckListRepository.findById(deckId)
				.orElseThrow(() -> new RuntimeException("デッキが見つかりません: " + deckId));
	}
	
	/**
	 * デッキ更新
	 * @param deckId
	 * @param deckName
	 * @param publishDeck
	 * @param deckCardId
	 */
	@Transactional
	public void changeDeck(Integer deckId, String deckName, boolean publishDeck, List<Integer> deckCardId) {
		Integer[] deckCardIds = deckCardId.toArray(new Integer[0]);
		LocalDate createdDate = LocalDate.now();
		
		DeckList changeDeck = getProductByDeckId(deckId);
		changeDeck.setDeckName(deckName);
		changeDeck.setPublishDeck(publishDeck);
		changeDeck.setDeckCardId(deckCardIds);
		changeDeck.setCreatedDate(createdDate);
		
		deckListRepository.save(changeDeck);
	}

}
