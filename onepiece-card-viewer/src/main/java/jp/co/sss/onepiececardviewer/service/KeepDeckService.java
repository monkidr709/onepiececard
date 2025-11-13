package jp.co.sss.onepiececardviewer.service;

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
	
	@Transactional
	public void keepDeck(Integer userId, String deckName, boolean publishDeck, Integer leaderId, List<Integer> deckCardId) {
		Integer[] deckCardIds = deckCardId.toArray(new Integer[0]);
		
		DeckList keepDeck = new DeckList();
		keepDeck.setUserNameId(userId);
		keepDeck.setDeckName(deckName);
		keepDeck.setPublishDeck(publishDeck);
		keepDeck.setLeaderCardId(leaderId);
		keepDeck.setDeckCardId(deckCardIds);
		keepDeck.setDeleted(false);
		
		deckListRepository.save(keepDeck);
	}

}
