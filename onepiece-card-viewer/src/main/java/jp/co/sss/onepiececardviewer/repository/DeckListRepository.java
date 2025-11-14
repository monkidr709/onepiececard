package jp.co.sss.onepiececardviewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.onepiececardviewer.entity.DeckList;

public interface DeckListRepository extends JpaRepository<DeckList, Integer> {
	
	List<DeckList> findByUserNameIdOrderByIdAsc(Integer userId);
	
	Optional<DeckList> findById(Integer deckId);

}
