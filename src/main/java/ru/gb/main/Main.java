package ru.gb.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.client.ConsoleClient;
import ru.gb.config.ConfigApp;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);
        ConsoleClient consoleClient = context.getBean(ConsoleClient.class);
        consoleClient.start();
    }
}
