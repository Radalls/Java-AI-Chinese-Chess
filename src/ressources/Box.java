package ressources;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private char symbol;
    private final int x;
    private final int y;
    private final List<Box> neighbours;

    public Box(char symbol, int x, int y) {
        this.symbol = symbol;
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();
    }

    public boolean isEmpty() {
        return symbol == 'o';
    }

    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append("----- BOX -----\n");
        sB.append("[").append(symbol).append(" | ").append(x).append(" | ").append(y).append("]\n");
        sB.append("----- NEIGHBOURS -----\n");
        for (Box c : neighbours) {
            sB.append(c.getSymbol()).append(" ");
        }
        return sB.toString();
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) { this.symbol = symbol; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Box> getNeighbours() {
        return neighbours;
    }

}
