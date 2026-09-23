public final class StrategyFactory {
    private StrategyFactory() {
    }

    public static AiStrategy createStrategy(int choice) {
        switch (choice) {
            case 1:
                return new RuleBasedStrategy();
            case 2:
                return new MinimaxStrategy();
            case 3:
                return new HeuristicStrategy();
            default:
                throw new IllegalArgumentException("Παρακαλώ εισάγετε έναν αριθμό από το 1 ως το 3.");
        }
    }
}
