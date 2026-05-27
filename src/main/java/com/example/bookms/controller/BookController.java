package com.example.bookms.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookms.config.LoginInterceptor;
import com.example.bookms.entity.Book;
import com.example.bookms.entity.User;
import com.example.bookms.service.AuthService;
import com.example.bookms.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthService authService;

    public BookController(BookService bookService, AuthService authService) {
        this.bookService = bookService;
        this.authService = authService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(@RequestParam(required = false) String keyword,
                            @RequestParam(defaultValue = "0") int page,
                            HttpSession session,
                            Model model) {
        Page<Book> bookPage = bookService.list(keyword, page);
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("currentPage", bookPage.getNumber());
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("loginUser", user);
        model.addAttribute("isAdmin", authService.isAdmin(user));
        model.addAttribute("total", bookPage.getTotalElements());
        return "dashboard";
    }

    @GetMapping("/books/new")
    public String newBookPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(user)) {
            return "redirect:/dashboard";
        }
        fillBookForm(model, user, new Book(), false, null);
        return "book-form";
    }

    @GetMapping("/books/edit/{id}")
    public String editBookPage(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(user)) {
            return "redirect:/dashboard";
        }
        Book book = bookService.findById(id);
        fillBookForm(model, user, book, true, null);
        return "book-form";
    }

    @PostMapping("/books/save")
    public String save(@ModelAttribute Book book, HttpSession session, Model model) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(user)) {
            return "redirect:/dashboard";
        }
        try {
            bookService.save(book);
            return "redirect:/dashboard";
        } catch (IllegalArgumentException ex) {
            fillBookForm(model, user, book, book.getId() != null, ex.getMessage());
            return "book-form";
        }
    }

    @GetMapping("/books/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(user)) {
            return "redirect:/dashboard";
        }
        bookService.delete(id);
        return "redirect:/dashboard";
    }

    private void fillBookForm(Model model, User loginUser, Book bookForm, boolean editing, String formError) {
        model.addAttribute("bookForm", bookForm);
        model.addAttribute("editing", editing);
        model.addAttribute("formError", formError);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("isAdmin", authService.isAdmin(loginUser));
    }
}
