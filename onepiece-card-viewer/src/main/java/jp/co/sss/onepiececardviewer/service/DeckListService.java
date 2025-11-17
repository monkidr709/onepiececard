package jp.co.sss.onepiececardviewer.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.DeckList;
import jp.co.sss.onepiececardviewer.repository.DeckListRepository;

@Service
public class DeckListService {
	
	@Autowired
	DeckListRepository deckListRepository;
	
	// userNameIdによる検索 (Id昇順)
	public List<DeckList> getDeckList(Integer userId) {
		return deckListRepository.findByUserNameIdOrderByIdAsc(userId);
	}
	
	// デッキ削除 (deletedをtrueに変更する)
	public void deletedDeck(Integer deckId) {
		DeckList deck = deckListRepository.findById(deckId).orElseThrow();
		LocalDate deletedDate = LocalDate.now();
		
		deck.setDeleted(true);
		deck.setDeletedDate(deletedDate);
		
		deckListRepository.save(deck);
	}

}
