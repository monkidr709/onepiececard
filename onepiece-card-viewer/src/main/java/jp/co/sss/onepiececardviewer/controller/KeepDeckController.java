package jp.co.sss.onepiececardviewer.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.sss.onepiececardviewer.entity.CardList;
import jp.co.sss.onepiececardviewer.form.KeepDeckForm;
import jp.co.sss.onepiececardviewer.service.CardListService;
import jp.co.sss.onepiececardviewer.service.KeepDeckService;

@Controller
public class KeepDeckController {
	
	@Autowired
	KeepDeckService keepDeckService;
	
	@Autowired
	CardListService cardListService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostMapping("/confirm/deck/{id}")
	public String confirmDeck(HttpSession session, Model model, @PathVariable Integer id, @RequestParam(required = false) String deckData) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		// セッションに保存したデッキデータを削除
		if (session != null && session.getAttribute("deckCards_" + id) != null) {
			session.removeAttribute("deckCards_" + id);
		}
		
		// デッキデータを保存
		try {
			List<Map<String, Object>> deckCards = objectMapper.readValue(
					deckData, 
					new TypeReference<List<Map<String, Object>>>(){}
			);
			
			model.addAttribute("deckCards", deckCards);
		} catch (Exception e) {
			System.err.println("デッキデータのパースに失敗しました: " + e.getMessage());
		}
		
		Optional<CardList> getCardListById = cardListService.getCardListById(id);
		CardList leaderCard = getCardListById.orElse(null);
		
		model.addAttribute("leaderCard", leaderCard);
		model.addAttribute("keepDeckForm", new KeepDeckForm());
		return "html/keepDeck";
	}
	
	@PostMapping("/keep/deck/{id}")
	public String keepDeck(HttpSession session, KeepDeckForm form, @PathVariable Integer id, @RequestParam(required = false) String deckData, RedirectAttributes redirectAttributes) {
		String username = (String) session.getAttribute("username");
		Integer userId = (Integer) session.getAttribute("userId");
		//セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		// デッキデータを登録
		try {
			List<Map<String, Object>> deckCards = objectMapper.readValue(
					deckData, 
					new TypeReference<List<Map<String, Object>>>(){}
			);
			
			List<Integer> deckCardId = deckCards.stream()
					.map(m -> Integer.valueOf(m.get("id").toString()))
					.collect(Collectors.toList());
			
			keepDeckService.keepDeck(userId, form.getDeckName(), form.isPublishDeck(), id, deckCardId);
		} catch (Exception e) {
			System.err.println("デッキデータの登録に失敗しました: " + e.getMessage());
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "デッキの保存に失敗しました");
		}
		
		return "redirect:/deck/list";
	}

}
