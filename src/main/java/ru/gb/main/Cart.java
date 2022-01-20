package ru.gb.main;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.gb.model.Product;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class Cart {
    Map<Product, Integer> cart;

    public Cart() {
        cart = new HashMap<>();
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public void changingQuantityOfProduct(Product product, int count) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + count);
        } else {
            cart.put(product, count);
        }
        if (cart.get(product) <= 0) clearProduct(product);
    }

    public void clear() {
        cart.clear();
    }

    public void clearProduct(Product product) {
        cart.remove(product);
    }

}
