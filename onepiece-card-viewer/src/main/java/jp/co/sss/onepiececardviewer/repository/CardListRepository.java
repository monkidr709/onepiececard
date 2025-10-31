package jp.co.sss.onepiececardviewer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import jp.co.sss.onepiececardviewer.entity.CardList;

public interface CardListRepository extends JpaRepository<CardList, Integer>, JpaSpecificationExecutor<CardList> {
	
	List<CardList> findAllByOrderByCardNumberAsc();
	
	List<CardList> findByCardType(String cardType);
	
	List<CardList> findByCardColorAndCardType(String cardColor, String cardType);

}
