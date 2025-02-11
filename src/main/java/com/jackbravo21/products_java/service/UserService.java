package com.jackbravo21.products_java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jackbravo21.products_java.dto.MailDTO;
import com.jackbravo21.products_java.dto.UserDTO;
import com.jackbravo21.products_java.exception.CustomException;
import com.jackbravo21.products_java.model.UserModel;
import com.jackbravo21.products_java.reporitory.UserRepository;
import com.jackbravo21.products_java.utils.DateUtil;
import com.jackbravo21.products_java.utils.EncryptionUtil;

@Service
public class UserService {

	private UserRepository userRepository;	
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserModel register(UserDTO userDTO) {
	    try{
	        UserModel user = new UserModel();
	        
	        Integer administrator = userDTO.getAdministrator();
	        String mail = userDTO.getMail();
	        
	        if(administrator == null) {
	        	administrator = 0;
	        }
	        
	        if(userCheckInDb(mail) > 0){
	            System.out.println("Erro! Usuário já cadastrado!");
	            throw new CustomException(406, "Erro! Usuário já cadastrado!");
	        }
	        
	        /*
	        if(!administrator.equals("user") && !administrator.equals("teacher") && !administrator.equals("administrator")) {
	        	administrator = "user";
	        }
	        */
	        	        
	        user.setFullname(userDTO.getFullname());
	        user.setMail(userDTO.getMail());
	        user.setAdministrator(administrator);
	        user.setPassword(EncryptionUtil.encryptPassword(userDTO.getPassword()));
	        user.setCreated_at(DateUtil.getCurrentDateTime()); 		
	        
	        return userRepository.save(user);
	    } 
	    catch(Exception e){
	        System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
	        throw new CustomException(500, "Erro ao cadastrar usuário!");            
	    }
	}
	
	public UserModel userLogin(UserDTO userDTO) {
		try {
			String mail = userDTO.getMail();
			String passwordCheck = userDTO.getPassword();
			System.out.println(mail);
			System.out.println(passwordCheck);
			
	        UserModel user = userRepository.findByMail(mail).orElse(null);
	        System.out.println("Retorno do DB: " + user);
	        
	        if(user != null && EncryptionUtil.verifyPassword(passwordCheck, user.getPassword())) {
	        	System.out.println("Verify: " + passwordCheck);
	        	System.out.println("Pass DB: " + user.getPassword());
	            return user;
	        } 
	        else{
	            System.out.println("Usuário não encontrado ou senha inválida.");
	            throw new CustomException(406, "Usuário não encontrado ou senha inválida."); 
	        }
	    } 
		catch (Exception e) {
			throw new CustomException(406, "Erro ao buscar usuário: " + e.getMessage());
	    }		
	}

	public int userCheckInDb(String mail) {
		try{
			System.out.println(mail);
			int response = userRepository.countByMail(mail);
			System.out.println(response);
			return response;
		}
		catch(Exception e){
			throw new CustomException(500, "Erro ao checkar e-mail: " + e.getMessage());
		}
	}
	
	public UserModel userEdit(UserDTO userDTO)
	{
	    try{	        
	        Long id = userDTO.getId();
	        String mail = userDTO.getMail();
	        
            int checkMail = userRepository.countByMailAndIdNot(mail, id);
          
	        if(checkMail > 0){
	            System.out.println("Erro! Usuário já cadastrado!");
	            throw new CustomException(406, "Erro! Usuário já cadastrado!");
	        }
	        
	        UserModel user = userRepository.findById(id).orElseThrow(() -> 
            new CustomException(404, "Usuário não encontrado!")
	        );
	        
	        user.setFullname(userDTO.getFullname());
	        user.setMail(userDTO.getMail());
	        user.setAdministrator(userDTO.getAdministrator());
	        user.setPassword(EncryptionUtil.encryptPassword(userDTO.getPassword())); //Criptografa a senha;
	        
	        return userRepository.save(user);
	    } 
	    catch(Exception e){
	    	throw new CustomException(500, "Erro ao editar usuário: " + e.getMessage());
	    }
	}
	
	public String userDelete(Long id) 
	{
		try{
	        if (!userRepository.existsById(id)) {
	            throw new CustomException(404, "Erro ao deletar usuário: Usuário não encontrado");
	        }

	        userRepository.deleteById(id);
	        return "Usuário "+ id +" Deleteado com Sucesso!";
		}
		catch (Exception e) {
	        throw new CustomException(500, "Erro ao deletar usuário: " + e.getMessage());
	    }
	}
	
	public Optional<UserModel> getOneUser(Long id)
	{
		try {
			if (!userRepository.existsById(id)) {
	            throw new CustomException(404, "Erro ao buscar usuário: Usuário não encontrado");
	        }
			return userRepository.findById(id);
		} 
		catch(Exception e){
			throw new CustomException(500, "Erro ao buscar usuário: " + e.getMessage());
		}
	}
	
	public List<UserModel> getAllUser()
	{
		try {
			List<UserModel> users = userRepository.findAll();
			return users;
		} 
		catch(Exception e){
			throw new CustomException(500, "Erro ao buscar usuários: " + e.getMessage());
		}
	}	
}
