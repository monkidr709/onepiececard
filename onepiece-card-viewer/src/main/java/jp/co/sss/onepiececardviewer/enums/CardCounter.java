package jp.co.sss.onepiececardviewer.enums;

import java.util.Arrays;

public enum CardCounter {
	Zero("0", "なし"),
	One("1000", "1000"),
	Two("2000", "2000");
	
	private final String dbValue;
	private final String displayName;
	
	CardCounter(String dbValue, String displayName) {
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
	public static CardCounter fromDbValue(String value) {
		return Arrays.stream(values())
				.filter(c -> c.dbValue.equals(value))
				.findFirst()
				.orElse(null);
	}
}
