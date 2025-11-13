package jp.co.sss.onepiececardviewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.DeckList;
import jp.co.sss.onepiececardviewer.repository.DeckListRepository;

@Service
public class DeckListService {
	
	@Autowired
	DeckListRepository deckListRepository;
	
	// 全件検索 (Id昇順)
	public List<DeckList> getAllDeckList() {
		return deckListRepository.findAllByOrderByIdAsc();
	}

}
