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
	
	//cardTypeがリーダーであるものの抽出
	public List<CardList> getCardListByCardTypeLeader() {
		return cardListRepository.findByCardType("リーダー");
	}
	
	//cardTypeとcardColorでの検索
	public List<CardList> getCardListByCardColorAndCardType(String color) {
		String[] cardTypes = {"キャラ", "イベント", "ステージ"};
		List<CardList> cardListForCreateDeck = new ArrayList<>();
		for (String cardType : cardTypes) {
			List<CardList> cardListByCardTypes = cardListRepository.findByCardColorAndCardType(color, cardType);
			for (CardList cardListByCardType : cardListByCardTypes) {
				cardListForCreateDeck.add(cardListByCardType);
			}
		}
		
		return cardListForCreateDeck;
	}

}
