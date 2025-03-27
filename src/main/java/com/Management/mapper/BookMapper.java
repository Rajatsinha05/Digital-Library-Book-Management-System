package com.Management.mapper;


import com.Management.dto.BookDTO;
import com.Management.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private final ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public BookDTO toDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book toEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }


    public List<BookDTO> toDTOList(List<Book> books) {
        return books.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public List<Book> toEntityList(List<BookDTO> bookDTOs) {
        return bookDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
