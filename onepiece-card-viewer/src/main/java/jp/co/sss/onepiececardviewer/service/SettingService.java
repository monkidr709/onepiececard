package jp.co.sss.onepiececardviewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.User;
import jp.co.sss.onepiececardviewer.repository.UserRepository;

@Service
public class SettingService {
	
	@Autowired
	UserRepository userRepository;
	
	// id検索
	public Optional<User> getUserProfile(Integer id) {
		return userRepository.findById(id);
	}
	
	// Userデータを更新
	public void updateProfile(Integer userId, String username, String emailAddress, String telephoneNumber) {
		User user = userRepository.findById(userId)
		        .orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
		user.setUsername(username);
		user.setEmailAddress(emailAddress);
		user.setTelephoneNumber(telephoneNumber);
		
		userRepository.save(user);
	}

}
