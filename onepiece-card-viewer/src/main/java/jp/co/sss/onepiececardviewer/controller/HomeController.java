package jp.co.sss.onepiececardviewer.controller;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sss.onepiececardviewer.entity.News;
import jp.co.sss.onepiececardviewer.service.ImagePathService;
import jp.co.sss.onepiececardviewer.service.NewsService;

@Controller
public class HomeController {
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private ImagePathService imagePathService;
	
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
		
		// スプラッシュ表示フラグをチェック
		Boolean showSplash = (Boolean) session.getAttribute("showSplash");
		if (showSplash != null && showSplash) {
			model.addAttribute("showSplash", true);
			// フラグをクリア（次回は表示しない）
			session.removeAttribute("showSplash");
		} else {
			model.addAttribute("showSplash", false);
		}
		
		return "html/home";
	}
	
	// 画像ファイルを返すエンドファイル
	@GetMapping("/file/news/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getNewsImageFile(@PathVariable Integer id) {
		// ID検索
		Optional<News> getNewsById = newsService.getNewsById(id);
		News image = getNewsById.get();
	
		return imagePathService.loadImageAsResponse(image.getImagePath());
	}

}
