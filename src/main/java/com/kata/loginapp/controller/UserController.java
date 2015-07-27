package com.kata.loginapp.controller;

import com.kata.loginapp.service.UserAlreadyExistsException;
import com.kata.loginapp.service.UserService;
import com.kata.loginapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class UserController extends WebMvcConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("success").setViewName("success");
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String loginPage(User user) {
        return "login";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String loginUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            userService.save(user);
        }
        catch (UserAlreadyExistsException exception){
            model.addAttribute("nameErrorMessage", exception.getMessage());
            return "login";
        }

        return "redirect:/success?message="+user.getName();
    }
}
