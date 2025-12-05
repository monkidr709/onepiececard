package jp.co.sss.onepiececardviewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.User;
import jp.co.sss.onepiececardviewer.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	//ユーザー検索
	public Optional<User> getFindByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	// パスワードの検証
//	public boolean verifyPassword(String rawPassword, String encodedPassword) {
//		return passwordEncoder.matches(rawPassword, encodedPassword);
//	}
	
	// パスワードの検証 (ハッシュ化されていない場合)
	public boolean verifyPassword(String rawPassword, String encodedPassword) {
		// まずBCryptでの検証を試みる
		try {
			if (passwordEncoder.matches(rawPassword, encodedPassword)) {
				return true;
			}
		} catch (IllegalArgumentException e) {
			// BCryptでない場合は例外が発生する
		}
		
		// BCryptでの検証に失敗した場合、平文での比較を試みる
		return rawPassword.equals(encodedPassword);
	}

}
