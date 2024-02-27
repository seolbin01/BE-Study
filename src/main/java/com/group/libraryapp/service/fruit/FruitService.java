package com.group.libraryapp.service.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import com.group.libraryapp.dto.fruit.request.FruitCreateRequest;
import com.group.libraryapp.dto.fruit.response.FruitCountResponse;
import com.group.libraryapp.dto.fruit.response.FruitListResponse;
import com.group.libraryapp.dto.fruit.response.FruitStatResponse;
import com.group.libraryapp.domain.fruit.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void saveFruit(FruitCreateRequest request) {
        fruitRepository.save(new Fruit(request.getName(), request.getPrice(),request.getWarehousingDate()));
    }

    public void updateFruit(long id) {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        fruit.updateSold(id);
        fruitRepository.save(fruit);
    }

    public FruitStatResponse getFruitStat (String name) {

        List<Fruit> fruitsSales = fruitRepository.findAllByNameAndSold(name, true);
        List<Fruit> fruitsNotSales = fruitRepository.findAllByNameAndSold(name, false);

        long salesAmount = 0;
        for (Fruit oneOfFruit : fruitsSales) {
            salesAmount += oneOfFruit.getPrice();
        }
        long notSalesAmount = 0;
        for (Fruit oneOfFruit : fruitsNotSales) {
            notSalesAmount += oneOfFruit.getPrice();
        }

        return new FruitStatResponse(salesAmount, notSalesAmount);
    }

    public FruitCountResponse getFruitCount(String name) {
        long count = fruitRepository.countByName(name);
        return new FruitCountResponse(count);
    }

    public List<FruitListResponse> getFruitList(String option, int price) {
        List<Fruit> fruits;
        if(option.equals("GTE")){
            fruits = fruitRepository.findAllByPriceGreaterThanEqualAndSold(price, false);
        }
        else if(option.equals("LTE")){
            fruits = fruitRepository.findAllByPriceLessThanEqualAndSold(price, false);
        }
        else throw new IllegalArgumentException();

        return fruits.stream()
                .map(FruitListResponse::new)
                .collect(Collectors.toList());
    }
}
