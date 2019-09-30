package console;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Scanner;

@Service
public class ConsoleImpl implements Console {
    private final Scanner scanner;

    public ConsoleImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void print(String information) {
        if (Objects.nonNull(information)) {
            System.out.print(information);
        }
    }
}
