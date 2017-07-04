package site.chronos.utils;

import java.nio.charset.StandardCharsets;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.common.hash.Hashing;

public class DiagestUtils {
	private static final PasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	public static void main(String[] args){
		String string = Hashing.md5().hashString("123456", StandardCharsets.UTF_8).toString().toUpperCase();
		System.out.println(string.toUpperCase());
		
	}
	
	
	
}
