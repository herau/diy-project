package com.ds.ce.diy.web.controllers;

import com.ds.ce.diy.domain.User;
import com.ds.ce.diy.domain.security.CurrentUser;
import com.ds.ce.diy.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

import java.util.Map;

@Controller
public class IndexController {

    @Inject
    private UserService userService;

    @Inject
    private ObjectMapper mapper;

    @RequestMapping("/")
    public String index(Map<String, Object> model, Authentication authentication) {
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null) {
                User user = userService.get(((CurrentUser) principal).getId());
                model.put("user", mapper.valueToTree(user));
            }
        }
        return "app";
    }

}
