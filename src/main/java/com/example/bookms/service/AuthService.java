package com.example.bookms.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.bookms.entity.User;
import com.example.bookms.repository.UserRepository;

@Service
public class AuthService {

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User register(String username,
                         String password,
                         String confirmPassword,
                         String nickname,
                         String email,
                         String role) {
        if (!StringUtils.hasText(username)
            || !StringUtils.hasText(password)
            || !StringUtils.hasText(confirmPassword)
            || !StringUtils.hasText(nickname)
            || !StringUtils.hasText(email)) {
            throw new IllegalArgumentException("请完整填写注册信息");
        }
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("两次输入的密码不一致");
        }
        if (!ROLE_ADMIN.equals(role) && !ROLE_USER.equals(role)) {
            throw new IllegalArgumentException("注册角色不合法");
        }

        String normalizedUsername = username.trim();
        String normalizedEmail = normalizeEmail(email);
        if (userRepository.existsByUsername(normalizedUsername)) {
            throw new IllegalArgumentException("用户名已存在");
        }
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("邮箱已存在");
        }

        User user = new User();
        user.setUsername(normalizedUsername);
        user.setPassword(password.trim());
        user.setNickname(nickname.trim());
        user.setEmail(normalizedEmail);
        user.setRole(role);
        return userRepository.save(user);
    }

    public boolean isAdmin(User user) {
        return user != null && ROLE_ADMIN.equals(user.getRole());
    }

    public String normalizeEmail(String email) {
        String normalizedEmail = email == null ? "" : email.trim().toLowerCase();
        if (!StringUtils.hasText(normalizedEmail)) {
            throw new IllegalArgumentException("邮箱不能为空");
        }
        int atIndex = normalizedEmail.indexOf('@');
        if (atIndex <= 0 || atIndex == normalizedEmail.length() - 1 || normalizedEmail.indexOf('@', atIndex + 1) >= 0) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
        return normalizedEmail;
    }
}
