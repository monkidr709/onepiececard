package jp.co.sss.onepiececardviewer.enums;

import java.util.Arrays;

public enum CardPack {
	ST01("ST01", "ST01:スタートデッキ 麦わらの一味"),
	ST02("ST02", "ST02:スタートデッキ 最悪の世代"),
	ST03("ST03", "ST03:スタートデッキ 王下七武海"),
	ST04("ST04", "ST04:スタートデッキ 百獣海賊団"),
	ST05("ST05", "ST06:スタートデッキ ONE PIECE FILM edition"),
	ST06("ST06", "ST06:スタートデッキ 海軍"),
	ST07("ST07", "ST07:スタートデッキ ビッグ・マム海賊団"),
	OP01("OP01", "OP01:ブースターパック ROMANCE DAWN"),
	OP02("OP02", "OP02:ブースターパック 頂上決戦"),
	OP03("OP03", "OP03:ブースターパック 強大な敵"),
	OP04("OP04", "OP04:ブースターパック 謀略の王国");
	
	private final String dbValue;
	private final String displayName;
	
	CardPack(String dbValue, String displayName) {
		this.dbValue = dbValue;
		this.displayName = displayName;
	}
	
	public String getDbValue() {
		return dbValue;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	// DBの値からEnumを取得
	public static CardPack fromDbValue(String value) {
		return Arrays.stream(values())
				.filter(c -> c.dbValue.equals(value))
				.findFirst()
				.orElse(null);
	}
}
