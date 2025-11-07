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
	
	//リーダーの色を返す
	public List<String> getLeaderCardColor(CardList leaderCard) {
		String[] colors = {"赤", "緑", "青", "紫", "黒", "黄"};
		List<String> leaderCardColors = new ArrayList<>();
		for (String color : colors) {
			if (leaderCard.getCardColor().contains(color)) {
				leaderCardColors.add(color);
			}
		}
		
		return leaderCardColors;
	}

}
