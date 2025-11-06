package jp.co.sss.onepiececardviewer.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import jp.co.sss.onepiececardviewer.DTO.CardListSearchCriteria;
import jp.co.sss.onepiececardviewer.entity.CardList;

public class CardListGenericSpecification {
	
	public static Specification<CardList> buildSpecification(CardListSearchCriteria criteria) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			// 1. OR検索系(同じカラム内でOR)
			addOrConditions(root, criteriaBuilder, predicates, criteria);
			
			// 2. 範囲検索系(大小比較)
			addRangeConditions(root, criteriaBuilder, predicates, criteria);
			
			// 3. 完全一致系
			addEqualConditions(root, criteriaBuilder, predicates, criteria);
			
			// すべての条件をANDで結合して返す
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	
	}
	
	private static void addOrConditions(Root<CardList> root,
										CriteriaBuilder cb,
										List<Predicate> predicates,
										CardListSearchCriteria criteria) {
		//cardNameのOR検索
		if (criteria.getCardName() != null && !criteria.getCardName().isEmpty()) {
			List<Predicate> namePredicates = criteria.getCardName().stream()
					.map(name -> cb.like(root.get("cardName"), "%" + name + "%"))
					.collect(Collectors.toList());
			predicates.add(cb.or(namePredicates.toArray(new Predicate[0])));
		}
		
		//cardColorのOR検索
		if (criteria.getCardColor() != null && !criteria.getCardColor().isEmpty()) {
			List<Predicate> colorPredicates = criteria.getCardColor().stream()
					.map(color -> cb.like(root.get("cardColor"), "%" + color + "%"))
					.collect(Collectors.toList());
			predicates.add(cb.or(colorPredicates.toArray(new Predicate[0])));
		}
		
		//cardTypeのOR検索
		if (criteria.getCardType() != null && !criteria.getCardType().isEmpty()) {
			predicates.add(root.get("cardType").in(criteria.getCardType()));
		}
		
		//cardPackのOR検索
		if (criteria.getCardPack() != null && !criteria.getCardPack().isEmpty()) {
			predicates.add(root.get("cardPack").in(criteria.getCardPack()));
		}
		
		//cardRarityのOR検索
		if (criteria.getCardRarity() != null && !criteria.getCardRarity().isEmpty()) {
			predicates.add(root.get("cardRarity").in(criteria.getCardRarity()));
		}
		
		//cardFeaturesのOR検索
		if (criteria.getCardFeatures() != null && !criteria.getCardFeatures().isEmpty()) {
			predicates.add(root.get("cardFeatures").in(criteria.getCardFeatures()));
		}
		
		//cardAttributeのOR検索
		if (criteria.getCardAttribute() != null && !criteria.getCardAttribute().isEmpty()) {
			List<Predicate> attributePredicates = criteria.getCardAttribute().stream()
					.map(attribute -> cb.like(root.get("cardAttribute"), "%" + attribute + "%"))
					.collect(Collectors.toList());
			predicates.add(cb.or(attributePredicates.toArray(new Predicate[0])));
		}
		
		//cardCounterのOR検索
		if (criteria.getCardCounter() != null && !criteria.getCardCounter().isEmpty()) {
			predicates.add(root.get("cardCounter").in(criteria.getCardCounter()));
		}
		
		//cardTextのOR検索
		if (criteria.getCardText() != null && !criteria.getCardText().isEmpty()) {
			List<Predicate> textPredicates = criteria.getCardText().stream()
					.map(text -> cb.like(root.get("cardText"), "%" + text + "%"))
					.collect(Collectors.toList());
			predicates.add(cb.or(textPredicates.toArray(new Predicate[0])));
		}
		
		//cardTriggerTextのOR検索
		if (criteria.getCardTriggerText() != null && !criteria.getCardTriggerText().isEmpty()) {
			List<Predicate> triggerTextPredicates = criteria.getCardTriggerText().stream()
					.map(triggerText -> cb.like(root.get("cardTriggerText"), "%" + triggerText + "%"))
					.collect(Collectors.toList());
			predicates.add(cb.or(triggerTextPredicates.toArray(new Predicate[0])));
		}
	}
	
	private static void addRangeConditions(Root<CardList> root,
										   CriteriaBuilder cb,
										   List<Predicate> predicates,
										   CardListSearchCriteria criteria) {
		//cardBlockIconの範囲検索
		if (criteria.getMinCardBlockIcon() != null && !criteria.getMinCardBlockIcon().isEmpty()) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("cardBlockIcon"), criteria.getMinCardBlockIcon()));
		}
		if (criteria.getMaxCardBlockIcon() != null && !criteria.getMaxCardBlockIcon().isEmpty()) {
			predicates.add(cb.lessThanOrEqualTo(root.get("cardBlockIcon"), criteria.getMaxCardBlockIcon()));
		}
		
		//cardCostOrLifeの範囲検索
		if (criteria.getMinCardCostOrLife() != null && !criteria.getMinCardCostOrLife().isEmpty()) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("cardCostOrLife"), criteria.getMinCardCostOrLife()));
		}
		if (criteria.getMaxCardCostOrLife() != null && !criteria.getMaxCardCostOrLife().isEmpty()) {
			predicates.add(cb.lessThanOrEqualTo(root.get("cardCostOrLife"), criteria.getMaxCardCostOrLife()));
		}
		
		//cardPowerの範囲検索
		if (criteria.getMinCardPower() != null && !criteria.getMinCardPower().isEmpty()) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("cardPower"), criteria.getMinCardPower()));
		}
		if (criteria.getMaxCardPower() != null && !criteria.getMaxCardPower().isEmpty()) {
			predicates.add(cb.lessThanOrEqualTo(root.get("cardPower"), criteria.getMaxCardPower()));
		}
	}
	
	private static void addEqualConditions(Root<CardList> root,
										   CriteriaBuilder cb,
										   List<Predicate> predicates,
										   CardListSearchCriteria criteria) {
		//cardTriggerの完全一致
		if (criteria.isCardTrigger() == true) {
			predicates.add(cb.equal(root.get("cardTrigger"), criteria.isCardTrigger()));
		}
		
		//cardAppearanceの完全一致
		if (criteria.isCardAppearance() == true) {
			predicates.add(cb.equal(root.get("cardAppearance"), criteria.isCardAppearance()));
		}
		
		//cardLaunchMainの完全一致
		if (criteria.isCardLaunchMain() == true) {
			predicates.add(cb.equal(root.get("cardLaunchMain"), criteria.isCardLaunchMain()));
		}
		
		//cardAttackの完全一致
		if (criteria.isCardAttack() == true) {
			predicates.add(cb.equal(root.get("cardAttack"), criteria.isCardAttack()));
		}
		
		//cardKOの完全一致
		if (criteria.isCardKO() == true) {
			predicates.add(cb.equal(root.get("cardKO"), criteria.isCardKO()));
		}
		
		//cardBlockの完全一致
		if (criteria.isCardBlock() == true) {
			predicates.add(cb.equal(root.get("cardBlock"), criteria.isCardBlock()));
		}
		
		//cardDuringYourTurnの完全一致
		if (criteria.isCardDuringYourTurn() == true) {
			predicates.add(cb.equal(root.get("cardDuringYourTurn"), criteria.isCardDuringYourTurn()));
		}
		
		//cardDuringOpponentTurnの完全一致
		if (criteria.isCardDuringOpponentTurn() == true) {
			predicates.add(cb.equal(root.get("cardDuringOpponentTurn"), criteria.isCardDuringOpponentTurn()));
		}
		
		//cardOpponentAttackの完全一致
		if (criteria.isCardOpponentAttack() == true) {
			predicates.add(cb.equal(root.get("cardOpponentAttack"), criteria.isCardOpponentAttack()));
		}
		
		//cardYourTurnEndの完全一致
		if (criteria.isCardYourTurnEnd() == true) {
			predicates.add(cb.equal(root.get("cardYourTurnEnd"), criteria.isCardYourTurnEnd()));
		}
		
		//cardMainの完全一致
		if (criteria.isCardMain() == true) {
			predicates.add(cb.equal(root.get("cardMain"), criteria.isCardMain()));
		}
		
		//cardEventCounterの完全一致
		if (criteria.isCardEventCounter() == true) {
			predicates.add(cb.equal(root.get("cardEventCounter"), criteria.isCardEventCounter()));
		}
		
		//cardOneTurnの完全一致
		if (criteria.isCardOneTurn() == true) {
			predicates.add(cb.equal(root.get("cardOneTurn"), criteria.isCardOneTurn()));
		}
		
		//cardDonHangの完全一致
		if (criteria.isCardDonHang() == true) {
			predicates.add(cb.equal(root.get("cardDonHang"), criteria.isCardDonHang()));
		}
		
		//cardDonUseの完全一致
		if (criteria.isCardDonUse() == true) {
			predicates.add(cb.equal(root.get("cardDonUse"), criteria.isCardDonUse()));
		}
		
		//cardDonMinusの完全一致
		if (criteria.isCardDonMinus() == true) {
			predicates.add(cb.equal(root.get("cardDonMinus"), criteria.isCardDonMinus()));
		}
		
		//cardBlockerの完全一致
		if (criteria.isCardBlocker() == true) {
			predicates.add(cb.equal(root.get("cardBlocker"), criteria.isCardBlocker()));
		}
		
		//cardHasteの完全一致
		if (criteria.isCardHaste() == true) {
			predicates.add(cb.equal(root.get("cardHaste"), criteria.isCardHaste()));
		}
		
		//cardDoubleAttackの完全一致
		if (criteria.isCardDoubleAttack() == true) {
			predicates.add(cb.equal(root.get("cardDoubleAttack"), criteria.isCardDoubleAttack()));
		}
		
		//cardVanishの完全一致
		if (criteria.isCardVanish() == true) {
			predicates.add(cb.equal(root.get("cardVanish"), criteria.isCardVanish()));
		}
	}

}
