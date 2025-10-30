package jp.co.sss.onepiececardviewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.DTO.CardListSearchCriteria;
import jp.co.sss.onepiececardviewer.entity.CardList;
import jp.co.sss.onepiececardviewer.repository.CardListRepository;
import jp.co.sss.onepiececardviewer.specification.CardListGenericSpecification;

@Service
public class CardListService {
	
	@Autowired
	private CardListRepository cardListRepository;
	
	//全件検索 (Id昇順)
	public List<CardList> getAllCardList(){
		return cardListRepository.findAllByOrderByIdAsc();
	}
	
	//主キー検索
	public Optional<CardList> getCardListById(Integer id){
		return cardListRepository.findById(id);
	}
	
	//動的な複数の列による条件検索
	public List<CardList> cardListSearch (List<CardListSearchCriteria> criteriaList) {
		if (criteriaList == null || criteriaList.isEmpty()) {
			return cardListRepository.findAll(Sort.by("id").ascending());
		}
		
		Specification<CardList> spec = null;
		
		for (CardListSearchCriteria criteria : criteriaList) {
			Specification<CardList> newSpec = new CardListGenericSpecification<>(criteria);
			spec = (spec == null) ? newSpec : spec.and(newSpec);
		}
		
		return cardListRepository.findAll(spec, Sort.by("id").ascending());
	}

}
