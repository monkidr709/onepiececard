package jp.co.sss.onepiececardviewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.User;
import jp.co.sss.onepiececardviewer.repository.UserRepository;

@Service
public class SettingService {
	
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    PasswordEncoder passwordEncoder; 
	
	// id検索
	public Optional<User> getUserProfile(Integer id) {
		return userRepository.findById(id);
	}
	
	// Userデータ更新
	public void updateProfile(Integer userId, String username, String emailAddress, String telephoneNumber) {
		User user = userRepository.findById(userId)
		        .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
		user.setUsername(username);
		user.setEmailAddress(emailAddress);
		user.setTelephoneNumber(telephoneNumber);
		
		userRepository.save(user);
	}
	
	// password更新
	public void changePassword(Integer userId, String currentPassword, String newPassword) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
		
		boolean isPasswordCorrect = false;
		
		// データベースのパスワードがハッシュ化されているか確認
		// BCryptのハッシュは "$2a$" または "$2b$" で始まる
		if (user.getPassword().startsWith("$2a$") || user.getPassword().startsWith("$2b$")) {
			// ハッシュ化されている場合
			isPasswordCorrect = passwordEncoder.matches(currentPassword, user.getPassword());
		} else {
			// 平文の場合（既存データ対応）
			isPasswordCorrect = currentPassword.equals(user.getPassword());
		}
		
		if (isPasswordCorrect) {
			// 新しいパスワードは必ずハッシュ化して保存
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepository.save(user);
		} else {
			throw new RuntimeException("現在のパスワードが正しくありません");
		}
	}

}
