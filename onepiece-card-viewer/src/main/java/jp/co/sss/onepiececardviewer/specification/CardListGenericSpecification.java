package jp.co.sss.onepiececardviewer.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import jp.co.sss.onepiececardviewer.DTO.CardListSearchCriteria;

public class CardListGenericSpecification<T> implements Specification<T> {
	
	private CardListSearchCriteria criteria;
	
	public CardListGenericSpecification(CardListSearchCriteria criteria) {
		this.criteria = criteria;
	}
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criteria.getOperation().equals("=")) {
			return builder.equal(root.get(criteria.getKey()), criteria.getValue());
		} else if (criteria.getOperation().equals(">")) {
			return builder.greaterThan(root.get(criteria.getKey()),
					criteria.getValue().toString());
		} else if (criteria.getOperation().equals("<")) {
			return builder.lessThan(root.get(criteria.getKey()),
					criteria.getValue().toString());
		} else if (criteria.getOperation().equals("LIKE")) {
			return builder.like(root.get(criteria.getKey()),
					"%" + criteria.getValue() + "%");
		}
		return null;
	}

}
