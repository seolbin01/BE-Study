package com.group.libraryapp.service.fruit;

import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.request.FruitUpdateRequest;
import com.group.libraryapp.dto.fruit.response.FruitStatResponse;
import com.group.libraryapp.repository.fruit.FruitRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestParam;

public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(JdbcTemplate jdbcTemplate) {
        this.fruitRepository = new FruitRepository(jdbcTemplate);
    }

    public void saveFruit(FruitCreateRequest request) {
        fruitRepository.saveFruit(request.getName(), request.getPrice(), request.getWarehousingDate());
    }

    public void updateFruit(long id) {
        if (fruitRepository.isFruitNotExist(id)) {
            throw new IllegalArgumentException();
        }

        fruitRepository.updateFruit(id);
    }

    public FruitStatResponse getFruitStat (String name) {
        if (fruitRepository.isFruitNameNotExist(name)) {
            throw new IllegalArgumentException();
        }

        return fruitRepository.getFruitStat(name);
    }
}
