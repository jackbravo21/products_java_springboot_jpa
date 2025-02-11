package com.jackbravo21.products_java.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jackbravo21.products_java.dto.MailDTO;
import com.jackbravo21.products_java.dto.UserDTO;
import com.jackbravo21.products_java.model.UserModel;
import com.jackbravo21.products_java.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService){
		this.userService = userService;	
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserModel> checkLogin(@RequestBody UserDTO userDTO){
		UserModel checkUser = userService.userLogin(userDTO);
		return ResponseEntity.ok(checkUser);
	}
	
	@PostMapping("/check")
	public ResponseEntity<Integer> checkUserDB(@RequestBody MailDTO mailDTO){
		String mail = mailDTO.getMail();
		int checkUser = userService.userCheckInDb(mail);
		System.out.println(mailDTO);
		return ResponseEntity.ok(checkUser);
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserModel> register(@RequestBody UserDTO userDTO) {
		UserModel saveUser = userService.register(userDTO);
		return ResponseEntity.ok(saveUser);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<UserModel> editUser(@RequestBody UserDTO userDTO) {
		UserModel editUser = userService.userEdit(userDTO);
		return ResponseEntity.ok(editUser);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		String deleteUser = userService.userDelete(id);
		return ResponseEntity.ok(deleteUser);
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<Optional<UserModel>> oneUser(@PathVariable Long id){
		Optional<UserModel> user = userService.getOneUser(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> getFetchAll() {
		List<UserModel> users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/all")
	public ResponseEntity<List<UserModel>> postFetchAll() {
		List<UserModel> users = userService.getAllUser();
		return ResponseEntity.ok(users);
	}
}
