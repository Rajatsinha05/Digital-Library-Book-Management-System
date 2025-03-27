package com.Management.dto;

import com.Management.enums.AvailabilityStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private String id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @NotBlank(message = "Genre is required")
    private String genre;
    private AvailabilityStatus availability;
}
