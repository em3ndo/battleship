import java.util.Scanner;

class HumanPlayer implements Player {
    private final Scanner scanner;
    private int hitCount = 0;
    public HumanPlayer() {
        scanner = new Scanner(System.in);
    }
    public Grid getGrid() {
        return this.aiChoices;
    }
    public PlayerChoices getPlayerChoices() {
        return this.playerChoices;
    }
    public int getHitCount() {
        return this.hitCount;
    }
    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
    public void fire(Grid opponentGrid) {
        System.out.print("Enter row to fire: ");
        int row = scanner.nextInt() - 1;
        //Check if the row or column choices are outside the coordinate range
        while(row>9 || row < 0) {
            System.out.println("Your number is out of bounds! Re-enter a valid number.");
            System.out.println("Enter row to fire: ");
            row = scanner.nextInt() - 1;
        }
        System.out.print("Enter column to fire: ");
        int col = scanner.nextInt() - 1;
        while(col>9 || col < 0) {
            System.out.println("Your number is out of bounds! Re-enter a valid number");
            System.out.println("Enter row to fire: ");
            col = scanner.nextInt() - 1;
        }
        //Check if the player already fired at their chosen spot
        while(playerChoices.grid[row][col] == 'O' || playerChoices.grid[row][col] == 'X') {
            System.out.println("You already fired here! Choose a different coordinate.");
            System.out.println("Enter row to fire: ");
            row = scanner.nextInt() - 1;
            while(row>9 || row < 0) {
                System.out.println("Your number is out of bounds! Re-enter a valid number.");
                System.out.println("Enter row to fire: ");
                row = scanner.nextInt() - 1;
            }
            System.out.println("Enter column to fire: ");
            col = scanner.nextInt() - 1;
            while(col>9 || col < 0) {
                System.out.println("Your number is out of bounds! Re-enter a valid number");
                System.out.println("Enter column to fire: ");
                col = scanner.nextInt() - 1;
            }
        }
        boolean hit = opponentGrid.hit(row, col);
        if (hit) {
            //Update the player choices grid and hit count
            System.out.println("Hit!");
            playerChoices.grid[row][col] = 'X';
            hitCount++;
            setHitCount(hitCount);
            aiShips.isShipEliminated(row, col);
            if (opponentGrid.isShipEliminated(row, col)) {
                System.out.println("You eliminated an AI ship!");
            }
        } else {
            //Update the player choices grid
            System.out.println("Miss!");
            playerChoices.grid[row][col] = 'O';
        }
    }
}
