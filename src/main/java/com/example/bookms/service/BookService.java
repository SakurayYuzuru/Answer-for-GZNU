package com.example.bookms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.bookms.entity.Book;
import com.example.bookms.repository.BookRepository;

@Service
public class BookService {

    public static final int PAGE_SIZE = 10;

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> list(String keyword, int page) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), PAGE_SIZE);
        if (!StringUtils.hasText(keyword)) {
            return bookRepository.findAllByOrderByIdDesc(pageable);
        }
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrderByIdDesc(keyword, keyword, pageable);
    }

    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        String isbn = book.getIsbn();
        boolean isbnTaken = book.getId() == null
            ? bookRepository.existsByIsbn(isbn)
            : bookRepository.findByIsbn(isbn).filter(existing -> !existing.getId().equals(book.getId())).isPresent();
        if (isbnTaken) {
            throw new IllegalArgumentException("ISBN 已存在");
        }
        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("图书不存在"));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
