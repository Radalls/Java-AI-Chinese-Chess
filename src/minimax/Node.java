package minimax;

import java.util.List;

public class Node {
    private int distanceToTarget;
    boolean isMaxPlayer;
    int score;
    List<Node> children;

    public Node(int distanceToTarget, boolean isMaxPlayer){
        this.distanceToTarget = distanceToTarget;
        this.isMaxPlayer = isMaxPlayer;
    }

    public int getDistanceToTarget() { return distanceToTarget; }

    public void setDistanceToTarget(int distanceToTarget) { this.distanceToTarget = distanceToTarget; }

    public boolean isMaxPlayer() { return isMaxPlayer; }

    public void setMaxPlayer(boolean maxPlayer) { isMaxPlayer = maxPlayer; }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }

    public List<Node> getChildren() { return children; }

    public void setChildren(List<Node> children) { this.children = children; }

}
