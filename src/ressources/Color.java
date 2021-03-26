package ressources;

public enum Color {
    red('R'),
    blue('B'),
    green('G'),
    yellow('Y'),
    white('W'),
    purple('P');

    private final char symbol;

    Color(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

}
