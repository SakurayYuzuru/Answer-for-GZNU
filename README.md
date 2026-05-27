# 图书管理系统

基于 Spring Boot 3 + Thymeleaf + MySQL 的简单图书管理系统，包含：

- 登录页面
- 注册页面
- 图书列表页面
- 独立的新增/编辑图书页面
- 借阅管理页面
- 图书新增、编辑、删除、搜索
- 借阅登记、归还、借阅记录删除
- 基于 Session 的登录校验
- 区分管理员和普通用户

## 项目位置

`D:\book-management-system`

## 环境要求

- JDK 17
- MySQL 8.x
- Maven 3.9+ 或 IDEA 自带 Maven

## 数据库准备

1. 先在 MySQL 中执行：

```sql
source D:/book-management-system/src/main/resources/sql/book_management.sql;
```

2. 或者手动创建数据库：

```sql
CREATE DATABASE book_management DEFAULT CHARACTER SET utf8mb4;
```

3. 默认数据库连接配置在 [application.yml](D:/book-management-system/src/main/resources/application.yml)：

- 数据库名：`book_management`
- 用户名：`root`
- 密码：`123456`

如果你的 MySQL 密码不是 `123456`，修改这里即可。

## 启动方式

1. 用 IDEA 打开 `D:\book-management-system`
2. 等待 Maven 导入依赖
3. 启动 `BookManagementSystemApplication`
4. 浏览器访问：

`http://localhost:8080/login`

## 默认登录账号

- 用户名：`admin`
- 密码：`123456`

## 角色说明

- 管理员：可新增、编辑、删除图书
- 管理员：可新增借阅、归还借阅、删除借阅记录
- 普通用户：可登录并查看图书列表与借阅记录
