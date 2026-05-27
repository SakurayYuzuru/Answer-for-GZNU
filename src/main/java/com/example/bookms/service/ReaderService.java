package com.example.bookms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.bookms.entity.User;
import com.example.bookms.repository.UserRepository;

@Service
public class ReaderService {

    private final UserRepository userRepository;

    public ReaderService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listReaders(String keyword) {
        String normalizedKeyword = StringUtils.hasText(keyword) ? keyword.trim() : null;
        if (normalizedKeyword == null) {
            return userRepository.findByRoleOrderByIdDesc(AuthService.ROLE_USER);
        }
        return userRepository.searchByRoleAndKeyword(AuthService.ROLE_USER, normalizedKeyword);
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("读者不存在"));
        if (!AuthService.ROLE_USER.equals(user.getRole())) {
            throw new IllegalArgumentException("该账号不是普通读者");
        }
        return user;
    }

    @Transactional
    public User saveReader(Long id, String username, String nickname, String email, String password) {
        if (!StringUtils.hasText(username) || !StringUtils.hasText(nickname) || !StringUtils.hasText(email)) {
            throw new IllegalArgumentException("请完整填写读者信息");
        }

        User user = id == null ? new User() : findById(id);
        String normalizedUsername = username.trim();
        String normalizedNickname = nickname.trim();
        String normalizedEmail = normalizeEmail(email);

        userRepository.findByUsername(normalizedUsername)
            .filter(existing -> id == null || !existing.getId().equals(id))
            .ifPresent(existing -> {
                throw new IllegalArgumentException("用户名已存在");
            });

        userRepository.findByEmail(normalizedEmail)
            .filter(existing -> id == null || !existing.getId().equals(id))
            .ifPresent(existing -> {
                throw new IllegalArgumentException("邮箱已存在");
            });

        if (id == null && !StringUtils.hasText(password)) {
            throw new IllegalArgumentException("新增读者时密码不能为空");
        }

        user.setUsername(normalizedUsername);
        user.setNickname(normalizedNickname);
        user.setEmail(normalizedEmail);
        user.setRole(AuthService.ROLE_USER);
        if (id == null || StringUtils.hasText(password)) {
            user.setPassword(password.trim());
        }
        return userRepository.save(user);
    }

    public void deleteReader(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("读者不存在"));
        if (AuthService.ROLE_ADMIN.equals(user.getRole())) {
            throw new IllegalArgumentException("管理员账号不能在读者管理中删除");
        }
        userRepository.delete(user);
    }

    private String normalizeEmail(String email) {
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
