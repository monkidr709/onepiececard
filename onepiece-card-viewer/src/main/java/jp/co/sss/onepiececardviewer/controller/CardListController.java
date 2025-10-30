package jp.co.sss.onepiececardviewer.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

@Controller
@RequestMapping("/card/list")
public class CardListController {
	
	@Autowired
	private CardListService cardListService;
	
	//カードリスト画面へ遷移
	@GetMapping
	public String cardList(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		//セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		model.addAttribute("images", cardListService.getAllCardList());
		model.addAttribute("cardListForm", new CardListForm());
		return "html/cardList";
	}
	
	//画像ファイルを返すエンドファイル
	@GetMapping("/file/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getImageFile(@PathVariable Integer id) {
		try {
			//ID検索
			Optional<CardList> getCardListById = cardListService.getCardListById(id);
			CardList image = getCardListById.get();
			
			//IDに対応するimageFilePathをファイルパスに変換し、読み込む
			Path filePath = Paths.get(image.getImageFilePath());
			Resource resource = new UrlResource(filePath.toUri());
			
			//ファイルの存在と読み込み権限の確認
			if (!resource.exists() || !resource.isReadable()) {
				throw new RuntimeException("ファイルが読み込めません");
			}
			
			//ファイルのContent-Typeを判定
			String contentType = Files.probeContentType(filePath);
			if (contentType == null) {
				contentType = "application/octet-stream";
			}
			
			return ResponseEntity.ok()
								 .contentType(MediaType.parseMediaType(contentType))
								 .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
								 .body(resource);
		} catch (IOException e) {
			throw new RuntimeException("ファイルの読み込みエラー", e);
		}
	}
	
