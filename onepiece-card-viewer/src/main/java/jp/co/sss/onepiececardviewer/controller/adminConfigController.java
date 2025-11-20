package jp.co.sss.onepiececardviewer.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminConfigController {
	
	@GetMapping("/admin/config")
	public String adminConfig(HttpSession session) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		return "html/adminConfig";
	}

}
