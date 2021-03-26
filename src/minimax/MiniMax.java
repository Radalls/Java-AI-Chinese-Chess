package minimax;

import java.util.List;

public class MiniMax {
    private Tree tree;

    public void constructTree(int distanceToTarget){
        tree = new Tree();
        Node root = new Node(distanceToTarget, true);
        tree.setRoot(root);
        constructTree(root);
    }

    public void constructTree(Node parentNode){
        List<Move> listOfPossibleMoves;
    }
}
