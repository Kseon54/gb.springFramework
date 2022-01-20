package ru.gb.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Scope("prototype")
public enum ConsoleCommands {

    EXIT("exit", "exit", (consoleClient,strings) -> ExecutorCommand.exit(consoleClient)),
    ADD("add", "add (idProduct) [count]", ExecutorCommand::addProductCart),
    CLEAR("clear", "clear [idProduct]", ExecutorCommand::clear),
    REDUCE("reduce", "reduce (idProduct) [count]", ExecutorCommand::reduceProductCart),
    PRINT_PRODUCT("printProduct", "printProduct", (consoleClient,strings) -> ExecutorCommand.printProduct()),
    PRINT_CART("printCart", "printCart", (consoleClient,strings) -> ExecutorCommand.printCart(consoleClient));

    private final String commandTitle;
    private final String formatCommand;
    private final CommandActions commandActions;

    public static Optional<ConsoleCommands> getByTitle(String title) {
        List<ConsoleCommands> collect = Arrays.stream(ConsoleCommands.values())
                .filter(command -> command.getCommandTitle().equals(title))
                .collect(Collectors.toList());
        if (collect.isEmpty()) return Optional.empty();
        return Optional.of(collect.get(0));
    }

    public void perform(ConsoleClient consoleClient, String[] strings) {
        commandActions.perform(consoleClient, strings);
    }
}
