package com.arturmolla.bookshelf.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoBorrowedBooksResponse {

    private Long id;
    private String title;
    private String authorName;
    private String isbn;
    private String ownerName;
    private Double rate;
    private Boolean returned;
    private Boolean returnApproved;
}
