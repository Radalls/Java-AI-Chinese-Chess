package ressources;

public class Pawn {
    private final Color color;
    private Box currentBox;
    private Box targetBox;
    private int distanceToTarget;

    public Pawn(Color color, Box currentBox) {
        this.color = color;
        this.currentBox = currentBox;
    }

    public Color getColor() { return color; }

    public Box getCurrentBox() {
        return currentBox;
    }

    public void setCurrentBox(Box currentBox) {
        this.currentBox = currentBox;
    }

    public Box getTargetBox() { return targetBox; }

    public void setTargetBox(Box targetBox) { this.targetBox = targetBox; }

    public int getDistanceToTarget() { return distanceToTarget; }

    public void setDistanceToTarget(int distanceToTarget) { this.distanceToTarget = distanceToTarget; }

}
