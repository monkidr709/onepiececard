package jp.co.sss.onepiececardviewer.controller;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.sss.onepiececardviewer.entity.User;
import jp.co.sss.onepiececardviewer.form.UserForm;
import jp.co.sss.onepiececardviewer.service.UserService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	//ログイン画面へ遷移
	@GetMapping("/login")
	public String loginForm() {
		return "html/login";
	}
	
	@PostMapping("/login")
	public String login(UserForm form, HttpSession session, Model model) {
		//ユーザー検索
		Optional<User> getFindByUsernameAndPassword = userService.getFindByUsernameAndPassword(form.getUsername(), form.getPassword());
		
		model.addAttribute("userForm", new UserForm());
		
		//ユーザー情報が未入力
		if (getFindByUsernameAndPassword.isEmpty()) {
			model.addAttribute("error", "ユーザー名またはパスワードを入力してください。");
			return "html/login";
		}
		
		//ユーザー認証
		if (getFindByUsernameAndPassword.isPresent()) {
			//認証成功
			User user = getFindByUsernameAndPassword.get();
			session.setAttribute("userId", user.getId());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", user.getRole());
			//セッションタイムアウトを設定 (3600秒)
			session.setMaxInactiveInterval(3600);
			return "redirect:/home";
		} else {
			//認証失敗
			model.addAttribute("error", "ユーザー名またはパスワードが正しくありません");
			return "html/login";
		}
	}
	
	//ログアウト
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}
