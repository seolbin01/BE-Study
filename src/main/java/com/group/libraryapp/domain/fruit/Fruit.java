package com.group.libraryapp.domain.fruit;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 20)
    private String name;

    private Integer price;
    private LocalDate stocked_date;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean sold;


    public Fruit(String name, Integer price, LocalDate stocked_date) {
        this.name = name;
        this.price = price;
        this.stocked_date = stocked_date;
    }

    public Fruit() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public LocalDate getStocked_date() {
        return stocked_date;
    }

    public boolean isSold() {
        return sold;
    }

    public void updateSold(Long id) {
        this.sold = true;
    }
}
