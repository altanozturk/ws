package com.hoaxify.ws.user;

import org.springframework.data.jpa.repository.JpaRepository;

// CRUD işlemleri yapan class JpaRepository save, create gibi fonksiyonları mevcut

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    
    
}
