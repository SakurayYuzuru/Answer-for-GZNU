package com.example.bookms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookms.config.LoginInterceptor;
import com.example.bookms.entity.User;
import com.example.bookms.service.AuthService;
import com.example.bookms.service.ReaderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReaderController {

    private final ReaderService readerService;
    private final AuthService authService;

    public ReaderController(ReaderService readerService, AuthService authService) {
        this.readerService = readerService;
        this.authService = authService;
    }

    @GetMapping("/readers")
    public String readerPage(@RequestParam(required = false) String keyword, HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        List<User> readers = readerService.listReaders(keyword);
        model.addAttribute("readers", readers);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("isAdmin", authService.isAdmin(loginUser));
        model.addAttribute("total", readers.size());
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "readers";
    }

    @GetMapping("/readers/new")
    public String newReaderPage(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(loginUser)) {
            return "redirect:/readers";
        }
        fillReaderForm(model, loginUser, new User(), false, null);
        return "reader-form";
    }

    @GetMapping("/readers/edit/{id}")
    public String editReaderPage(@PathVariable Long id, HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(loginUser)) {
            return "redirect:/readers";
        }
        User reader = readerService.findById(id);
        fillReaderForm(model, loginUser, reader, true, null);
        return "reader-form";
    }

    @PostMapping("/readers/save")
    public String saveReader(@RequestParam(required = false) Long id,
                             @RequestParam String username,
                             @RequestParam String nickname,
                             @RequestParam String email,
                             @RequestParam(required = false) String password,
                             HttpSession session,
                             Model model) {
        User loginUser = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(loginUser)) {
            return "redirect:/readers";
        }
        try {
            readerService.saveReader(id, username, nickname, email, password);
            return "redirect:/readers";
        } catch (IllegalArgumentException ex) {
            User reader = new User();
            reader.setId(id);
            reader.setUsername(username);
            reader.setNickname(nickname);
            reader.setEmail(email);
            reader.setRole(AuthService.ROLE_USER);
            fillReaderForm(model, loginUser, reader, id != null, ex.getMessage());
            return "reader-form";
        }
    }

    @GetMapping("/readers/delete/{id}")
    public String deleteReader(@PathVariable Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(loginUser)) {
            return "redirect:/readers";
        }
        readerService.deleteReader(id);
        return "redirect:/readers";
    }

    private void fillReaderForm(Model model, User loginUser, User reader, boolean editing, String formError) {
        model.addAttribute("readerForm", reader);
        model.addAttribute("editing", editing);
        model.addAttribute("formError", formError);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("isAdmin", authService.isAdmin(loginUser));
    }
}
