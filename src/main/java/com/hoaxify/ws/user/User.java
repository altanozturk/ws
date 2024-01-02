package com.hoaxify.ws.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity ile database içerisinde bir tabloya atıyoruz
// class ismi User olduğu için database içerisinde kullanılmıyor
// alternatif olarak name="users" şeklinde tanımlıyoruz  
@Entity
@Table(name="users")  
public class User {

    // @Id tagi ile uniqe bir ifade haline getiriyoruz
    // @GenerateValue id'nin nasıl generate edileceğini db ye bırakıyoruz
    // Çünkü requestte id göndermediğimiz içi default long değeri olarak hep 0 kaydetmeye çalışıp hata alacak
    @Id
    @GeneratedValue
    long id;

    String username;

    String email;

    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
