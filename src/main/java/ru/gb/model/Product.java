package ru.gb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private long id;
    private String title;
    private double cost;

    public String getInfo() {
        return String.format("id: %d\t%s\tcost: %.2f", id, title, cost);
    }
}
