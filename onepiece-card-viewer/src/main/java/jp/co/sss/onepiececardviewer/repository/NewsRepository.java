package jp.co.sss.onepiececardviewer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.onepiececardviewer.entity.News;

public interface NewsRepository extends JpaRepository<News, Integer> {
	
	// 公開されているニュースを公開日降順で取得
	List<News> findByIsPublishedTrueOrderByPublishedDateDescCreatedAtDesc();
	
	// カテゴリで絞り込み
	List<News> findByCategoryAndIsPublishedTrueOrderByPublishedDateDescCreatedAtDesc(String category);
	
	// 最新N件を取得
	List<News> findTop6ByIsPublishedTrueOrderByPublishedDateDescCreatedAtDesc();

}
