package com.arturmolla.bookshelf.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DtoBookRequest(
        @NotNull(message = "100")
        @NotEmpty(message = "100")
        String title,
        @NotNull(message = "101")
        @NotEmpty(message = "101")
        String authorName,
        @NotNull(message = "102")
        @NotEmpty(message = "102")
        String isbn,
        String synopsis,
        String genre,
        String cover,
        String coverUrl,
        Integer pageBookmark,
        boolean favourite,
        boolean archived,
        boolean shareable,
        boolean read
) {
}
