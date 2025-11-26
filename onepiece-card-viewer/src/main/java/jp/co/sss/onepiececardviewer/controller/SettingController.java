package jp.co.sss.onepiececardviewer.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sss.onepiececardviewer.DTO.ResponseMessage;
import jp.co.sss.onepiececardviewer.entity.User;
import jp.co.sss.onepiececardviewer.form.UserForm;
import jp.co.sss.onepiececardviewer.service.SettingService;

@Controller
@RequestMapping("/setting")
public class SettingController {
	
	@Autowired
	SettingService settingService;
	
	// 設定画面の表示
	@GetMapping
	public String setting(HttpSession session) {
		String username = (String) session.getAttribute("username");
		// セッションタイムアウト
		if (username == null) {
			return "redirect:/login";
		}
		
		return "html/setting";
	}
	
	// プロフィール設定の表示
	@GetMapping("/profile")
	public String getProfile(HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		User user = settingService.getUserProfile(userId).orElse(null);
		model.addAttribute("profile", user);
		return "html/settings/profile :: content";
	}
	
	// プロフィール設定の保存
	@PostMapping("/profile")
	@ResponseBody
	public ResponseMessage saveProfile(HttpSession session, @ModelAttribute UserForm userForm) {
		Integer userId = (Integer) session.getAttribute("userId");
		settingService.updateProfile(userId, userForm.getUsername(), userForm.getEmailAddress(), userForm.getTelephoneNumber());
		
		// セッションに保存したユーザー名を変更
		if (session != null && session.getAttribute("username") != null) {
			session.removeAttribute("username");
			session.setAttribute("username", userForm.getUsername());
		}
		
		return new ResponseMessage("success", "プロフィールを更新しました");
	}
	
	// セキュリティ設定の表示
	@GetMapping("/security")
	public String getSecurity() {
		return "html/settings/security :: content";
	}

}
