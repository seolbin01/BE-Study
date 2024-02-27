package com.group.libraryapp.dto.fruit.response;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.domain.user.User;

import java.time.LocalDate;

public class FruitListResponse {

    private String name;
    private Integer price;
    private LocalDate warehousingDate;

    public FruitListResponse(Fruit fruit) {
        this.name = fruit.getName();
        this.price = fruit.getPrice();
        this.warehousingDate = fruit.getStocked_date();
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }
}
