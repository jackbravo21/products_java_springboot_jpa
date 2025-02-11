package com.jackbravo21.products_java.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtil {

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Criptografar senha
    public static String encryptPassword(String password) {
        return encoder.encode(password);
    }

    // Verificar se a senha informada corresponde ao hash
    public static boolean verifyPassword(String rawPassword, String encryptedPassword) {
        return encoder.matches(rawPassword, encryptedPassword);
    }

}
