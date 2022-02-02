package ru.gb.model;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long id;
    private String title;
    private double cost;

    public Product(String title, double cost) {
        this.title = title;
        this.cost = cost;
    }

    public String getInfo() {
        return String.format("id: %d\t%s\tcost: %.2f", id, title, cost);
    }

    public Product copy() {
        return Product.builder()
                .id(getId())
                .title(getTitle())
                .cost(getCost())
                .build();
    }
}
