package com.group.libraryapp.controller.fruit;

import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitUpdateRequest;
import com.group.libraryapp.dto.fruit.response.FruitStatResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class FruitController {

    private final JdbcTemplate jdbcTemplate;

    public FruitController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/api/v1/fruit")
    public void saveFruit(@RequestBody FruitCreateRequest request) {
        String sql = "INSERT INTO fruit (name, price, stocked_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getPrice(), request.getWarehousingDate());
    }

    @PutMapping("/api/v1/fruit")
    public void updateFruit(@RequestBody FruitUpdateRequest request) {
        String readSql = "SELECT * FROM fruit WHERE id = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        String sql = "UPDATE fruit SET is_sold = 1 WHERE id = ?";
        jdbcTemplate.update(sql, request.getId());
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitStatResponse fruitSoldStatus (@RequestParam String name) {
        String readSql = "SELECT * FROM fruit WHERE name = ?";
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
        if (isUserNotExist) {
            throw new IllegalArgumentException("");
        }

        String salesSql = "SELECT sum(price) FROM fruit WHERE is_sold = 1";
        String notSalesSql = "SELECT sum(price) FROM fruit WHERE is_sold = 0";

        Long salesAmount = jdbcTemplate.queryForObject(salesSql, Long.class);
        Long notSalesAmount = jdbcTemplate.queryForObject(notSalesSql, Long.class);

        return new FruitStatResponse(salesAmount, notSalesAmount);
    }
}
