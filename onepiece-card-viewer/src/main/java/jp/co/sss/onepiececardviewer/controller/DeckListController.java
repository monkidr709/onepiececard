package jp.co.sss.onepiececardviewer.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.onepiececardviewer.service.DeckListService;

@Controller
public class DeckListController {
	
	@Autowired
	DeckListService deckListService;
	
	@GetMapping("/deck/list")
	public String deckList(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		//セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("deckLists", deckListService.getAllDeckList());
		return "html/deckList";
	}

}
