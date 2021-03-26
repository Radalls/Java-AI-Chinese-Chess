package ressources;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int LINES = 8;
    private static final int COLUMNS = 12;
    private final List<Pawn> pawns;
    private final List<Box> boxes;
    private final char[][] boardDisplay;

    public Board() {
        this.pawns = new ArrayList<>();
        this.boxes = new ArrayList<>();
        this.boardDisplay = new char[LINES+1][COLUMNS+1];
    }

    public void initNeighborsOfBoxes(Box toInit) {
        for (Box box : boxes) {
            if ((box.getX() == toInit.getX() - 1 && box.getY() == toInit.getY()
                    || (box.getX() == toInit.getX() + 1 && box.getY() == toInit.getY())
                    || (box.getX() == toInit.getX() && box.getY() == toInit.getY() - 1)
                    || (box.getX() == toInit.getX() && box.getY() == toInit.getY() + 1))) {
                toInit.getNeighbours().add(box);
            }
        }
    }

    public void initNeighborsOfBoxes() {
        for (Box box : boxes) {
            initNeighborsOfBoxes(box);
        }
    }

    public void initPawn(Box box) {
        switch(box.getSymbol()) {
            case 'R':
                pawns.add(new Pawn(Color.red, box));
                break;
            case 'G':
                pawns.add(new Pawn(Color.green, box));
                break;
            case 'B':
                pawns.add(new Pawn(Color.blue, box));
                break;
            case 'Y':
                pawns.add(new Pawn(Color.yellow, box));
                break;
            case 'W':
                pawns.add(new Pawn(Color.white, box));
                break;
            case 'P':
                pawns.add(new Pawn(Color.purple, box));
                break;
            default:
                break;
        }
    }

    public void initPawns() {
        for (Box box : boxes) {
            initPawn(box);
        }
    }

    public void initPlayer(Player toInit, Color colorChosen) {
        for (Pawn pawn : pawns) {
            if (pawn.getColor().equals(colorChosen)) {
                toInit.getPawns().add(pawn);
            }
        }
    }

    public Box getBoxByCoordinates(int x, int y) {
        for (Box box : boxes) {
            if (box.getX() == x && box.getY() == y) {
                return box;
            }
        }
        return null;
    }

    public void initPlayerTargetBoxes(Player player) {
        for (Pawn pawn : player.getPawns()) {
            pawn.setTargetBox(getBoxByCoordinates(LINES - pawn.getCurrentBox().getX(), pawn.getCurrentBox().getY()));
            pawn.setDistanceToTarget(pawn.getTargetBox().getX() - pawn.getCurrentBox().getX());
        }
    }

    public void updateDisplay() {
        for (Box c : boxes) {
            boardDisplay[c.getX()][c.getY()] = c.getSymbol();
        }
    }

    public String showDisplay() {
        updateDisplay();
        StringBuilder s = new StringBuilder("----- BOARD -----\n");
        for (int i = 0; i < LINES + 1; i++) {
            for (int j = 0; j < COLUMNS + 1; j++) {
                s.append(boardDisplay[i][j]);
            }
            s.append("\n");
        }
        return s.toString();
    }

    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append("----- BOXES -----\n");
        for (Box c : boxes) {
            sB.append("[")
                    .append(c.getSymbol())
                    .append(" | ")
                    .append(c.getX())
                    .append(" | ")
                    .append(c.getY())
                    .append("]\n");
        }
        sB.append("----- PAWNS -----\n");
        for (Pawn pawn : pawns) {
            sB.append("[")
                    .append(pawn.getColor().getSymbol())
                    .append("]\n");
        }
        return sB.toString();
    }

    public List<Box> getBoxes() { return boxes; }

}
