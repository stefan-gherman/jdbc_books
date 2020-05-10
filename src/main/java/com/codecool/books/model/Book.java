package com.codecool.books.model;

import lombok.*;

@RequiredArgsConstructor
public class Book {
    @Getter
    @Setter
    @NonNull
    private Author author;
    @Getter
    @Setter
    @NonNull
    private String title;

    @Setter
    @Getter
    private Integer id;

    @Override
    public String toString() {
        return "Book:" +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", author:" + author.getFirstName() + " " + author.getLastName();
    }
}
