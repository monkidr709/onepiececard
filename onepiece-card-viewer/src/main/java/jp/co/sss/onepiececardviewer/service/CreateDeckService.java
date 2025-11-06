package jp.co.sss.onepiececardviewer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.CardList;
import jp.co.sss.onepiececardviewer.repository.CardListRepository;

@Service
public class CreateDeckService {
	
	@Autowired
	private CardListRepository cardListRepository;
	
	@Autowired
	private CardListService cardListService;
	
	//cardTypeがリーダーであるものの抽出
	public List<CardList> getCardListByCardTypeLeader() {
		return cardListRepository.findByCardType("リーダー");
	}
	
	//cardColorでの検索
	public List<CardList> getCardListByCardColor(Integer id) {
		Optional<CardList> getCardListById = cardListService.getCardListById(id);
		CardList cardListById = getCardListById.get();
		String[] colors = {"赤", "緑", "青", "紫", "黒", "黄"};
		List<CardList> cardListForCreateDeck = new ArrayList<>();
		for (String color : colors) {
			if (cardListById.getCardColor().contains(color)) {
				List<CardList> getCardListByCardColorAndCardTypes = getCardListByCardColorAndCardType(color);
				for (CardList getCardListByCardColorAndCardType : getCardListByCardColorAndCardTypes) {
					cardListForCreateDeck.add(getCardListByCardColorAndCardType);
				}
			}
		}
		
		return cardListForCreateDeck;
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
	
	//動的な複数の列による条件検索
//	public List<CardList> cardListSearch (List<CardListSearchCriteria> criteriaList) {
//		if (criteriaList == null || criteriaList.isEmpty()) {
//			return cardListRepository.findAll(Sort.by("id").ascending());
//		}
//		
//		Specification<CardList> spec = null;
//		
//		for (CardListSearchCriteria criteria : criteriaList) {
//			Specification<CardList> newSpec = new CardListGenericSpecification<>(criteria);
//			spec = (spec == null) ? newSpec : spec.and(newSpec);
//		}
//		
//		return cardListRepository.findAll(spec, Sort.by("id").ascending());
//	}

}
