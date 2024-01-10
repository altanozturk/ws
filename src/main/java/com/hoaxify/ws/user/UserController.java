package com.hoaxify.ws.user;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericMessage;

import jakarta.validation.Valid;

@RestController
public class UserController {

    // Bir class'a ihtiyacımız olduğunda verdiğimiz annotation
    // Her @Autowired taglendirmesi olduğunda ilgili instance yaratılır
    @Autowired
    UserService usersService;

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody User user) {
        usersService.save(user);
        return new GenericMessage("User is created");
    };

    // User class'ında verdiğimiz username ve email için @NotBlank sebebiyle spring
    // kendisi MethodArgumentNotValidException yakalıyor bunu burda mapliyoruz
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgNotValidEx(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage("Validation error");
        apiError.setStatus(400);

        var validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage,
                        (existing, replacing) -> existing));

        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
        // badRequest() 400 dönecek
        // ek olarak apiError nesnesi dönecek

    }

    @ExceptionHandler(NotUniqueEmailException.class)
    ResponseEntity<ApiError> handleNotUniqueEmailEx(NotUniqueEmailException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage("Validation error");
        apiError.setStatus(400);

        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put("email", "E-mail in use");

        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
        // badRequest() 400 dönecek
        // ek olarak apiError nesnesi dönecek

    }

};
