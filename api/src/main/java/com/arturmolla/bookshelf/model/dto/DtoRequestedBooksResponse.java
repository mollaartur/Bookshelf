package com.arturmolla.bookshelf.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoRequestedBooksResponse {

    private Long id;
    private String title;
    private String authorName;
    private String isbn;
    private String requesterName;
    private Double rate;
    private Boolean requested;
    private Boolean requestApproved;
    private byte[] cover;
    private String coverUrl;
}
