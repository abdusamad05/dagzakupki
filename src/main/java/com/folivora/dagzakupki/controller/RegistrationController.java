package com.folivora.dagzakupki.controller;


import com.folivora.dagzakupki.domain.Role;
import com.folivora.dagzakupki.domain.User;
import com.folivora.dagzakupki.reps.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRep userRep;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRep.findByUsername(user.getUsername());

        if (userFromDb != null){
            model.put("message","User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRep.save(user);
        return "redirect:/login";
    }
}
