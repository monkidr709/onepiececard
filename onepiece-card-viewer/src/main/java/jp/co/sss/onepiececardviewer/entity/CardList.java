package jp.co.sss.onepiececardviewer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jp.co.sss.onepiececardviewer.converter.CardCounterConverter;
import jp.co.sss.onepiececardviewer.converter.CardPackConverter;
import jp.co.sss.onepiececardviewer.enums.CardCounter;
import jp.co.sss.onepiececardviewer.enums.CardPack;

@Entity
@Table(name = "cards")
public class CardList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (name = "card_number")
	private String cardNumber;
	
	@Column (name = "card_name")
	private String cardName;
	
	@Column (name = "image_file_path")
	private String imageFilePath;
	
	@Column (name = "card_color")
	private String cardColor;
	
	@Column (name = "card_type")
	private String cardType;
	
	@Convert(converter = CardPackConverter.class)
	@Column (name = "card_pack")
	private CardPack cardPack;
	
	@Column (name = "card_block_icon")
	private Integer cardBlockIcon;
	
	@Column (name = "card_rarity")
	private String cardRarity;
	
	@Column (name = "card_cost_or_life")
	private Integer cardCostOrLife;
	
	@Column (name = "card_power")
	private Integer cardPower;
	
	@Column (name = "card_features")
	private String cardFeatures;
	
	@Column (name = "card_attribute_1")
	private String cardAttribute1;
	
	@Column (name = "card_attribute_2")
	private String cardAttribute2;
	
	@Column (name = "card_attribute_3")
	private String cardAttribute3;
	
	@Column (name = "card_attribute_4")
	private String cardAttribute4;
	
	@Column (name = "card_attribute_5")
	private String cardAttribute5;
	
	@Convert(converter = CardCounterConverter.class)
	@Column (name = "card_counter")
	private CardCounter cardCounter;
	
	@Column (name = "card_text")
	private String cardText;
	
	@Column (name = "card_trigger")
	private boolean cardTrigger;
	
	@Column (name = "card_trigger_text")
	private String cardTriggerText;
	
	@Column (name = "card_appearance")
	private boolean cardAppearance;
	
	@Column (name = "card_launch_main")
	private boolean cardLaunchMain;
	
	@Column (name = "card_attack")
	private boolean cardAttack;
	
	@Column (name = "card_KO")
	private boolean cardKO;
	
	@Column (name = "card_block")
	private boolean cardBlock;
	
	@Column (name = "card_during_your_turn")
	private boolean cardDuringYourTurn;
	
	@Column (name = "card_during_opponent_turn")
	private boolean cardDuringOpponentTurn;
	
	@Column (name = "card_opponent_attack")
	private boolean cardOpponentAttack;
	
	@Column (name = "card_your_turn_end")
	private boolean cardYourTurnEnd;
	
	@Column (name = "card_main")
	private boolean cardMain;
	
	@Column (name = "card_event_counter")
	private boolean cardEventCounter;
	
	@Column (name = "card_one_turn")
	private boolean cardOneTurn;
	
	@Column (name ="card_don_hang")
	private boolean cardDonHang;
	
	@Column (name = "card_don_use")
	private boolean cardDonUse;
	
	@Column (name = "card_don_minus")
	private boolean cardDonMinus;
	
	@Column (name = "card_blocker")
	private boolean cardBlocker;
	
	@Column (name = "card_haste")
	private boolean cardHaste;
	
	@Column (name = "card_double_attack")
	private boolean cardDoubleAttack;
	
	@Column (name = "card_vanish")
	private boolean cardVanish;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getCardColor() {
		return cardColor;
	}

	public void setCardColor(String cardColor) {
		this.cardColor = cardColor;
	}
	
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public CardPack getCardPack() {
		return cardPack;
	}

	public void setCardPack(CardPack cardPack) {
		this.cardPack = cardPack;
	}

	public Integer getCardBlockIcon() {
		return cardBlockIcon;
	}

	public void setCardBlockIcon(Integer cardBlockIcon) {
		this.cardBlockIcon = cardBlockIcon;
	}

	public String getCardRarity() {
		return cardRarity;
	}

	public void setCardRarity(String cardRarity) {
		this.cardRarity = cardRarity;
	}

	public Integer getCardCostOrLife() {
		return cardCostOrLife;
	}

	public void setCardCostOrLife(Integer cardCostOrLife) {
		this.cardCostOrLife = cardCostOrLife;
	}

	public Integer getCardPower() {
		return cardPower;
	}

	public void setCardPower(Integer cardPower) {
		this.cardPower = cardPower;
	}

	public String getCardFeatures() {
		return cardFeatures;
	}

	public void setCardFeatures(String cardFeatures) {
		this.cardFeatures = cardFeatures;
	}

	public String getCardAttribute1() {
		return cardAttribute1;
	}

	public void setCardAttribute1(String cardAttribute1) {
		this.cardAttribute1 = cardAttribute1;
	}

	public String getCardAttribute2() {
		return cardAttribute2;
	}

	public void setCardAttribute2(String cardAttribute2) {
		this.cardAttribute2 = cardAttribute2;
	}

	public String getCardAttribute3() {
		return cardAttribute3;
	}

	public void setCardAttribute3(String cardAttribute3) {
		this.cardAttribute3 = cardAttribute3;
	}

	public String getCardAttribute4() {
		return cardAttribute4;
	}

	public void setCardAttribute4(String cardAttribute4) {
		this.cardAttribute4 = cardAttribute4;
	}

	public String getCardAttribute5() {
		return cardAttribute5;
	}

	public void setCardAttribute5(String cardAttribute5) {
		this.cardAttribute5 = cardAttribute5;
	}

	public CardCounter getCardCounter() {
		return cardCounter;
	}

	public void setCardCounter(CardCounter cardCounter) {
		this.cardCounter = cardCounter;
	}

	public String getCardText() {
		return cardText;
	}

	public void setCardText(String cardText) {
		this.cardText = cardText;
	}

	public boolean isCardTrigger() {
		return cardTrigger;
	}

	public void setCardTrigger(boolean cardTrigger) {
		this.cardTrigger = cardTrigger;
	}

	public String getCardTriggerText() {
		return cardTriggerText;
	}

	public void setCardTriggerText(String cardTriggerText) {
		this.cardTriggerText = cardTriggerText;
	}

	public boolean isCardAppearance() {
		return cardAppearance;
	}

	public void setCardAppearance(boolean cardAppearance) {
		this.cardAppearance = cardAppearance;
	}

	public boolean isCardLaunchMain() {
		return cardLaunchMain;
	}

	public void setCardLaunchMain(boolean cardLaunchMain) {
		this.cardLaunchMain = cardLaunchMain;
	}

	public boolean isCardAttack() {
		return cardAttack;
	}

	public void setCardAttack(boolean cardAttack) {
		this.cardAttack = cardAttack;
	}

	public boolean isCardKO() {
		return cardKO;
	}

	public void setCardKO(boolean cardKO) {
		this.cardKO = cardKO;
	}

	public boolean isCardBlock() {
		return cardBlock;
	}

	public void setCardBlock(boolean cardBlock) {
		this.cardBlock = cardBlock;
	}

	public boolean isCardDuringYourTurn() {
		return cardDuringYourTurn;
	}

	public void setCardDuringYourTurn(boolean cardDuringYourTurn) {
		this.cardDuringYourTurn = cardDuringYourTurn;
	}
	
	public boolean isCardDuringOpponentTurn() {
		return cardDuringOpponentTurn;
	}

	public void setCardDuringOpponentTurn(boolean cardDuringOpponentTurn) {
		this.cardDuringOpponentTurn = cardDuringOpponentTurn;
	}

	public boolean isCardOpponentAttack() {
		return cardOpponentAttack;
	}

	public void setCardOpponentAttack(boolean cardOpponentAttack) {
		this.cardOpponentAttack = cardOpponentAttack;
	}

	public boolean isCardYourTurnEnd() {
		return cardYourTurnEnd;
	}

	public void setCardYourTurnEnd(boolean cardYourTurnEnd) {
		this.cardYourTurnEnd = cardYourTurnEnd;
	}

	public boolean isCardMain() {
		return cardMain;
	}

	public void setCardMain(boolean cardMain) {
		this.cardMain = cardMain;
	}

	public boolean isCardEventCounter() {
		return cardEventCounter;
	}

	public void setCardEventCounter(boolean cardEventCounter) {
		this.cardEventCounter = cardEventCounter;
	}

	public boolean isCardOneTurn() {
		return cardOneTurn;
	}

	public void setCardOneTurn(boolean cardOneTurn) {
		this.cardOneTurn = cardOneTurn;
	}

	public boolean isCardDonHang() {
		return cardDonHang;
	}

	public void setCardDonHang(boolean cardDonHang) {
		this.cardDonHang = cardDonHang;
	}

	public boolean isCardDonUse() {
		return cardDonUse;
	}

	public void setCardDonUse(boolean cardDonUse) {
		this.cardDonUse = cardDonUse;
	}

	public boolean isCardDonMinus() {
		return cardDonMinus;
	}

	public void setCardDonMinus(boolean cardDonMinus) {
		this.cardDonMinus = cardDonMinus;
	}

	public boolean isCardBlocker() {
		return cardBlocker;
	}

	public void setCardBlocker(boolean cardBlocker) {
		this.cardBlocker = cardBlocker;
	}

	public boolean isCardHaste() {
		return cardHaste;
	}

	public void setCardHaste(boolean cardHaste) {
		this.cardHaste = cardHaste;
	}

	public boolean isCardDoubleAttack() {
		return cardDoubleAttack;
	}

	public void setCardDoubleAttack(boolean cardDoubleAttack) {
		this.cardDoubleAttack = cardDoubleAttack;
	}

	public boolean isCardVanish() {
		return cardVanish;
	}

	public void setCardVanish(boolean cardVanish) {
		this.cardVanish = cardVanish;
	}

}
