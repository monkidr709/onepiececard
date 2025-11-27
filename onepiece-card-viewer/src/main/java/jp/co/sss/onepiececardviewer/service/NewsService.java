package jp.co.sss.onepiececardviewer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sss.onepiececardviewer.entity.News;
import jp.co.sss.onepiececardviewer.repository.NewsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NewsService {
	
	private final NewsRepository newsRepository;
	
	/**
	 * 全ニュース取得（公開済みのみ、公開日降順）
	 */
	@Transactional(readOnly = true)
	public List<News> getAllPublishedNews() {
		return newsRepository.findByIsPublishedTrueOrderByPublishedDateDescCreatedAtDesc();
	}
	
	/**
	 * カテゴリ別ニュース取得
	 */
	@Transactional(readOnly = true)
	public List<News> getNewsByCategory(String category) {
		return newsRepository.findByCategoryAndIsPublishedTrueOrderByPublishedDateDescCreatedAtDesc(category);
	}
	
	/**
	 * 最新6件取得（ホーム画面用）
	 */
	@Transactional(readOnly = true)
	public List<News> getLatestNews() {
		return newsRepository.findTop6ByIsPublishedTrueOrderByPublishedDateDescCreatedAtDesc();
	}
	
	/**
	 * ニュース詳細取得
	 */
	@Transactional(readOnly = true)
	public Optional<News> getNewsById(Integer id) {
		return newsRepository.findById(id);
	}
	
	/**
	 * ニュース保存（管理者用）
	 */
	@Transactional
	public News saveNews(News news) {
		return newsRepository.save(news);
	}
	
	/**
	 * ニュース削除（管理者用）
	 */
	@Transactional
	public void deleteNews(Integer id) {
		newsRepository.deleteById(id);
	}

}
