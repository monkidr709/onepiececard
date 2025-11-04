package jp.co.sss.onepiececardviewer.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
		
//		Optional<CardList> getCardListById = cardListService.getCardListById(id);
//		CardList cardListById = getCardListById.get();
//		String[] colors = {"赤", "緑", "青", "紫", "黒", "黄"};
//		List<CardList> cardListForCreateDeck = new ArrayList<>();
//		for (String color : colors) {
//			if (cardListById.getCardColor().contains(color)) {
//				List<CardList> getCardListByCardColorAndCardTypes = createDeckService.getCardListByCardColorAndCardType(color);
//				for (CardList getCardListByCardColorAndCardType : getCardListByCardColorAndCardTypes) {
//					cardListForCreateDeck.add(getCardListByCardColorAndCardType);
//				}
//			}
//		}
//		
//		model.addAttribute("cardListForCreateDeck", cardListForCreateDeck);
		return "html/createDeck";
	}

}
