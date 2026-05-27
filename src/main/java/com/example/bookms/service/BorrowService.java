package com.example.bookms.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.bookms.entity.Book;
import com.example.bookms.entity.BorrowRecord;
import com.example.bookms.repository.BookRepository;
import com.example.bookms.repository.BorrowRepository;

@Service
public class BorrowService {

    public static final String STATUS_BORROWED = "BORROWED";
    public static final String STATUS_RETURNED = "RETURNED";
    public static final int PAGE_SIZE = 10;

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

    public Page<BorrowRecord> list(String status, String keyword, int page) {
        Pageable pageable = PageRequest.of(Math.max(page, 0), PAGE_SIZE);
        String normalizedStatus = StringUtils.hasText(status) ? status.trim() : null;
        String normalizedKeyword = StringUtils.hasText(keyword) ? keyword.trim() : null;
        if (normalizedStatus == null && normalizedKeyword == null) {
            return borrowRepository.findAllByOrderByIdDesc(pageable);
        }
        if (normalizedKeyword == null) {
            return borrowRepository.findByStatusOrderByIdDesc(normalizedStatus, pageable);
        }
        return borrowRepository.search(normalizedStatus, normalizedKeyword, pageable);
    }

    public BorrowRecord findById(Long id) {
        return borrowRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("借阅记录不存在"));
    }

    @Transactional
    public void createBorrow(Long bookId,
                             String borrowerName,
                             String borrowerPhone,
                             LocalDate borrowDate,
                             LocalDate dueDate,
                             String remark,
                             String operatorUsername) {
        if (bookId == null) {
            throw new IllegalArgumentException("请选择图书");
        }
        if (!StringUtils.hasText(borrowerName)) {
            throw new IllegalArgumentException("借阅人不能为空");
        }

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("图书不存在"));
        if (book.getStock() == null || book.getStock() <= 0) {
            throw new IllegalArgumentException("库存不足，无法借出");
        }

        BorrowRecord record = new BorrowRecord();
        record.setBook(book);
        record.setBorrowerName(borrowerName.trim());
        record.setBorrowerPhone(StringUtils.hasText(borrowerPhone) ? borrowerPhone.trim() : null);
        record.setBorrowDate(borrowDate == null ? LocalDate.now() : borrowDate);
        record.setDueDate(dueDate);
        record.setRemark(StringUtils.hasText(remark) ? remark.trim() : null);
        record.setStatus(STATUS_BORROWED);
        record.setOperatorUsername(operatorUsername);
        borrowRepository.save(record);

        book.setStock(book.getStock() - 1);
        bookRepository.save(book);
    }

    @Transactional
    public void updateBorrow(Long id,
                             Long bookId,
                             String borrowerName,
                             String borrowerPhone,
                             LocalDate borrowDate,
                             LocalDate dueDate,
                             String remark) {
        BorrowRecord record = findById(id);
        if (!StringUtils.hasText(borrowerName)) {
            throw new IllegalArgumentException("借阅人不能为空");
        }
        if (bookId == null) {
            throw new IllegalArgumentException("请选择图书");
        }

        Book currentBook = record.getBook();
        if (!currentBook.getId().equals(bookId)) {
            Book newBook = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("图书不存在"));
            if (STATUS_BORROWED.equals(record.getStatus())) {
                if (newBook.getStock() == null || newBook.getStock() <= 0) {
                    throw new IllegalArgumentException("目标图书库存不足，无法切换");
                }
                currentBook.setStock((currentBook.getStock() == null ? 0 : currentBook.getStock()) + 1);
                newBook.setStock(newBook.getStock() - 1);
                bookRepository.save(currentBook);
                bookRepository.save(newBook);
            }
            record.setBook(newBook);
        }

        record.setBorrowerName(borrowerName.trim());
        record.setBorrowerPhone(StringUtils.hasText(borrowerPhone) ? borrowerPhone.trim() : null);
        record.setBorrowDate(borrowDate == null ? LocalDate.now() : borrowDate);
        record.setDueDate(dueDate);
        record.setRemark(StringUtils.hasText(remark) ? remark.trim() : null);
        borrowRepository.save(record);
    }

    @Transactional
    public void returnBook(Long borrowId) {
        BorrowRecord record = findById(borrowId);
        if (!STATUS_BORROWED.equals(record.getStatus())) {
            throw new IllegalArgumentException("该记录已归还");
        }

        record.setStatus(STATUS_RETURNED);
        record.setReturnDate(LocalDate.now());
        borrowRepository.save(record);

        Book book = record.getBook();
        book.setStock((book.getStock() == null ? 0 : book.getStock()) + 1);
        bookRepository.save(book);
    }

    @Transactional
    public void deleteRecord(Long borrowId) {
        BorrowRecord record = findById(borrowId);
        if (STATUS_BORROWED.equals(record.getStatus())) {
            Book book = record.getBook();
            book.setStock((book.getStock() == null ? 0 : book.getStock()) + 1);
            bookRepository.save(book);
        }
        borrowRepository.delete(record);
    }
}
