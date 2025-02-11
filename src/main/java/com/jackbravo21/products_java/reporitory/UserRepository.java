package com.jackbravo21.products_java.reporitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jackbravo21.products_java.dto.MailDTO;
import com.jackbravo21.products_java.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

	Optional<UserModel> findByMail(String mail);

	int countByMailAndIdNot(String mail, Long id);
	
	int countByMail(String mail);
}
