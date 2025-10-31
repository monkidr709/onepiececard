package jp.co.sss.onepiececardviewer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.CardList;
import jp.co.sss.onepiececardviewer.repository.CardListRepository;

@Service
public class CreateDeckService {
	
	@Autowired
	private CardListRepository cardListRepository;
	
	//cardTypeが"リーダー"のカード検索
	public List<CardList> getCardListByCardTypeLeader() {
		return cardListRepository.findByCardType("リーダー");
	}
	
	//cardColorとcardTypeが"リーダー"以外による検索
	public List<CardList> getCardListByCardColorAndCardType(String cardColor) {
		String[] cardTypes = {"キャラ", "イベント", "ステージ"};
		List<CardList> findByCardColorAndCardType = new ArrayList<>();
		for (String cardType : cardTypes) {
			List<CardList> findByCardColorAndCardTypeForConnections = cardListRepository.findByCardColorAndCardType(cardColor, cardType);
			for (CardList findByCardColorAndCardTypeForConnection : findByCardColorAndCardTypeForConnections) {
				findByCardColorAndCardType.add(findByCardColorAndCardTypeForConnection);
			}
		}
		return findByCardColorAndCardType;
	}

}
