package jp.co.sss.onepiececardviewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.onepiececardviewer.entity.User;
import jp.co.sss.onepiececardviewer.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//ユーザー検索
	public Optional<User> getFindByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

}
