import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean includeUpper, boolean includeLower, boolean includeNumber, boolean includeSymbol) {
        alphabet = new Alphabet(includeUpper, includeLower, includeNumber, includeSymbol);
    }

    public void mainLoop() {
        System.out.println("Welcome to Ziz Password Services :)");
        printMenu();

        String userOption = "-1";

        while (!userOption.equals("4")) {

            userOption = keyboard.next();

            switch (userOption) {
                case "1" -> {
                    requestPassword();
                    printMenu();
                }
                case "2" -> {
                    checkPassword();
                    printMenu();
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                }
                case "4" -> printQuitMessage();
                default -> {
                    System.out.println();
                    System.out.println("Kindly select one of the available commands");
                    printMenu();
                }
            }
        }
    }

    private Password generatePassword(int length) {
        final StringBuilder password = new StringBuilder("");

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            password.append(alphabet.getAlphabet().charAt(index));
        }

        return new Password(password.toString());
    }

    private void printUsefulInfo() {
        System.out.println();
        System.out.println("Use a minimum password length of 8 or more characters if permitted");
        System.out.println("Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted");
        System.out.println("Generate passwords randomly where feasible");
        System.out.println("Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)");
        System.out.println("Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences," +
                "\nusernames, relative or pet names, romantic links (current or past) " +
                "and biographical information (e.g., ID numbers, ancestors' names or dates).");
        System.out.println("Avoid using information that the user's colleagues and/or " +
                "acquaintances might know to be associated with the user");
        System.out.println("Do not use passwords which consist wholly of any simple combination of the aforementioned weak components");
    }

    private void requestPassword() {
        LinkedHashMap<String, Boolean> passwordOptions = new LinkedHashMap<>() {{
            put("Lowercase letters \"abcd...\"", false);
            put("Uppercase letters \"ABCD...\"", false);
            put("Numbers \"1234...\"", false);
            put("Symbols \"!@#$...\"", false);
        }};

        System.out.println("\nHello, welcome to the Password Generator :) Answer the following questions by y or n\n");

        for (Map.Entry<String, Boolean> entry : passwordOptions.entrySet()) {
            String input;
            do {
                System.out.println("Do you want " + entry.getKey() + " to be used? ");
                input = keyboard.next();
                passwordRequestError(input);
            } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
            entry.setValue(isInclude(input));
        }
      
        if (passwordOptions.values().stream().noneMatch(Boolean::booleanValue)) {
            System.out.println("You have selected no characters to generate your password. At least one of your answers should be y\n");
            return;
        }

        System.out.println("Great! Now enter the length of the password");
        int length = keyboard.nextInt();

        final Generator generator = new Generator(
                passwordOptions.get("Uppercase letters \"ABCD...\""),
                passwordOptions.get("Lowercase letters \"abcd...\""),
                passwordOptions.get("Numbers \"1234...\""),
                passwordOptions.get("Symbols \"!@#$...\"")
        );
        final Password password = generator.generatePassword(length);

        System.err.println("Your generated password -> " + password);
    }

    private boolean isInclude(String input) {
        return input.equalsIgnoreCase("y");
    }

    private void passwordRequestError(String input) {
        if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private void checkPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = keyboard.next();

        final Password p = new Password(input);

        System.out.println(p.calculateScore());
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Enter 1 - Password Generator");
        System.out.println("Enter 2 - Password Strength Check");
        System.out.println("Enter 3 - Useful Information");
        System.out.println("Enter 4 - Quit");
        System.out.print("Choice:");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }
}
