package jp.co.sss.onepiececardviewer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.sss.onepiececardviewer.entity.CardList;
import jp.co.sss.onepiececardviewer.form.CardListForm;
import jp.co.sss.onepiececardviewer.service.CardListService;

@Controller
public class CreateDeckController {
	
	@Autowired
	private CardListService cardListService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * デッキ作成画面表示
	 * @param session
	 * @param model
	 * @param id
	 * @param deckData
	 * @return
	 */
	@RequestMapping(value = "/create/deck/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String createDeck(HttpSession session, Model model, @PathVariable Integer id, 
							 @RequestParam(required = false) String deckData) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		// デッキデータをセッションに保存
		if (deckData != null && !deckData.isEmpty()) {
			try {
				List<Map<String, Object>> deckCards = objectMapper.readValue(
					deckData, 
					new TypeReference<List<Map<String, Object>>>(){}
				);
				model.addAttribute("deckCards", deckCards);
			} catch (Exception e) {
				System.err.println("デッキデータのパースに失敗しました: " + e.getMessage());
			}
		}
		
		Optional<CardList> getCardListById = cardListService.getCardListById(id);
		CardList leaderCard = getCardListById.orElse(null);
		
		model.addAttribute("cardListForm", new CardListForm());
		model.addAttribute("leaderCard", leaderCard);
		model.addAttribute("cardListForCreateDecks", cardListService.cardListSearch(new CardListForm(), leaderCard));
		return "html/createDeck";
	}
	
	/**
	 * デッキ作成画面でのカード検索
	 * @param session
	 * @param form
	 * @param model
	 * @param id
	 * @param deckData
	 * @return
	 */
	@PostMapping("/create/deck/search/{id}")
	public String searchCardListForCreatedDeck(HttpSession session, CardListForm form, 
											   Model model, @PathVariable Integer id, 
											   @RequestParam(required = false) String deckData) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		// デッキデータをセッションに保存
		if (deckData != null && !deckData.isEmpty()) {
			try {
				List<Map<String, Object>> deckCards = objectMapper.readValue(
					deckData, 
					new TypeReference<List<Map<String, Object>>>(){}
				);
				session.setAttribute("deckCards_" + id, deckCards);
			} catch (Exception e) {
				System.err.println("デッキデータのパースに失敗しました: " + e.getMessage());
			}
		}
		
		Optional<CardList> getCardListById = cardListService.getCardListById(id);
		CardList leaderCard = getCardListById.orElse(null);
		
		// デッキ情報をセッションから取得
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> deckCards = (List<Map<String, Object>>) session.getAttribute("deckCards_" + id);
		if (deckCards == null) {
			deckCards = new ArrayList<>();
		}
		
		model.addAttribute("cardListForm", form);
		model.addAttribute("leaderCard", leaderCard);
		model.addAttribute("cardListForCreateDecks", cardListService.cardListSearch(form, leaderCard));
		model.addAttribute("deckCards", deckCards);
		return "html/createDeck";
	}

}
