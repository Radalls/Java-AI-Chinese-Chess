package ressources;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Pawn> pawns;

    public Player(String name) {
        this.name = name;
        this.pawns = new ArrayList<>();
    }

    public boolean movePawn(Pawn pawnToPlay, Box target) {
        if (target.getSymbol() == 'o') {
            pawnToPlay.getCurrentBox().setSymbol('o');
            pawnToPlay.setCurrentBox(target);
            pawnToPlay.getCurrentBox().setSymbol(pawnToPlay.getColor().getSymbol());
            return true;
        }
        return false;
    }

    public void movePawnToNeighbourBox(Pawn pawn, Box neighbour) {
        if (pawn.getCurrentBox().getNeighbours().contains(neighbour)) {
            movePawn(pawn, neighbour);
        }
    }

    public boolean jumpOverPawn(Pawn toPlay, Pawn toJump) {
        if (toPlay.getCurrentBox().getNeighbours().contains(toJump.getCurrentBox())) {
            // NORTH
            if (toJump.getCurrentBox().getX() < toPlay.getCurrentBox().getX()) {
                return movePawn(toPlay, jumpOverNorth(toJump));
            }
            // SOUTH
            if (toJump.getCurrentBox().getX() > toPlay.getCurrentBox().getX()) {
                return movePawn(toPlay, jumpOverSouth(toJump));
            }
            // EAST
            if (toJump.getCurrentBox().getY() > toPlay.getCurrentBox().getY()) {
                return movePawn(toPlay, jumpOverEast(toJump));
            }
            // WEST
            if (toJump.getCurrentBox().getY() < toPlay.getCurrentBox().getY()) {
                return movePawn(toPlay, jumpOverWest(toJump));
            }
        } else {
            throw new IllegalArgumentException("ERROR : action impossible");
        }
        return false;
    }

    public Box jumpOverNorth(Pawn toJump) {
        for (Box c : toJump.getCurrentBox().getNeighbours()) {
            if (c.getX() < toJump.getCurrentBox().getX() && c.isEmpty()) {
                return c;
            }
        }
        return null;
    }

    public Box jumpOverSouth(Pawn toJump) {
        for (Box c : toJump.getCurrentBox().getNeighbours()) {
            if (c.getX() > toJump.getCurrentBox().getX() && c.isEmpty()) {
                return c;
            }
        }
        return null;
    }

    public Box jumpOverEast(Pawn toJump) {
        for (Box c : toJump.getCurrentBox().getNeighbours()) {
            if (c.getY() > toJump.getCurrentBox().getY() && c.isEmpty()) {
                return c;
            }
        }
        return null;
    }

    public Box jumpOverWest(Pawn toJump) {
        for (Box c : toJump.getCurrentBox().getNeighbours()) {
            if (c.getY() < toJump.getCurrentBox().getY() && c.isEmpty()) {
                return c;
            }
        }
        return null;
    }

    public boolean isWinning() {
        int nbOfCorrectPawns = 0;
        for (Pawn pawn : pawns) {
            if (pawn.getCurrentBox().equals(pawn.getTargetBox())) {
                nbOfCorrectPawns++;
            }
        }
        return nbOfCorrectPawns == pawns.size();
    }

    @Override
    public String toString() {
        StringBuilder sB = new StringBuilder();
        sB.append("----- ").append(name).append(" -----\n----- PAWNS -----\n");
        for (Pawn pawn : pawns) {
            sB.append("[")
                    .append(pawn.getColor().getSymbol())
                    .append("] | C:(")
                    .append(pawn.getCurrentBox().getX())
                    .append(" ")
                    .append(pawn.getCurrentBox().getY())
                    .append(") | O:(")
                    .append(pawn.getTargetBox().getX())
                    .append(" ")
                    .append(pawn.getTargetBox().getY())
                    .append(") | D:(")
                    .append(pawn.getDistanceToTarget())
                    .append(")\n");
        }
        return sB.toString();
    }

    public String getName() {
        return name;
    }

    public List<Pawn> getPawns() {
        return pawns;
    }

}
