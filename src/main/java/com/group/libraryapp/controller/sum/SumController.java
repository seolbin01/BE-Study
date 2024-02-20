package com.group.libraryapp.controller.sum;

import com.group.libraryapp.dto.sum.request.SumRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SumController {

    @PostMapping("/sum")
    public int sum(@RequestBody SumRequest request) {
        List<Integer> numbers = request.getNumbers();

        int sum = 0;
        for(int i=0;i<numbers.size();i++) {
            sum += numbers.get(i);
        }

        return sum;
    }
}
