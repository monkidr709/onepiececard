package jp.co.sss.onepiececardviewer.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.sss.onepiececardviewer.service.DeckListService;

@Controller
public class DeckListController {
	
	@Autowired
	DeckListService deckListService;
	
	@GetMapping("/deck/list")
	public String deckList(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		Integer userId = (Integer) session.getAttribute("userId");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("deckLists", deckListService.getDeckList(userId));
		return "html/deckList";
	}
	
	@PostMapping("/change/deck/{id}")
	public String changeDeck(HttpSession session, Model model, @PathVariable Integer id, 
							 @RequestParam(required = false) String deckData, 
							 @RequestParam(required = false) Integer deckId, 
							 RedirectAttributes redirectAttributes) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		// deckDataをリダイレクト先に渡す
		if (deckData != null && !deckData.isEmpty()) {
			redirectAttributes.addAttribute("deckData", deckData);
		}
		
		session.setAttribute("changeDeck", true);
		session.setAttribute("deckId", deckId);
		return "redirect:/create/deck/" + id;
	}
	
	@GetMapping("/deleted/deck/{id}")
	public String deletedDeck(HttpSession session, @PathVariable Integer id) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		// デッキ削除
		deckListService.deletedDeck(id);
		
		return "redirect:/deck/list";
	}

}
