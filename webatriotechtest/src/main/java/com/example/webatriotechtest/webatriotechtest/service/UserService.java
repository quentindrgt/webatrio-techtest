package com.example.webatriotechtest.webatriotechtest.service;

import com.example.webatriotechtest.webatriotechtest.controller.model.UserID;
import com.example.webatriotechtest.webatriotechtest.model.User;
import com.example.webatriotechtest.webatriotechtest.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.management.BadAttributeValueExpException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    public UserID create(User user) throws BadAttributeValueExpException, ParseException {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date birthDate = formatter.parse(user.getBirthDate());
        long diffDatedays = DateUtils.getDateDiff(today, birthDate, TimeUnit.DAYS);

        if (diffDatedays >= 54750) { // 150 years is 54750 days
            throw new BadAttributeValueExpException("You are not 150 years or more.");
        }
        if (diffDatedays < 0) {
            throw new BadAttributeValueExpException("Your birth date is in the future.");
        }
        //
        // *Appel au DAO qui utilise l'ORM et sotre en DB, return l'id du user*
        return new UserID(UUID.randomUUID().toString());
    }
}
