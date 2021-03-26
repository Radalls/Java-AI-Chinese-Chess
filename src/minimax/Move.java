package minimax;

import ressources.Box;
import ressources.Pawn;

public class Move {
    private Pawn pawn;
    private Box target;

    public Move(Pawn pawn, Box target) {
        this.pawn = pawn;
        this.target = target;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public Box getTarget() {
        return target;
    }

    public void setTarget(Box target) {
        this.target = target;
    }
}
