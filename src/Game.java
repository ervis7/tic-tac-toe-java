import java.util.Scanner;

public class Game {
    private static final char HUMAN_MARK = 'X';
    private static final char COMPUTER_MARK = 'O';

    private final Board board;
    private final AiStrategy aiStrategy;
    private final Scanner scanner;

    public Game(AiStrategy aiStrategy, Scanner scanner) {
        this.board = new Board();
        this.aiStrategy = aiStrategy;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println();
        System.out.println("Στρατηγική υπολογιστή: " + aiStrategy.getName());
        System.out.println("Εισάγετε κινήσεις ως αριθμούς γραμμών και στηλών, για παράδειγμα: 2 3");

        while (true) {
            board.print();
            Move humanMove = readHumanMove();
            board.placeMove(humanMove, HUMAN_MARK);

            if (board.isWinner(HUMAN_MARK)) {
                board.print();
                System.out.println("Κερδίσατε!");
                return;
            }

            if (board.isDraw()) {
                board.print();
                System.out.println("Ισοπαλία!");
                return;
            }

            Move computerMove = aiStrategy.chooseMove(board, COMPUTER_MARK, HUMAN_MARK);
            board.placeMove(computerMove, COMPUTER_MARK);
            System.out.println("Ο υπολογιστής έπαιξε: " + computerMove);

            if (board.isWinner(COMPUTER_MARK)) {
                board.print();
                System.out.println("Υπολογιστής κέρδισε!");
                return;
            }

            if (board.isDraw()) {
                board.print();
                System.out.println("Ισοπαλία!");
                return;
            }
        }
    }

    private Move readHumanMove() {
        while (true) {
            System.out.print("Η κίνησή σας (γραμμή στήλη): ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split("\\s+");

            if (parts.length != 2) {
                System.out.println("Παρακαλώ εισάγετε δύο αριθμούς, για παράδειγμα: 1 3");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                Move move = new Move(row, col);

                if (!board.isValidPosition(row, col)) {
                    System.out.println("Η γραμμή και η στήλη πρέπει να είναι μεταξύ 1 και 3.");
                } else if (!board.isCellEmpty(row, col)) {
                    System.out.println("Αυτό το κελί είναι ήδη κατειλημμένο.");
                } else {
                    return move;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Και οι δύο τιμές πρέπει να είναι αριθμοί.");
            }
        }
    }
}
