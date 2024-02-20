package com.group.libraryapp.dto.date.request;

import java.time.LocalDate;

public class DateRequest {

    private LocalDate date;

    public DateRequest(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
