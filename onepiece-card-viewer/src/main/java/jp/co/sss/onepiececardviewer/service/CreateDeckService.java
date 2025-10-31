package jp.co.sss.onepiececardviewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.CardList;
import jp.co.sss.onepiececardviewer.repository.CardListRepository;

@Service
public class CreateDeckService {
	
	@Autowired
	private CardListRepository cardListRepository;
	
	public List<CardList> getCardListByCardTypeLeader() {
		return cardListRepository.findByCardType("リーダー");
	}

}