	//動的な複数の列による条件検索
	@PostMapping("/search")
	public String searchCards(HttpSession session, CardListForm form, Model model) {
		String username = (String) session.getAttribute("username");
		//セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		//全件検索
		if (form == null) {
			return "redirect:/card/list";
		}
		
		List<CardListSearchCriteria> criteriaList = new ArrayList<>();
		
		//カードの名前
		if (form.getCardName() != null && !form.getCardName().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardName", "LIKE", form.getCardName()));
		}
		
		//カードの色
		if (form.getCardColor() != null && !form.getCardColor().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardColor", "LIKE", form.getCardColor()));
		}
		
		//カードの種類
		if (form.getCardType() != null && !form.getCardType().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardType", "=", form.getCardType()));
		}
		
		//カードの収録されているパック
		if (form.getCardPack() != null && !form.getCardPack().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardPack", "=", form.getCardPack()));
		}
		
		//カードのブロックアイコン
		if (form.getCardBlockIcon() != null && !form.getCardBlockIcon().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardBlockIcon", "=", form.getCardBlockIcon()));
		}
		
		//カードのレアリティ
		if (form.getCardRarity() != null && !form.getCardRarity().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardRarity", "=", form.getCardRarity()));
		}
		
		//カードのコストまたはライフ
		if (form.getCardCostOrLife() != null && !form.getCardCostOrLife().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardCostOrLife", "=", form.getCardCostOrLife()));
		}
		
		//カードのパワー
		if (form.getCardPowerMore() != null && !form.getCardPowerMore().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardPower", ">", form.getCardPowerMore()));
		}
		if (form.getCardPowerUnder() != null && !form.getCardPowerUnder().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardPower", "<", form.getCardPowerUnder()));
		}
		
		//カードの属性
		if (form.getCardFeatures() != null && !form.getCardFeatures().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardFeatures", "LIKE", form.getCardFeatures()));
		}
		
		//カードの特徴
		if (form.getCardAttribute() != null && !form.getCardAttribute().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardAttribute", "LIKE", form.getCardAttribute()));
		}
		
		//カードのカウンター値
		if (form.getCardCounter() != null && !form.getCardCounter().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardCounter", "=", form.getCardCounter()));
		}
		
		//カードのカウンター値
		if (form.getCardText() != null && !form.getCardText().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardText", "LIKE", form.getCardText()));
		}
		
		//カードのトリガー
		if (form.isCardTrigger() == true) {
			criteriaList.add(new CardListSearchCriteria("cardTrigger", "=", form.isCardTrigger()));
		}
		if (form.getCardTriggerText() != null && !form.getCardTriggerText().isEmpty()) {
			criteriaList.add(new CardListSearchCriteria("cardTriggerText", "LIKE", form.getCardTriggerText()));
		}
		
		//カードの登場時効果
		if (form.isCardAppearance() == true) {
			criteriaList.add(new CardListSearchCriteria("cardAppearance", "=", form.isCardAppearance()));
		}
		
		//カードの起動メイン効果
		if (form.isCardLaunchMain() == true) {
			criteriaList.add(new CardListSearchCriteria("cardLaunchMain", "=", form.isCardLaunchMain()));
		}
		
		//カードのアタック時効果
		if (form.isCardAttack() == true) {
			criteriaList.add(new CardListSearchCriteria("cardAttack", "=", form.isCardAttack()));
		}
		
		//カードのKO時効果
		if (form.isCardKO() == true) {
			criteriaList.add(new CardListSearchCriteria("cardKO", "=", form.isCardKO()));
		}
		
		//カードのブロック時効果
		if (form.isCardBlock() == true) {
			criteriaList.add(new CardListSearchCriteria("cardBlock", "=", form.isCardBlock()));
		}
		
		//カードの自分のターン中効果
		if (form.isCardDuringYourTurn() == true) {
			criteriaList.add(new CardListSearchCriteria("cardDuringYourTurn", "=", form.isCardDuringYourTurn()));
		}
		
		//カードの相手のターン中効果
		if (form.isCardDuringOpponentTurn() == true) {
			criteriaList.add(new CardListSearchCriteria("cardDuringOpponentTurn", "=", form.isCardDuringOpponentTurn()));
		}
		
		//カードの自分のターン終了時
		if (form.isCardYourTurnEnd() == true) {
			criteriaList.add(new CardListSearchCriteria("cardYourTurnEnd", "=", form.isCardYourTurnEnd()));
		}
		
		//カードの相手のアタック時
		if (form.isCardOpponentAttack() == true) {
			criteriaList.add(new CardListSearchCriteria("cardOpponentAttack", "=", form.isCardOpponentAttack()));
		}
		
		//カードのメイン効果
		if (form.isCardMain() == true) {
			criteriaList.add(new CardListSearchCriteria("cardMain", "=", form.isCardMain()));
		}
		
		//カードのイベントカウンター効果
		if (form.isCardEventCounter() == true) {
			criteriaList.add(new CardListSearchCriteria("cardEventCounter", "=", form.isCardEventCounter()));
		}
		
		//カードのターン1回
		if (form.isCardOneTurn() == true) {
			criteriaList.add(new CardListSearchCriteria("cardOneTurn", "=", form.isCardOneTurn()));
		}
		
		//カードのドン!!×n
		if (form.isCardDonHang() == true) {
			criteriaList.add(new CardListSearchCriteria("cardDonHang", "=", form.isCardDonHang()));
		}
		
		//カードのドン!!-n
		if (form.isCardDonMinus() == true) {
			criteriaList.add(new CardListSearchCriteria("cardDonMinus", "=", form.isCardDonMinus()));
		}
		
		//カードのブロッカー
		if (form.isCardBlocker() == true) {
			criteriaList.add(new CardListSearchCriteria("cardBlocker", "=", form.isCardBlocker()));
		}
		
		//カードの速攻
		if (form.isCardHaste() == true) {
			criteriaList.add(new CardListSearchCriteria("cardHaste", "=", form.isCardHaste()));
		}
		
		//カードのダブルアタック
		if (form.isCardDoubleAttack() == true) {
			criteriaList.add(new CardListSearchCriteria("cardDoubleAttack", "=", form.isCardDoubleAttack()));
		}
		
		//カードのバニッシュ
		if (form.isCardVanish() == true) {
			criteriaList.add(new CardListSearchCriteria("cardVanish", "=", form.isCardVanish()));
		}
		
		List<CardList> cardListGroup = cardListService.cardListSearch(criteriaList);
		
		model.addAttribute("images", cardListGroup);
		model.addAttribute("cardListForm", form);
		return "html/cardList";
	}
	
	//モーダル表示
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
