package com.Management.service;

import com.Management.dto.BookDTO;
import com.Management.entity.Book;
import com.Management.enums.AvailabilityStatus;
import com.Management.exception.BookNotFoundException;
import com.Management.mapper.BookMapper;
import com.Management.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book.setAvailability(AvailabilityStatus.AVAILABLE);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookMapper.toDTOList(bookRepository.findAll());
    }

    @Override
    public BookDTO getBookById(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO getBookByIdOrTitle(String id, String title) {
        Book book = bookRepository.findByIdOrTitle(id, title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID or Title: " + id + " / " + title));
        return bookMapper.toDTO(book);
    }


    @Override
    @Transactional
    public BookDTO updateBook(String id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + id));

        if (bookDTO.getTitle() != null && !bookDTO.getTitle().isBlank())
            book.setTitle(bookDTO.getTitle());

        if (bookDTO.getAuthor() != null && !bookDTO.getAuthor().isBlank())
            book.setAuthor(bookDTO.getAuthor());

        if (bookDTO.getGenre() != null && !bookDTO.getGenre().isBlank())
            book.setGenre(bookDTO.getGenre());

        if (bookDTO.getAvailability() != null)
            book.setAvailability(bookDTO.getAvailability());

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public void deleteBook(String id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
        bookRepository.deleteById(id);
    }
}
