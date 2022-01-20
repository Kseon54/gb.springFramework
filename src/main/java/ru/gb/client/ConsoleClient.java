package ru.gb.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.gb.main.Cart;
import ru.gb.repository.ProductRepository;

import java.util.Optional;
import java.util.Scanner;

@Setter
@Getter
@Component
@RequiredArgsConstructor
@Scope("prototype")
public class ConsoleClient {

    private Scanner scanner;
    private Cart cart;

    @Autowired
    public ConsoleClient(Cart cart) {
        scanner = new Scanner(System.in);
        this.cart = cart;
    }

    public void start() {
        ExecutorCommand.printProduct();
        read();
    }

    private void read() {
        String text = scanner.nextLine().trim();
        parsCommand(text);
    }

    private void parsCommand(String text) {
        if (text.equals("")) return;
        String[] split = text.split(" ");
        Optional<ConsoleCommands> byTitle = ConsoleCommands.getByTitle(split[0]);
        if (byTitle.isPresent()) {
            byTitle.get().perform(this, split);
        } else {
            System.out.println("Command not found");
        }
        read();
    }

    public void exit(int exit) {
        System.exit(exit);
    }

}
