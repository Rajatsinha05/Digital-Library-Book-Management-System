package com.Management.repository;

import com.Management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    Optional<Book> findByTitle(String title);
    @Query("SELECT b FROM Book b WHERE b.id = :id OR b.title = :title")
    Optional<Book> findByIdOrTitle(@Param("id") String id, @Param("title") String title);
}
