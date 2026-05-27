package com.example.bookms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookms.config.LoginInterceptor;
import com.example.bookms.entity.Book;
import com.example.bookms.entity.BorrowRecord;
import com.example.bookms.entity.User;
import com.example.bookms.service.AuthService;
import com.example.bookms.service.BookService;
import com.example.bookms.service.BorrowService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BorrowController {

    private final BorrowService borrowService;
    private final BookService bookService;
    private final AuthService authService;

    public BorrowController(BorrowService borrowService, BookService bookService, AuthService authService) {
        this.borrowService = borrowService;
        this.bookService = bookService;
        this.authService = authService;
    }

    @GetMapping("/borrows")
    public String borrowPage(@RequestParam(required = false) String status,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(defaultValue = "0") int page,
                             HttpSession session,
                             Model model) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        fillBorrowPage(model, user, status, keyword, page, null);
        return "borrows";
    }

    @GetMapping("/borrows/new")
    public String newBorrowPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(user)) {
            return "redirect:/borrows";
        }
        fillBorrowForm(model, user, null, null);
        return "borrow-form";
    }

    @GetMapping("/borrows/edit/{id}")
    public String editBorrowPage(@PathVariable Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(user)) {
            return "redirect:/borrows";
        }
        BorrowRecord record = borrowService.findById(id);
        fillBorrowForm(model, user, record, null);
        return "borrow-form";
    }

    @PostMapping("/borrows/save")
    public String saveBorrow(@RequestParam(required = false) Long id,
                             @RequestParam Long bookId,
                             @RequestParam String borrowerName,
                             @RequestParam(required = false) String borrowerPhone,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate borrowDate,
                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dueDate,
                             @RequestParam(required = false) String remark,
                             HttpSession session,
                             Model model) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(user)) {
            return "redirect:/borrows";
        }
        try {
            if (id == null) {
                borrowService.createBorrow(bookId, borrowerName, borrowerPhone, borrowDate, dueDate, remark, user.getUsername());
            } else {
                borrowService.updateBorrow(id, bookId, borrowerName, borrowerPhone, borrowDate, dueDate, remark);
            }
            return "redirect:/borrows";
        } catch (IllegalArgumentException ex) {
            BorrowRecord fallback = new BorrowRecord();
            fallback.setId(id);
            fallback.setBorrowerName(borrowerName);
            fallback.setBorrowerPhone(borrowerPhone);
            fallback.setBorrowDate(borrowDate);
            fallback.setDueDate(dueDate);
            fallback.setRemark(remark);
            Book book = new Book();
            book.setId(bookId);
            fallback.setBook(book);
            fillBorrowForm(model, user, fallback, ex.getMessage());
            return "borrow-form";
        }
    }

    @GetMapping("/borrows/return/{id}")
    public String returnBook(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(user)) {
            return "redirect:/borrows";
        }
        borrowService.returnBook(id);
        return "redirect:/borrows";
    }

    @GetMapping("/borrows/delete/{id}")
    public String deleteBorrow(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute(LoginInterceptor.SESSION_KEY);
        if (!authService.isAdmin(user)) {
            return "redirect:/borrows";
        }
        borrowService.deleteRecord(id);
        return "redirect:/borrows";
    }

    private void fillBorrowPage(Model model, User loginUser, String status, String keyword, int page, String error) {
        Page<BorrowRecord> borrowPage = borrowService.list(status, keyword, page);
        model.addAttribute("records", borrowPage.getContent());
        model.addAttribute("borrowPage", borrowPage);
        model.addAttribute("currentPage", borrowPage.getNumber());
        model.addAttribute("status", status == null ? "" : status);
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("formError", error);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("isAdmin", authService.isAdmin(loginUser));
        model.addAttribute("borrowedStatus", BorrowService.STATUS_BORROWED);
        model.addAttribute("returnedStatus", BorrowService.STATUS_RETURNED);
        model.addAttribute("total", borrowPage.getTotalElements());
    }

    private void fillBorrowForm(Model model, User loginUser, BorrowRecord record, String formError) {
        List<Book> books = bookService.listAll();
        model.addAttribute("books", books);
        model.addAttribute("record", record);
        model.addAttribute("editing", record != null && record.getId() != null);
        model.addAttribute("formError", formError);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("isAdmin", authService.isAdmin(loginUser));
    }
}
