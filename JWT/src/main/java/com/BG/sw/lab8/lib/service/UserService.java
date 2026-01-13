package com.BG.sw.lab8.lib.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.BG.sw.lab8.lib.dto.User;
import com.BG.sw.lab8.lib.security.Role;
import com.BG.sw.lab8.lib.security.SecurityUtil;

public class UserService {
    private static final Map<String, User> USERS = new ConcurrentHashMap<>(Map.of(
            "John_doe", new User("John_doe", SecurityUtil.hashPassword("password"), Role.USER),
            "Michael", new User("Michael", SecurityUtil.hashPassword("masi"), Role.USER)
    ));


    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(USERS.get(username));
    }
}
