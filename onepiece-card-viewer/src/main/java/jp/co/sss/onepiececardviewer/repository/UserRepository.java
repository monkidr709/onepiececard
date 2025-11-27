package jp.co.sss.onepiececardviewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.onepiececardviewer.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	// ユーザー名でユーザー検索
	Optional<User> findByUsername(String username);

}
