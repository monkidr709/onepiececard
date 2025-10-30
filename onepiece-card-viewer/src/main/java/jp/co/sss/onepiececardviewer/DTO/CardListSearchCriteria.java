package jp.co.sss.onepiececardviewer.DTO;

public class CardListSearchCriteria {
	
	private String key; // 列名
	private String operation; // 演算子 (=, >, < など)
	private Object value; //検索値
	
	public CardListSearchCriteria() {
		
	}
	
	public CardListSearchCriteria(String key, String operation, Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

}
