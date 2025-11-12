package jp.co.sss.onepiececardviewer.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jp.co.sss.onepiececardviewer.form.CardListForm;
import jp.co.sss.onepiececardviewer.service.CreateDeckService;

@Controller
public class ChoiceLeaderController {
	
	@Autowired
	private CreateDeckService createDeckService;
	
	//リーダー選択画面へ遷移
	@GetMapping("/choice/leader")
	public String deckCreate(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		//セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		model.addAttribute("leaderImages", createDeckService.getCardListByCardTypeLeader());
		model.addAttribute("cardListForm", new CardListForm());
		return "html/choiceLeader";
	}
	
	//デッキ作成のキャンセル
	@GetMapping("/cancel/deck/creation/{id}")
	public String cancelDeckCreation(HttpSession session, @PathVariable Integer id) {
		if (session != null && session.getAttribute("deckCards_" + id) != null) {
			session.removeAttribute("deckCards_" + id);
		}
		
		return "redirect:/choice/leader";
	}

}
