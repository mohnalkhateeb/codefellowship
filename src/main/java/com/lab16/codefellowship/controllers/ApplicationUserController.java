package com.lab16.codefellowship.controllers;

import com.lab16.codefellowship.models.ApplicationUser;
import com.lab16.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        newUser = applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

    @GetMapping("/myprofile")
    public String getProfile(Principal p, Model m) {
        ApplicationUser applicationUser = applicationUserRepository.findApplicationUserByUsername(p.getName());
        m.addAttribute("applicationUser", applicationUser);
        return "myprofile";
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
    @PostMapping("/users/follow")
    public RedirectView addFollower(long followedUser, Principal p) {
        ApplicationUser primaryUser = applicationUserRepository.findApplicationUserByUsername(p.getName());
        primaryUser.addFollower(applicationUserRepository.findById(followedUser).get());
        applicationUserRepository.save(primaryUser);
        return new RedirectView("/users");
    }

    @PostMapping("/users/unfollow")
    public RedirectView removeFollower(long unfollowedUser, Principal p) {
        ApplicationUser primaryUser = applicationUserRepository.findApplicationUserByUsername(p.getName());
        primaryUser.removeFollower(applicationUserRepository.findById(unfollowedUser).get());
        applicationUserRepository.save(primaryUser);
        return new RedirectView("/users");
    }

}
