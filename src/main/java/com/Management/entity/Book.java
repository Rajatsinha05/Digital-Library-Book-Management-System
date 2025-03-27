package com.Management.entity;


import com.Management.enums.AvailabilityStatus;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;

    @NotBlank(message = "Genre is required")
    @Column(nullable = false)
    private String genre;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Availability status is required")
    private AvailabilityStatus availability;


    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = generateULID();
        }
    }

    public static String generateULID() {
        return new ULID().nextULID();
    }
}
