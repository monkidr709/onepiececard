package jp.co.sss.onepiececardviewer.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sss.onepiececardviewer.entity.User;
import jp.co.sss.onepiececardviewer.form.UserForm;
import jp.co.sss.onepiececardviewer.service.AuthenticationService;
import jp.co.sss.onepiececardviewer.service.AuthenticationService.AuthenticationResult;

@Controller
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	/**
	 * ログイン画面表示
	 * @return
	 */
	@GetMapping("/login")
	public String loginForm() {
		return "html/login";
	}
	
	/**
	 * ログイン処理
	 * @param form
	 * @param session
	 * @param model
	 * @return
	 */
	@PostMapping("/login")
	public String login(UserForm form, HttpSession session, Model model) {
		// 認証処理
		AuthenticationResult result = authenticationService.authenticate(form);
		
		if (!result.isSuccess()) {
			// 認証失敗
			model.addAttribute("error", result.getErrorMessage());
			model.addAttribute("userForm", new UserForm());
			return "html/login";
		}
		
		// 認証成功 - セッションに情報を設定
		User user = result.getUser();
		session.setAttribute("userId", user.getId());
		session.setAttribute("username", user.getUsername());
		session.setAttribute("role", user.getRole());
		session.setAttribute("showSplash", true);
		//セッションタイムアウトを設定 (86400秒)
		session.setMaxInactiveInterval(86400);
		
		return "redirect:/home";
	}
	
	/**
	 * ログアウト
	 * @param session
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}
