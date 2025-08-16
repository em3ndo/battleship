public class AIShips extends Grid {
    @Override
    public void chooseShip() {
        for (int size : BattleshipGame.SHIP_SIZES) {
            shipLocation(size);
        }
    }
    @Override
    public void shipLocation(int size) {
        int row, col;
        char direction;
        do {
            row = (int) (Math.random() * Player.aiShips.grid.length);
            col = (int) (Math.random() * Player.aiShips.grid.length);
            direction = (Math.random() < 0.5) ? 'v' : 'h';
        } while (!Player.aiShips.isValidPlacement(row, col, size, direction));

        Player.aiShips.placeShip(row, col, size, direction);
    }
}
