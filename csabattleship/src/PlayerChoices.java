public class PlayerChoices extends Grid {
    public void display(int hits) {
        System.out.println("You have scored " + hits + " hits so far.");
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public PlayerChoices() {
        super();
    }
    @Override
    public void chooseShip() {};
    @Override
    public void shipLocation(int size) {};
}