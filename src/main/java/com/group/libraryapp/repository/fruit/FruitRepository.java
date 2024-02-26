package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.dto.fruit.response.FruitStatResponse;

import java.time.LocalDate;

public interface FruitRepository {
    boolean isFruitNotExist(long id);
    boolean isFruitNameNotExist(String name);
    void saveFruit(String name, long price, LocalDate stocked_date);
    void updateFruit(long id);
    FruitStatResponse getFruitStat(String name);
}
