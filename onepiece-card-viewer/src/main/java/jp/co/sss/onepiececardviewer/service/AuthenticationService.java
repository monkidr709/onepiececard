package jp.co.sss.onepiececardviewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.User;
import jp.co.sss.onepiececardviewer.form.UserForm;

@Service
public class AuthenticationService {
	
	@Autowired
	private UserService userService;
	
	/**
	 * ユーザー認証
	 * @param form
	 * @return
	 */
	public AuthenticationResult authenticate(UserForm form) {
		// 入力値の検証
		if (!validateInput(form)) {
			return AuthenticationResult.failure("ユーザー名またはパスワードを入力してください");
		}
		
		// ユーザー検索
		Optional<User> userOptional = userService.getFindByUsername(form.getUsername());
		if (!userOptional.isPresent()) {
			return AuthenticationResult.failure("ユーザー名またはパスワードが正しくありません");
		}
		User user = userOptional.get();
		
		// 削除済みユーザーチェック
		if (user.isDeleted()) {
			return AuthenticationResult.failure("ユーザー名またはパスワードが正しくありません");
		}
		
		// パスワード検証
		if (!userService.verifyPassword(form.getPassword(), user.getPassword())) {
			return AuthenticationResult.failure("ユーザー名またはパスワードが正しくありません");
		}
		
		return AuthenticationResult.success(user);
	}
	
	/**
	 * 入力値の検証
	 * @param form ユーザーフォーム
	 * @return 検証結果
	 */
	private boolean validateInput(UserForm form) {
		return form.getUsername() != null && !form.getUsername().isEmpty() 
				&& form.getPassword() != null && !form.getPassword().isEmpty();
	}

	/**
	 * 認証結果を保持するクラス
	 */
	public static class AuthenticationResult {
		private final boolean success;
		private final User user;
		private final String errorMessage;
		
		private AuthenticationResult(boolean success, User user, String errorMessage) {
			this.success = success;
			this.user = user;
			this.errorMessage = errorMessage;
		}
		
		public static AuthenticationResult success(User user) {
			return new AuthenticationResult(true, user, null);
		}
		
		public static AuthenticationResult failure(String errorMessage) {
			return new AuthenticationResult(false, null, errorMessage);
		}
		
		public boolean isSuccess() {
			return success;
		}
		
		public User getUser() {
			return user;
		}
		
		public String getErrorMessage() {
			return errorMessage;
		}
	}

}
