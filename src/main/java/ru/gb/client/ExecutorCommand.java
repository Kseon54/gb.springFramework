package ru.gb.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.main.Cart;
import ru.gb.model.Product;
import ru.gb.repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ExecutorCommand {

    private static ProductRepository productRepository;

    @Autowired
    public ExecutorCommand(ProductRepository productRepository) {
        ExecutorCommand.productRepository = productRepository;
    }

    public static void printProduct() {
        List<Product> products = productRepository.getAll();
        for (Product product : products) {
            System.out.println(product.getInfo());
        }
    }

    public static void printCart(ConsoleClient consoleClient) {
        Map<Product, Integer> cart = consoleClient.getCart().getCart();
        if (cart.isEmpty()) System.out.println("Cart empty");
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            System.out.printf("id: %d %s\tcost: %.2f\tcount: %d\t allCost: %.2f\n",
                    entry.getKey().getId(),
                    entry.getKey().getTitle(),
                    entry.getKey().getCost(),
                    entry.getValue(),
                    entry.getKey().getCost() * entry.getValue()
            );
        }
    }

    public static void exit(ConsoleClient consoleClient) {
        consoleClient.exit(100);
    }

    public static void clear(ConsoleClient consoleClient, String[] strings) {
        if (strings.length == 1 || strings.length == 2) {
            if (strings.length == 1) consoleClient.getCart().clear();
            if (strings.length == 2) {
                try {
                    long idProduct = Long.parseLong(strings[1]);

                    Optional<Product> product = productRepository.getById(idProduct);
                    if (product.isPresent()) consoleClient.getCart().clearProduct(product.get());
                    else System.out.println("The product with this id was not found");
                } catch (NumberFormatException e) {
                    printExceptionInvalidCommand(strings[0]);
                }
            }
        } else {
            printExceptionInvalidCommand(strings[0]);
        }
    }

    public static void reduceProductCart(ConsoleClient consoleClient, String[] strings) {
        parsDataChangingCount(consoleClient, strings, -1);
    }

    public static void addProductCart(ConsoleClient consoleClient, String[] strings) {
        parsDataChangingCount(consoleClient, strings, 1);
    }

    private static void parsDataChangingCount(ConsoleClient consoleClient, String[] strings, int sign) {
        if (strings.length == 2 || strings.length == 3) {
            try {
                long idProduct = Long.parseLong(strings[1]);
                int count;

                if (strings.length == 3) count = Integer.parseInt(strings[2]) * sign;
                else count = sign;

                changingQuantityOfProduct(consoleClient, idProduct, count);
            } catch (NumberFormatException e) {
                printExceptionInvalidCommand(strings[0]);
            }
        } else {
            printExceptionInvalidCommand(strings[0]);
        }
    }

    public static void changingQuantityOfProduct(ConsoleClient consoleClient, long idProduct, int count) {
        Optional<Product> product = productRepository.getById(idProduct);
        if (product.isPresent()) consoleClient.getCart().changingQuantityOfProduct(product.get(), count);
        else System.out.println("The product with this id was not found");
    }

    private static void printExceptionInvalidCommand(String titleCommand) {
        ConsoleCommands.getByTitle(titleCommand).ifPresent(commands ->
                System.out.printf("Invalid command.\nFormat command: %s", commands.getFormatCommand())
        );
    }
}
