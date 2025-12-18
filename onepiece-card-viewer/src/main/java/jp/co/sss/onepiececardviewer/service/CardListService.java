package jp.co.sss.onepiececardviewer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.DTO.CardListSearchCriteria;
import jp.co.sss.onepiececardviewer.entity.CardList;
import jp.co.sss.onepiececardviewer.form.CardListForm;
import jp.co.sss.onepiececardviewer.repository.CardListRepository;
import jp.co.sss.onepiececardviewer.specification.CardListGenericSpecification;

@Service
public class CardListService {
	
	@Autowired
	private CardListRepository cardListRepository;
	
	/**
	 * すべてのカードリストを取得 (Id昇順)
	 * @return
	 */
	public List<CardList> getAllCardList(){
		return cardListRepository.findAllByOrderByIdAsc();
	}
	
	/**
	 * cardTypeがリーダーであるものを取得
	 * @return
	 */
	public List<CardList> getCardListByCardTypeLeader() {
		return cardListRepository.findByCardTypeOrderByIdAsc("リーダー");
	}
	
	/**
	 * IDでカードリストを取得
	 * @param id
	 * @return
	 */
	public Optional<CardList> getCardListById(Integer id){
		return cardListRepository.findById(id);
	}
	
	/**
	 * 動的な複数の列による条件検索
	 * @param form
	 * @param leaderCard
	 * @return
	 */
	public List<CardList> cardListSearch(CardListForm form, CardList leaderCard) {
		CardListSearchCriteria criteria = mapFormToCriteria(form, leaderCard);
		Specification<CardList> spec = CardListGenericSpecification.buildSpecification(criteria);
		return cardListRepository.findAll(spec, Sort.by("id").ascending());
	}
	
	/**
	 * リーダーカードの色を返す
	 * @param leaderCard
	 * @return
	 */
	public List<String> getLeaderCardColor(CardList leaderCard) {
		String[] colors = {"赤", "緑", "青", "紫", "黒", "黄"};
		List<String> leaderCardColors = new ArrayList<>();
		for (String color : colors) {
			if (color.equals(leaderCard.getCardColor())) {
				leaderCardColors.add(color);
			}
			if (color.equals(leaderCard.getCardColor2())) {
				leaderCardColors.add(color);
			}
		}
		
		return leaderCardColors;
	}
	
	/**
	 * フォームを検索条件にマッピング
	 * @param form
	 * @param leaderCard
	 * @return
	 */
	private CardListSearchCriteria mapFormToCriteria(CardListForm form, CardList leaderCard) {
		CardListSearchCriteria criteria = new CardListSearchCriteria();
		
		criteria.setCardName(form.getCardName());
		if (leaderCard.getId() != null ) {
			if (form.getCardColor() == null || form.getCardColor().isEmpty()) {
				criteria.setCardColor(getLeaderCardColor(leaderCard));
			} else {
				criteria.setCardColor(form.getCardColor());
			}
		} else {
			criteria.setCardColor(form.getCardColor());
		}
		if (leaderCard.getId() != null) {
			if (form.getCardType() == null || form.getCardType().isEmpty()) {
				List<String> cardTypeForCreateDeck = Arrays.asList("キャラ", "イベント", "ステージ");
				criteria.setCardType(cardTypeForCreateDeck);
			} else {
				criteria.setCardType(form.getCardType());
			}
		} else {
			criteria.setCardType(form.getCardType());
		}
		criteria.setCardPack(form.getCardPack());
		criteria.setMinCardBlockIcon(form.getMinCardBlockIcon());
		criteria.setMaxCardBlockIcon(form.getMaxCardBlockIcon());
		criteria.setCardRarity(form.getCardRarity());
		criteria.setMinCardCostOrLife(form.getMinCardCostOrLife());
		criteria.setMaxCardCostOrLife(form.getMaxCardCostOrLife());
		criteria.setMinCardPower(form.getMinCardPower());
		criteria.setMaxCardPower(form.getMaxCardPower());
		criteria.setCardFeatures(form.getCardFeatures());
		criteria.setCardAttribute(form.getCardAttribute());
		criteria.setCardCounter(form.getCardCounter());
		criteria.setCardText(form.getCardText());
		criteria.setCardTrigger(form.isCardTrigger());
		criteria.setCardTriggerText(form.getCardTriggerText());
		criteria.setCardAppearance(form.isCardAppearance());
		criteria.setCardLaunchMain(form.isCardLaunchMain());
		criteria.setCardAttack(form.isCardAttack());
		criteria.setCardKO(form.isCardKO());
		criteria.setCardBlock(form.isCardBlock());
		criteria.setCardDuringYourTurn(form.isCardDuringYourTurn());
		criteria.setCardDuringOpponentTurn(form.isCardDuringOpponentTurn());
		criteria.setCardYourTurnEnd(form.isCardYourTurnEnd());
		criteria.setCardMain(form.isCardMain());
		criteria.setCardEventCounter(form.isCardEventCounter());
		criteria.setCardOneTurn(form.isCardOneTurn());
		criteria.setCardDonHang(form.isCardDonHang());
		criteria.setCardDonUse(form.isCardDonUse());
		criteria.setCardDonMinus(form.isCardDonMinus());
		criteria.setCardBlocker(form.isCardBlocker());
		criteria.setCardHaste(form.isCardHaste());
		criteria.setCardDoubleAttack(form.isCardDoubleAttack());
		criteria.setCardVanish(form.isCardVanish());
		
		return criteria;
	}

}
