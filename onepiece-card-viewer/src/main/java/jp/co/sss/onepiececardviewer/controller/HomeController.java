package jp.co.sss.onepiececardviewer.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.sss.onepiececardviewer.service.HomeService;

@Controller
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	//ホーム画面へ遷移
	@GetMapping("/home")
	public String home(HttpSession session) {
		String username = (String) session.getAttribute("username");
		//セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		return "html/home";
	}

}
