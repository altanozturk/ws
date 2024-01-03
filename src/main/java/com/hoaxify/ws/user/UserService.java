package com.hoaxify.ws.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service  //@Component gibi bir şey de kullanılabilir initilaze etmek için
public class UserService {

    // Bir class'a ihtiyacımız olduğunda verdiğimiz annotation
    // Her @Autowired taglendirmesi olduğunda ilgili instance yaratılır
    @Autowired
    UserRepository userRepository;


    // Passwordu clear text olarak bırakmamak için
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save(User user) {
        
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

    }
}
