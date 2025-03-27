package com.Management.service;

import com.Management.dto.BookDTO;

import java.util.List;

public interface IBookService {
    BookDTO addBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(String id);
    BookDTO getBookByIdOrTitle(String id, String title);
    BookDTO updateBook(String id, BookDTO bookDTO);
    void deleteBook(String id);
}
