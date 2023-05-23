package com.example.blogappweek9.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getSessionUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUserEmail = authentication.getName();
            System.out.println("static securityconfig username is "+currentUserEmail);
            return currentUserEmail;
        }
        return null;
    }
}
