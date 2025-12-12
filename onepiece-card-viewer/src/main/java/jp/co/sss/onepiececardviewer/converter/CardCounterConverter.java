package jp.co.sss.onepiececardviewer.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.sss.onepiececardviewer.enums.CardCounter;

@Converter(autoApply = true)
public class CardCounterConverter implements AttributeConverter<CardCounter, String> {
	
	@Override
	public String convertToDatabaseColumn(CardCounter attribute) {
		return attribute != null ? attribute.getDbValue() : null;
	}
	
	@Override
	public CardCounter convertToEntityAttribute(String dbData) {
		return dbData != null ? CardCounter.fromDbValue(dbData) : null;
	}

}
