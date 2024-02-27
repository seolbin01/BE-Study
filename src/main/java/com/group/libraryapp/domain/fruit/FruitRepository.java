package com.group.libraryapp.domain.fruit;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

//    Optional<Fruit> findByName(String name);

    List<Fruit> findAllByNameAndSold(String name, boolean sold);

    long countByName(String name);

    List<Fruit> findAllByPriceGreaterThanEqualAndSold(int price, boolean sold);

    List<Fruit> findAllByPriceLessThanEqualAndSold(int price, boolean sold);
}
