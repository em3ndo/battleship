import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class BattleshipGame {
    public static final int[] SHIP_SIZES = {4, 3, 3, 2, 2};

    private final HumanPlayer humanplayer;
    private final AIPlayer aiPlayer;

    public BattleshipGame() {
        humanplayer = new HumanPlayer();
        aiPlayer = new AIPlayer();
    }
    public void playGame() {
        //Start the game
        boolean playerTurn = true;
        while (true) {
            if (playerTurn) {
                System.out.println("Your Turn:");
                //Show an empty grid; the player chooses ship coordinates here
                humanplayer.getGrid().display();
                //After the player places their ships, the human player fires the first shot; they will alternate turns
                //with the AI
                humanplayer.fire(aiPlayer.getGrid());
                //Display every shot so far, on an empty grid with no AI ship positions, as a hit or miss (X or 0)
                humanplayer.getPlayerChoices().display(humanplayer.getHitCount());
                //See if all AI ships are destroyed or not
                if (checkGameOver(aiPlayer.getGrid())) {
                    System.out.println("Congratulations! You win!");
                    break;
                }
            } else {
                System.out.println("AI's Turn:");
                //AI fires the first shot
                aiPlayer.fire(humanplayer.getGrid());
                //See if all human ships are destroyed or not
                if (checkGameOver(humanplayer.getGrid())) {
                    System.out.println("Sorry, you lose. Better luck next time!");
                    break;
                }
            }
            playerTurn = !playerTurn;
        }
        //End the game if either player wins
        System.out.println("Game Over!");
    }

    //Check if the AI or human grid has any ship coordinates still alive, denoted as an S
    private boolean checkGameOver(Grid grid) {
        for (int i = 0; i < grid.grid.length; i++) {
            for (int j = 0; j < grid.grid.length; j++) {
                if (grid.grid[i][j] == 'S') {
                    return false;
                }
            }
        }
        return true;
    }

    //Start a new battleship game
    public static void main(String[] args) throws IOException {
        BattleshipGame game = new BattleshipGame();
        //Have the human player choose their ships; have the AI player receive randomly selected ships
        game.humanplayer.aiChoices.chooseShip();
        game.aiPlayer.aiShips.chooseShip();
        game.playGame();
    }
}
