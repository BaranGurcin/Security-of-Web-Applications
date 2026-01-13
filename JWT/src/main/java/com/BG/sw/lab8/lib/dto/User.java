package com.BG.sw.lab8.lib.dto;

import com.BG.sw.lab8.lib.security.Role;

public record User(String username, String hashPassword, Role role) {

    public String getUsername() {
        return username; 
    }
    
    public String hashPassword() {
        return hashPassword;
    }
    
    public Role getRole() {
        return role; 
    }
}