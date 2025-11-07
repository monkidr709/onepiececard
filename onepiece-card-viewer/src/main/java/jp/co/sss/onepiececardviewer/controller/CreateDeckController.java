package jp.co.sss.onepiececardviewer.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sss.onepiececardviewer.DTO.CardListSearchCriteria;
import jp.co.sss.onepiececardviewer.entity.CardList;
import jp.co.sss.onepiececardviewer.form.CardListForm;
import jp.co.sss.onepiececardviewer.service.CardListService;
import jp.co.sss.onepiececardviewer.service.CreateDeckService;

@Controller
public class CreateDeckController {
	
	@Autowired
	private CreateDeckService createDeckService;
	
	@Autowired
	private CardListService cardListService;
	
	@GetMapping("/create/deck/{id}")
	public String createDeck(HttpSession session, Model model, @PathVariable Integer id) {
		String username = (String) session.getAttribute("username");
		//セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		Optional<CardList> getCardListById = cardListService.getCardListById(id);
		CardList leaderCard = getCardListById.orElse(null);
		
		CardListSearchCriteria criteria = new CardListSearchCriteria();
		List<String> cardTypeForCreateDeck = Arrays.asList("キャラ", "イベント", "ステージ");
		criteria.setCardColor(createDeckService.getLeaderCardColor(leaderCard));
		criteria.setCardType(cardTypeForCreateDeck);
		
		model.addAttribute("cardListForm", new CardListForm());
		model.addAttribute("leaderCard", leaderCard);
		model.addAttribute("cardListForCreateDecks", cardListService.cardListSearch(criteria));
		return "html/createDeck";
	}
	
	@PostMapping("/create/deck/search/{id}")
	public String searchCardListForCreatedDeck(HttpSession session, CardListForm form, Model model, @PathVariable Integer id) {
		String username = (String) session.getAttribute("username");
		//セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		Optional<CardList> getCardListById = cardListService.getCardListById(id);
		CardList leaderCard = getCardListById.orElse(null);
		
		CardListSearchCriteria criteria = new CardListSearchCriteria();
		criteria.setCardName(form.getCardName());
		if (form.getCardColor().isEmpty()) {
			criteria.setCardColor(createDeckService.getLeaderCardColor(leaderCard));
		} else {
			criteria.setCardColor(form.getCardColor());
		}
		if (form.getCardType().isEmpty()) {
			List<String> cardTypeForCreateDeck = Arrays.asList("キャラ", "イベント", "ステージ");
			criteria.setCardType(cardTypeForCreateDeck);
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
		
		model.addAttribute("cardListForm", form);
		model.addAttribute("leaderCard", leaderCard);
		model.addAttribute("cardListForCreateDecks", cardListService.cardListSearch(criteria));
		return "html/createDeck";
	}

}
