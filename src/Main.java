import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Τρίλιζα χρησιμοποιώντας διάφορες στρατηγικές");
        System.out.println("Χρήστης: X, Υπολογιστής: O");

        do {
            AiStrategy strategy = askForStrategy();
            Game game = new Game(strategy, SCANNER);
            game.start();
        } while (askToPlayAgain());

        System.out.println("Ευχαριστούμε που παίξατε!");
    }

    private static AiStrategy askForStrategy() {
        while (true) {
            System.out.println();
            System.out.println("Διαλέξτε την στρατηγική του υπολογιστή :");
            System.out.println("1. Rule-based");
            System.out.println("2. Minimax");
            System.out.println("3. Heuristic");
            System.out.print("Επιλογή (1-3): ");

            String input = SCANNER.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                return StrategyFactory.createStrategy(choice);
            } catch (NumberFormatException ex) {
                System.out.println("Παρακαλώ εισάγετε έναν αριθμό από το 1 ως το 3.");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

   private static boolean askToPlayAgain() {
        while (true) {
            System.out.print("Θέλετε να παίξετε ξανά? (y/n): ");
            String input = SCANNER.nextLine().trim().toLowerCase();

            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }

            System.out.println("Παρακαλώ απαντήστε y ή n.");
        }
    }

}
