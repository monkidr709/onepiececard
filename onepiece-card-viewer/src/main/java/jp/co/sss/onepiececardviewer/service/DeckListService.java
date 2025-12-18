package jp.co.sss.onepiececardviewer.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.DeckList;
import jp.co.sss.onepiececardviewer.repository.DeckListRepository;

@Service
public class DeckListService {
	
	@Autowired
	DeckListRepository deckListRepository;
	
	/**
	 * userIdでデッキリストを取得 (Id昇順)
	 * @param userId
	 * @return
	 */
	public List<DeckList> getDeckList(Integer userId) {
		return deckListRepository.findByUserNameIdOrderByIdAsc(userId);
	}
	
	/**
	 * deckIdでデッキリストを取得
	 * @param deckId
	 * @return
	 */
	public Optional<DeckList> getDeckListByDeckId(Integer deckId) {
		return deckListRepository.findById(deckId);
	}
	
	/**
	 * デッキ削除
	 * @param deckId
	 */
	public void deletedDeck(Integer deckId) {
		DeckList deck = deckListRepository.findById(deckId).orElseThrow();
		LocalDate deletedDate = LocalDate.now();
		
		deck.setDeleted(true);
		deck.setDeletedDate(deletedDate);
		
		deckListRepository.save(deck);
	}

}
