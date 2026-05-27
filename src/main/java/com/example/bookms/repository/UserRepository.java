package com.example.bookms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bookms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findByRoleOrderByIdDesc(String role);

    @Query("""
        select u
        from User u
        where u.role = :role
          and (
            :keyword is null
            or lower(u.username) like lower(concat('%', :keyword, '%'))
            or lower(u.nickname) like lower(concat('%', :keyword, '%'))
            or lower(coalesce(u.email, '')) like lower(concat('%', :keyword, '%'))
          )
        order by u.id desc
        """)
    List<User> searchByRoleAndKeyword(@Param("role") String role, @Param("keyword") String keyword);
}
