package com.group.libraryapp.dto.date.response;

public class DateResponse {
    private String dayOfTheWeek;

    public DateResponse(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
