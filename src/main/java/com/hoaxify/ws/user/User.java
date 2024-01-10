package com.hoaxify.ws.user;

import com.hoaxify.ws.user.validation.UniqueEmail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// @Entity ile database içerisinde bir tabloya atıyoruz
// class ismi User olduğu için database içerisinde kullanılmıyor
// alternatif olarak name="users" şeklinde tanımlıyoruz  
@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))  
public class User {

    // @Id tagi ile uniqe bir ifade haline getiriyoruz
    // @GenerateValue id'nin nasıl generate edileceğini db ye bırakıyoruz
    // Çünkü requestte id göndermediğimiz içi default long değeri olarak hep 0 kaydetmeye çalışıp hata alacak
    @Id
    @GeneratedValue
    long id;

    // @NotBlank ile boş olmaması kontrolü yapılır
    @NotBlank
    @Size(min=4, max=255) // bu constraint ile notBlank çakışıp hata alabilir
    String username;

    // @NotBlank ile boş olmaması kontrolü yapılır
    @NotBlank
    @Email // email formatında olması için
    @UniqueEmail // yazdığımız custom constraint
    String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    @Size(min=8, max=255)
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
