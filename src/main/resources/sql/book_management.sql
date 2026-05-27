CREATE DATABASE IF NOT EXISTS book_management DEFAULT CHARACTER SET utf8mb4;

USE book_management;

CREATE TABLE IF NOT EXISTS sys_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(80) NOT NULL,
    category VARCHAR(50) NOT NULL,
    isbn VARCHAR(40) NOT NULL UNIQUE,
    publisher VARCHAR(100),
    publish_date DATE,
    stock INT NOT NULL DEFAULT 0,
    description VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS borrow_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    book_id BIGINT NOT NULL,
    borrower_name VARCHAR(50) NOT NULL,
    borrower_phone VARCHAR(30),
    borrow_date DATE NOT NULL,
    due_date DATE,
    return_date DATE,
    status VARCHAR(20) NOT NULL,
    remark VARCHAR(300),
    operator_username VARCHAR(50),
    CONSTRAINT fk_borrow_book FOREIGN KEY (book_id) REFERENCES book(id)
);

INSERT INTO sys_user (username, password, nickname, email, role)
SELECT 'admin', '123456', '系统管理员', 'admin@library.local', 'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM sys_user WHERE username = 'admin'
);

INSERT INTO book (title, author, category, isbn, publisher, publish_date, stock, description)
SELECT 'Spring Boot 实战', 'Craig Walls', '编程', '9787115428028', '人民邮电出版社', '2023-01-01', 12, 'Spring Boot 入门与实战图书'
WHERE NOT EXISTS (
    SELECT 1 FROM book WHERE isbn = '9787115428028'
);
