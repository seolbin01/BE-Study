package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.fruit.FruitRepository;
import com.group.libraryapp.dto.fruit.response.FruitStatResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@Primary
public class FruitMySqlRepository {
    private final JdbcTemplate jdbcTemplate;

    public FruitMySqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean isFruitNotExist(long id) {
        String readSql = "SELECT * FROM fruit WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }


    public boolean isFruitNameNotExist(String name) {
        String readSql = "SELECT * FROM fruit WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }


    public void saveFruit(String name, long price, LocalDate stocked_date) {
        String sql = "INSERT INTO fruit (name, price, stocked_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, name, price, stocked_date);
    }


    public void updateFruit(long id) {
        String sql = "UPDATE fruit SET is_sold = 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    public FruitStatResponse getFruitStat(String name) {
        String salesSql = "SELECT sum(price) FROM fruit WHERE is_sold = 1 AND name = ?";
        String notSalesSql = "SELECT sum(price) FROM fruit WHERE is_sold = 0 AND name = ?";

        Long salesAmount = jdbcTemplate.queryForObject(salesSql, Long.class, name);
        Long notSalesAmount = jdbcTemplate.queryForObject(notSalesSql, Long.class, name);

        return new FruitStatResponse(salesAmount, notSalesAmount);
    }
}
