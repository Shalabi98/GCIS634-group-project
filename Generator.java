import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Random;

public class Generator {
    private Alphabet alphabet;
    private final Scanner keyboard;
    private static final String QUIT_OPTION = "4";
    private final Map<String, Runnable> actions;

    public Generator(Scanner scanner) {
        this.keyboard = scanner;
        this.actions = initAction();
    }

    public Generator(boolean includeUpper, boolean includeLower, boolean includeNumber, boolean includeSymbol) {
        alphabet = new Alphabet(includeUpper, includeLower, includeNumber, includeSymbol);
        this.keyboard = new Scanner(System.in);
        this.actions = initAction();
    }

    private Map<String, Runnable> initAction() {
        Map<String, Runnable> actions = new LinkedHashMap<>();
        actions.put("1", this::requestPassword);
        actions.put("2", this::checkPassword);
        actions.put("3", this::printUsefulInfo);
        actions.put(QUIT_OPTION, this::printQuitMessage);
        return actions;
    }

    private String getUserInput() {
        while (true) {
            try {
                return keyboard.next();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid value.");
                keyboard.next();
            }
        }
    }

    public void mainLoop() {
        System.out.println("Welcome to Ziz Password Services :)");
        printMenu();

        String userOption = "";

        while (!userOption.equals(QUIT_OPTION)) {
            try {
                userOption = getUserInput();
            } catch (java.util.NoSuchElementException e) {
                System.out.println("No more input. Exiting program.");
                break;
            }

            actions.getOrDefault(userOption, this::handleInvalidOption).run();
            printMenu();
        }
    }

    private LinkedHashMap<String, Boolean> initPasswordOptions() {
        LinkedHashMap<String, Boolean> passwordOptions = new LinkedHashMap<>();
        passwordOptions.put("Lowercase letters \"abcd...\"", false);
        passwordOptions.put("Uppercase letters \"ABCD...\"", false);
        passwordOptions.put("Numbers \"1234...\"", false);
        passwordOptions.put("Symbols \"!@#$...\"", false);
        return passwordOptions;
    }

    void requestPassword() {
        try {
            LinkedHashMap<String, Boolean> passwordOptions = initPasswordOptions();

            System.out.println("\nHello, welcome to the Password Generator :) Answer the following questions by y or n\n");

            for (Map.Entry<String, Boolean> entry : passwordOptions.entrySet()) {
                String input;
                do {
                    System.out.println("Do you want " + entry.getKey() + " to be used? ");
                    input = getUserInput();
                    passwordRequestError(input);
                } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));
                entry.setValue(isInclude(input));
            }

            if (passwordOptions.values().stream().noneMatch(Boolean::booleanValue)) {
                System.out.println("You have selected no characters to generate your password. At least one of your answers should be y\n");
                return;
            }

            int length;
            do {
                try {
                    System.out.println("Great! Now enter the length of the password");
                    length = keyboard.nextInt();
                    if (length <= 0) {
                        throw new IllegalArgumentException("Password length should be greater than 0.");
                    }
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    keyboard.next(); // consume the invalid input
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } while (true);

            final Generator generator = new Generator(
                    passwordOptions.get("Uppercase letters \"ABCD...\""),
                    passwordOptions.get("Lowercase letters \"abcd...\""),
                    passwordOptions.get("Numbers \"1234...\""),
                    passwordOptions.get("Symbols \"!@#$...\"")
            );
            final Password password = generator.generatePassword(length);

            System.err.println("Your generated password -> " + password);
        } catch (IllegalStateException e) {
            System.out.println("Error reading input: " + e.getMessage());
        }
    }

    private Password generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);
        Random random = new Random();

        int alphabetLength = alphabet.getAlphabet().length();

        try {
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(alphabetLength);
                password.append(alphabet.getAlphabet().charAt(index));
            }
        } catch (Exception e) {
            System.out.println("Error generating password: " + e.getMessage());
        }

        return new Password(password.toString());
    }

    private void printUsefulInfo() {
        System.out.println(
                """
                	
                	Use a minimum password length of 8 or more characters if permitted
                	Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted
                	Generate passwords randomly where feasible
                	Avoid using the same password twice (e.g., across multiple user accounts and/or software systems)
                	Avoid character repetition, keyboard patterns, dictionary words, letter or number sequences,
                	usernames, relative or pet names, romantic links (current or past)\s\
                	and biographical information (e.g., ID numbers, ancestors' names or dates).
                	Avoid using information that the user's colleagues and/or\s\
                	acquaintances might know to be associated with the user
                	Do not use passwords which consist wholly of any simple combination of the aforementioned weak components
                    """
        );
    }

    void checkPassword() {
        String input;

        System.out.print("\nEnter your password:");
        input = getUserInput();

        final Password p = new Password(input);

        System.out.println(p.calculateScore());
    }

    private void passwordRequestError(String input) {
        if (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
            System.out.println("You have entered something incorrect let's go over it again \n");
        }
    }

    private boolean isInclude(String input) {
        return input.equalsIgnoreCase("y");
    }

    private void printMenu() {
        System.out.println("\n1 - Password Generator");
        System.out.println("2 - Password Strength Check");
        System.out.println("3 - Useful Information");
        System.out.println("4 - Quit");
        System.out.print("Choice:");
    }

    private void printQuitMessage() {
        System.out.println("Closing the program bye bye!");
    }

    private void handleInvalidOption() {
        System.out.println("\nKindly select one of the available commands");
    }
}

