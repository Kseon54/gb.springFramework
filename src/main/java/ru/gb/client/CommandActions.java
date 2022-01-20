package ru.gb.client;

@FunctionalInterface
public interface CommandActions {

    void perform(ConsoleClient consoleClient, String[] strings);
}
