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

import jp.co.sss.onepiececardviewer.DTO.CardListSearchCriteria;
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
	
	// カードリスト画面へ遷移
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
	
	// 画像ファイルを返すエンドファイル
	@GetMapping("/file/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getImageFile(@PathVariable Integer id) {
		// ID検索
		Optional<CardList> getCardListById = cardListService.getCardListById(id);
		CardList image = getCardListById.get();
			
		return imagePathService.loadImageAsResponse(image.getImageFilePath());
	}
	
	// 動的な複数の列による条件検索
	@PostMapping("/search")
	public String searchCards(HttpSession session, CardListForm form, Model model) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		CardListSearchCriteria criteria = new CardListSearchCriteria();
		criteria.setCardName(form.getCardName());
		criteria.setCardColor(form.getCardColor());
		criteria.setCardType(form.getCardType());
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
		criteria.setCardOpponentAttack(form.isCardOpponentAttack());
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
		
		model.addAttribute("images", cardListService.cardListSearch(criteria));
		model.addAttribute("cardListForm", form);
		return "html/cardList";
	}
	
	// カード詳細の取得
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
