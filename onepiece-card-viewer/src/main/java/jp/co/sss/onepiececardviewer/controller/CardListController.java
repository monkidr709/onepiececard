package jp.co.sss.onepiececardviewer.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sss.onepiececardviewer.entity.CardList;
import jp.co.sss.onepiececardviewer.form.CardListForm;
import jp.co.sss.onepiececardviewer.service.CardListService;
import jp.co.sss.onepiececardviewer.service.ImagePathService;

@Controller
@RequestMapping("/card/list")
public class CardListController {
	
	@Autowired
	private CardListService cardListService;
	
	@Autowired
	private ImagePathService imagePathService;
	
	/**
	 * カードリスト表示
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping
	public String cardList(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("images", cardListService.getAllCardList());
		model.addAttribute("cardListForm", new CardListForm());
		return "html/cardList";
	}
	
	/**
	 * 画像ファイルの取得
	 * @param id
	 * @return　画像ファイルをレスポンスとして返す
	 */
	@GetMapping("/file/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getImageFile(@PathVariable Integer id) {
		// ID検索
		Optional<CardList> getCardListById = cardListService.getCardListById(id);
		CardList image = getCardListById.get();
		
		return imagePathService.loadImageAsResponse(image.getImageFilePath());
	}
	
	/**
	 * 動的な複数の列による条件検索
	 * @param session
	 * @param form
	 * @param model
	 * @return
	 */
	@PostMapping("/search")
	public String searchCards(HttpSession session, CardListForm form, Model model) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("images", cardListService.cardListSearch(form, new CardList()));
		model.addAttribute("cardListForm", form);
		return "html/cardList";
	}
	
	/**
	 * カード詳細の取得
	 * @param id
	 * @return データが存在するならば、カードデータを返す
	 */
	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseEntity<CardList> CardDatail(@PathVariable Integer id) {
		Optional<CardList> cardListOptional = cardListService.getCardListById(id);
		
		if (cardListOptional.isPresent()) {
			return ResponseEntity.ok(cardListOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
