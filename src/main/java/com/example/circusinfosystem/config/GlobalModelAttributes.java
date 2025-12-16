package com.example.circusinfosystem.config;

import com.example.circusinfosystem.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("user")
    public User addUserToModel(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
