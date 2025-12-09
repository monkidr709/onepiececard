package jp.co.sss.onepiececardviewer.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.sss.onepiececardviewer.enums.CardPack;

@Converter(autoApply = true)
public class CardPackConverter implements AttributeConverter<CardPack, String> {
	
	@Override
	public String convertToDatabaseColumn(CardPack attribute) {
		return attribute != null ? attribute.getDbValue() : null;
	}
	
	@Override
	public CardPack convertToEntityAttribute(String dbData) {
		return dbData != null ? CardPack.fromDbValue(dbData) : null;
	}

}
