package jp.co.sss.onepiececardviewer.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.onepiececardviewer.entity.News;
import jp.co.sss.onepiececardviewer.service.NewsService;

@Controller
public class HomeController {
	
	@Autowired
	private NewsService newsService;
	
	//ホーム画面へ遷移
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		//セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		// 最新ニュース6件を取得
		List<News> latestNews = newsService.getLatestNews();
		model.addAttribute("latestNews", latestNews);
		
		return "html/home";
	}

}
