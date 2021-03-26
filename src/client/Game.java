package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ressources.*;

public class Game {
    private static Board board;

    ///

    public static void main(String[] args) {
        board = new Board();
        Player P1 = new Player("Mario");
        Player P2 = new Player("Luigi");
        initBoxes();
        board.initNeighborsOfBoxes();
        board.initPawns();
        initPlayer(P1);
        initPlayer(P2);
        board.initPlayerTargetBoxes(P1);
        board.initPlayerTargetBoxes(P2);
        System.out.println(P1);
        while(!P1.isWinning() && !P2.isWinning()) {
            System.out.println(board.showDisplay());
            chooseAction(P1);
            if (!P1.isWinning() && !P2.isWinning()) {
                System.out.println(board.showDisplay());
                chooseAction(P2);
            }
        }
        if (P1.isWinning()){
            System.out.println(P2.getName() + " WINS");
        }
        else if (P2.isWinning()){
            System.out.println(P2.getName() + " WINS");
        }
        System.out.println("Game Over");
    }

    public static void initBoxes() {
        File file = new File("board.txt");
        Scanner s;
        try {
            s = new Scanner(file);
            int i = 0;
            while (s.hasNext()) {
                String line = s.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    board.getBoxes().add(new Box(line.charAt(j), i, j));
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void initPlayer(Player toInit) {
        Scanner sc = new Scanner(System.in);
        String choix;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(toInit.getName() + " choose a color ->");
            choix = sc.next().trim().toUpperCase();
            switch (choix) {
                case "RED" -> {
                    board.initPlayer(toInit, Color.red);
                    isValid = true;
                }
                case "GREEN" -> {
                    board.initPlayer(toInit, Color.green);
                    isValid = true;
                }
                case "BLUE" -> {
                    board.initPlayer(toInit, Color.blue);
                    isValid = true;
                }
                case "YELLOW" -> {
                    board.initPlayer(toInit, Color.yellow);
                    isValid = true;
                }
                case "WHITE" -> {
                    board.initPlayer(toInit, Color.white);
                    isValid = true;
                }
                case "PURPLE" -> {
                    board.initPlayer(toInit, Color.purple);
                    isValid = true;
                }
                default -> System.out.println("Invalid entry, try again");
            }
        }
    }

    public static Pawn choosePawnToPlay(Player player) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(player.getName() + " choose a pawn by coordinates (X Y) ->");
            int x = sc.nextInt();
            int y = sc.nextInt();
            for (Pawn pawn : player.getPawns()) {
                if (pawn.getCurrentBox().getX() == x && pawn.getCurrentBox().getY() == y) {
                    return pawn;
                }
            }
            System.out.println("Invalid entry, try again");
        }
    }

    public static boolean movePawnToNeighbourBox(Player player, Pawn toPlay) {
        Scanner sc = new Scanner(System.in);
        boolean isValid = false;

        while (!isValid) {
            int compteur = 0;
            System.out.print("\n----- NEIGHBOURS -----");
            for (Box voisin : toPlay.getCurrentBox().getNeighbours()) {
                if(voisin.getSymbol() != 'o') {
                    System.out.println("\n" + compteur + "[" + voisin.getX() + " " + voisin.getY() + "]");
                    compteur++;
                }
            }
            System.out.print(player.getName() + " choose a box ->");
            int choice = sc.nextInt();
            try {
                Box c = toPlay.getCurrentBox().getNeighbours().get(choice);
                player.movePawnToNeighbourBox(toPlay, c);
                isValid = true;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean jumpOverPawn(Player player, Pawn toPlay) {
        System.out.println("\nChoose a pawn to jump :");
        Pawn aSauter = choosePawnToPlay(player);
        return player.jumpOverPawn(toPlay, aSauter);
    }

    public static void chooseAction(Player player) {
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean isValid = false;
        while (!isValid) {
            Pawn toPlay = choosePawnToPlay(player);
            System.out.print("\n1) Move a pawn to neighbour box\n2) Jump over a pawn\n->");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> isValid = movePawnToNeighbourBox(player, toPlay);
                case 2 -> isValid = jumpOverPawn(player, toPlay);
                default -> System.out.println("Invalid entry, try again");
            }
        }
    }

}
