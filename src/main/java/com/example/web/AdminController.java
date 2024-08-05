package com.example.web;

import com.example.bot.UserData;
import com.example.bot.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin")
    public String admin(Model model) {
        List<UserData> users = userRepository.readUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/linkTelegram")
    public String linkTelegram(@RequestParam Long telegramId, @RequestParam String username) {
        UserData userData = new UserData(telegramId, username);
        userRepository.saveUser(userData);
        return "redirect:/admin";
    }
}
