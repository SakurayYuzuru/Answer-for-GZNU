package com.example.bookms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bookms.entity.BorrowRecord;

public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {

    @EntityGraph(attributePaths = "book")
    Page<BorrowRecord> findAllByOrderByIdDesc(Pageable pageable);

    @EntityGraph(attributePaths = "book")
    Page<BorrowRecord> findByStatusOrderByIdDesc(String status, Pageable pageable);

    @EntityGraph(attributePaths = "book")
    @Query("""
        select r
        from BorrowRecord r
        join r.book b
        where (:status is null or r.status = :status)
          and (
            :keyword is null
            or lower(r.borrowerName) like lower(concat('%', :keyword, '%'))
            or lower(b.title) like lower(concat('%', :keyword, '%'))
            or lower(b.isbn) like lower(concat('%', :keyword, '%'))
          )
        order by r.id desc
        """)
    Page<BorrowRecord> search(@Param("status") String status,
                              @Param("keyword") String keyword,
                              Pageable pageable);
}
