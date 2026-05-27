package com.example.bookms.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.bookms.entity.Book;
import com.example.bookms.entity.BorrowRecord;
import com.example.bookms.entity.User;
import com.example.bookms.repository.BookRepository;
import com.example.bookms.repository.BorrowRepository;
import com.example.bookms.repository.UserRepository;
import com.example.bookms.service.AuthService;
import com.example.bookms.service.BorrowService;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final int TARGET_READER_COUNT = 15;
    private static final int TARGET_BOOK_COUNT = 15;
    private static final int TARGET_BORROW_COUNT = 15;

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BorrowRepository borrowRepository;

    public DataInitializer(UserRepository userRepository,
                           BookRepository bookRepository,
                           BorrowRepository borrowRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.borrowRepository = borrowRepository;
    }

    @Override
    public void run(String... args) {
        User admin = initAdminUser();
        initReaders();
        List<Book> books = initBooks();
        initBorrowRecords(books, admin);
    }

    private User initAdminUser() {
        User admin = userRepository.findByUsername("admin").orElse(null);
        if (admin == null) {
            admin = new User();
            admin.setUsername("admin");
            admin.setPassword("123456");
        }
        if (!StringUtils.hasText(admin.getPassword())) {
            admin.setPassword("123456");
        }
        admin.setNickname("系统管理员");
        admin.setEmail("admin@library.local");
        admin.setRole(AuthService.ROLE_ADMIN);
        return userRepository.save(admin);
    }

    private void initReaders() {
        List<User> existingReaders = userRepository.findByRoleOrderByIdDesc(AuthService.ROLE_USER);
        for (User reader : existingReaders) {
            boolean changed = false;
            if (!StringUtils.hasText(reader.getPassword())) {
                reader.setPassword("123456");
                changed = true;
            }
            if (!StringUtils.hasText(reader.getNickname())) {
                reader.setNickname("读者" + reader.getId());
                changed = true;
            }
            if (!StringUtils.hasText(reader.getEmail())) {
                reader.setEmail(reader.getUsername() + "@example.com");
                changed = true;
            }
            if (changed) {
                userRepository.save(reader);
            }
        }

        long currentReaders = existingReaders.size();
        int toCreate = (int) Math.max(0, TARGET_READER_COUNT - currentReaders);
        for (int i = 0; i < toCreate; i++) {
            int index = (int) currentReaders + i + 1;
            User reader = new User();
            reader.setUsername("reader" + String.format("%02d", index));
            reader.setPassword("123456");
            reader.setNickname("读者" + index);
            reader.setEmail("reader" + String.format("%02d", index) + "@example.com");
            reader.setRole(AuthService.ROLE_USER);
            userRepository.save(reader);
        }
    }

    private List<Book> initBooks() {
        long existingCount = bookRepository.count();
        int toCreate = (int) Math.max(0, TARGET_BOOK_COUNT - existingCount);
        for (int i = 0; i < toCreate; i++) {
            int index = (int) existingCount + i + 1;
            Book book = new Book();
            book.setTitle("图书样例 " + index);
            book.setAuthor("作者" + index);
            book.setCategory(index % 2 == 0 ? "文学" : "编程");
            book.setIsbn("9787300000" + String.format("%05d", index));
            book.setPublisher("示例出版社" + index);
            book.setPublishDate(LocalDate.of(2024 + (index % 2), (index % 12) + 1, Math.min(20, index)));
            book.setStock(8 + (index % 5));
            book.setDescription("这是第 " + index + " 本初始化图书，用于分页和借阅演示。");
            bookRepository.save(book);
        }
        return bookRepository.findAll();
    }

    private void initBorrowRecords(List<Book> books, User admin) {
        long existingCount = borrowRepository.count();
        int toCreate = (int) Math.max(0, TARGET_BORROW_COUNT - existingCount);
        if (toCreate <= 0 || books.isEmpty()) {
            return;
        }

        for (int i = 0; i < toCreate; i++) {
            int index = (int) existingCount + i + 1;
            int bookIndex = i % books.size();
            Book book = books.get(bookIndex);
            boolean returned = index % 3 == 0;

            if (!returned) {
                int currentStock = book.getStock() == null ? 0 : book.getStock();
                if (currentStock <= 0) {
                    book.setStock(3);
                    currentStock = 3;
                }
                book.setStock(currentStock - 1);
                book = bookRepository.save(book);
                books.set(bookIndex, book);
            }

            BorrowRecord record = new BorrowRecord();
            record.setBook(book);
            record.setBorrowerName("借阅人" + index);
            record.setBorrowerPhone("1380000" + String.format("%04d", index));
            record.setBorrowDate(LocalDate.now().minusDays(index));
            record.setDueDate(LocalDate.now().plusDays(10 - (index % 5)));
            record.setStatus(returned ? BorrowService.STATUS_RETURNED : BorrowService.STATUS_BORROWED);
            record.setReturnDate(returned ? LocalDate.now().minusDays(index / 2) : null);
            record.setRemark("初始化借阅记录 " + index);
            record.setOperatorUsername(admin.getUsername());
            borrowRepository.save(record);
        }
    }
}
