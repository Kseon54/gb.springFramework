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
    private long iterator = 1;

    public ProductRepository() {
        this.products = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            products.add(new Product(iterator++, String.format("Product%d", i), Math.round(Math.random() * 1000)));
        }
    }

    public Optional<Product> findById(long id) {
        List<Product> collect = products.stream().filter(p -> p.getId() == id).limit(1).collect(Collectors.toList());
        if (collect.isEmpty()) return Optional.empty();
        return Optional.of(collect.get(0).copy());
    }

    public ArrayList<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        for (Product product : this.products) {
            products.add(product.copy());
        }
        return products;
    }

    public Optional<Product> save(Product product) {
        if (product.getId() != 0) {
            if (findById(product.getId()).isPresent()) {
                return Optional.empty();
            }
        }
        product.setId(iterator++);
        products.add(product);
        return Optional.of(product.copy());
    }

    public Optional<Product> update(long id, Product product) {
        if (product.getId() != 0 || findById(id).isEmpty()) {
            return Optional.empty();
        }
        if (id != product.getId())
            if (findById(product.getId()).isEmpty())
                return Optional.empty();

        Product oldProduct = getOriginalById(id).get(0);

        oldProduct.setId(product.getId());
        oldProduct.setTitle(product.getTitle());
        oldProduct.setCost(product.getCost());
        return Optional.of(oldProduct.copy());
    }

    public Optional<Product> delete(Product product) {
        if (findById(product.getId()).isPresent())
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == product.getId()) {
                    return Optional.of(products.remove(i));
                }
            }
        return Optional.empty();
    }

    private List<Product> getOriginalById(long id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .collect(Collectors.toList());
    }
}
