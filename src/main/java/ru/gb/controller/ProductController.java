package ru.gb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.client.ExecutorCommand;
import ru.gb.model.Product;
import ru.gb.service.ProductService;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/add")
    public String getAllProduct(Model model) {
        ArrayList<Product> list = productService.findAll();
        model.addAttribute("products", list);
        return "product/product";
    }

    @GetMapping("/{id}")
    public String getProductById(Model model, @PathVariable Long id) {
        ArrayList<Product> list = new ArrayList<>();
        list.add(productService.findById(id));
        model.addAttribute("products", list);
        return "product/product";
    }

    @GetMapping
    public String openFormAddProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product/addProduct";
    }

    @PostMapping("/add")
    public String addProduct(Model model) {
        System.out.println("-------------------------------------------------------------------------------");
        Product product = new Product(
                (String) model.getAttribute("title"),
                Double.parseDouble(model.getAttribute("cost").toString()));
        productService.save(product);
        ExecutorCommand.printProduct();
        return "product/product";
    }

}
