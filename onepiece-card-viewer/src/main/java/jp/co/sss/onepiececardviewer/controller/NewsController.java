package jp.co.sss.onepiececardviewer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.sss.onepiececardviewer.entity.News;
import jp.co.sss.onepiececardviewer.service.NewsService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	// ニュース一覧画面へ遷移
	@GetMapping("/list")
	public String newsList(@RequestParam(required = false) String category, Model model) {
		List<News> newsList;
		
		if (category != null && !category.isEmpty()) {
			newsList = newsService.getNewsByCategory(category);
			model.addAttribute("selectedCategory", category);
		} else {
			newsList = newsService.getAllPublishedNews();
		}
		
		model.addAttribute("newsList", newsList);
		return "html/newsList";
	}
	
	// ニュース詳細画面へ遷移
	@GetMapping("/detail/{id}")
	public String newsDetail(@PathVariable Integer id, Model model) {
		return newsService.getNewsById(id)
				.map(news -> {
					model.addAttribute("news", news);
					return "html/newsDetail";
				})
				.orElse("redirect:/news/list");
	}

}
