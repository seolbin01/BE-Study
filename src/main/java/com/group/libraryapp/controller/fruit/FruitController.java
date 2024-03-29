package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitUpdateRequest;
import com.group.libraryapp.dto.fruit.response.FruitCountResponse;
import com.group.libraryapp.dto.fruit.response.FruitListResponse;
import com.group.libraryapp.dto.fruit.response.FruitStatResponse;
import com.group.libraryapp.service.fruit.FruitService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v1/fruit")
    public void saveFruit(@RequestBody FruitCreateRequest request) {
        fruitService.saveFruit(request);
    }

    @PutMapping("/api/v1/fruit")
    public void updateFruit(@RequestBody FruitUpdateRequest request) {
        fruitService.updateFruit(request.getId());
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitStatResponse fruitSoldStatus (@RequestParam String name) {

        return fruitService.getFruitStat(name);
    }

    @GetMapping("/api/v1/fruit/count")
    public FruitCountResponse getFruitCount(@RequestParam String name) {
        return fruitService.getFruitCount(name);
    }

    @GetMapping("/api/v1/fruit/list")
    public List<FruitListResponse> getFruitList(@RequestParam String option, int price) {
        return fruitService.getFruitList(option, price);
    }

}
