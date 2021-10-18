package com.lab16.codefellowship.controllers;

import com.lab16.codefellowship.models.ApplicationUser;
import com.lab16.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationUserController {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/users")
    public RedirectView createUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName,
                                   @RequestParam Date dateOfBirth, @RequestParam String bio) {
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName,
                dateOfBirth, bio);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/profile");
    }

    @GetMapping("/profile")
    public String getProfile(Principal p, Model m) {
        ApplicationUser applicationUser = applicationUserRepository.findApplicationUserByUsername(p.getName());
        m.addAttribute("applicationUser", applicationUser);
        return "profile";
    }

    @GetMapping("/users")
    public String getAllUsers(Principal p, Model m) {
        ApplicationUser applicationUser =  applicationUserRepository.findApplicationUserByUsername(p.getName());
        List<ApplicationUser> allUsers = applicationUserRepository.findAll();
        m.addAttribute("applicationUser", applicationUser);
        m.addAttribute("allUsers", allUsers);
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getOneUser(@PathVariable long id, Principal p, Model m) {
        ApplicationUser otherUser = applicationUserRepository.findById(id).get();
        ApplicationUser currentUser =  applicationUserRepository.findApplicationUserByUsername(p.getName());
        m.addAttribute("otherUser", otherUser);
        m.addAttribute("currentUser", currentUser);
        return "singleUser";
    }


}
