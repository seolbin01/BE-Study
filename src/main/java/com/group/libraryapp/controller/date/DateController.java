package com.group.libraryapp.controller.date;

import com.group.libraryapp.dto.date.request.DateRequest;
import com.group.libraryapp.dto.date.response.DateResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;

@RestController
public class DateController {
    @GetMapping("/api/v1/day-of-the-week")
    public DateResponse getdayoftheweek(DateRequest requset){
        LocalDate date = requset.getDate();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return new DateResponse(dayOfWeek.toString());
    }
}
