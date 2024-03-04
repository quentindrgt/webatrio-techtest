package com.example.webatriotechtest.webatriotechtest.controller;

import com.example.webatriotechtest.webatriotechtest.controller.model.UserID;
import com.example.webatriotechtest.webatriotechtest.exceptions.BirthDateException;
import com.example.webatriotechtest.webatriotechtest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.webatriotechtest.webatriotechtest.model.User;

import javax.management.BadAttributeValueExpException;
import javax.naming.ldap.Control;
import javax.print.attribute.standard.Media;
import java.text.ParseException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.UUID;

@RestController
public class Controller {

    private UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(BadAttributeValueExpException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(BadAttributeValueExpException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Invalid birth date: " + ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/user")
    public ResponseEntity<UserID> createUser(@RequestBody final User user) throws ParseException, BadAttributeValueExpException {
        UserID userId = this.userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userId);
    }
}
