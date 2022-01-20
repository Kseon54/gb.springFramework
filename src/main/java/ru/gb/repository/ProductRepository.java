package ru.gb.repository;

import org.springframework.stereotype.Component;
import ru.gb.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepository {
    private final List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            products.add(new Product(i, String.format("Product%d", i), Math.round(Math.random() * 1000)));
        }
    }

    public Optional<Product> getById(long id){
        List<Product> collect = products.stream().filter(p -> p.getId() == id).limit(1).collect(Collectors.toList());
        if (collect.isEmpty()) return Optional.empty();
        return Optional.of(collect.get(0));
    }

    public List<Product> getAll(){
        return new ArrayList<>(products);
    }
}
