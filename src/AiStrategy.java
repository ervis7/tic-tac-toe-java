public interface AiStrategy {
    Move chooseMove(Board board, char aiMark, char humanMark);

    String getName();
}
