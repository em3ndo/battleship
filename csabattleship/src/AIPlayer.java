import java.util.ArrayList;
import java.util.List;

class AIPlayer implements Player {
    private final List<int[]> hits;
    public AIPlayer() {
        hits = new ArrayList<>();
    }

    public Grid getGrid() {
        return this.aiShips;
    }

    public void fire(Grid opponentGrid) {
        int row, col;
        if (!hits.isEmpty()) {
            int[] lastHit = hits.getLast();
            row = lastHit[0];
            col = lastHit[1];
            do {
                int direction = (int) (Math.random() * 4);
                switch (direction) {
                    case 0: // Up
                        row--;
                        break;
                    case 1: // Right
                        col++;
                        break;
                    case 2: // Down
                        row++;
                        break;
                    case 3: // Left
                        col--;
                        break;
                }
            } while (row < 0 || row >= aiChoices.grid.length || col < 0 || col >= aiChoices.grid.length || opponentGrid.validFire(row, col));
        } else {
            do {
                row = (int) (Math.random() * aiChoices.grid.length);
                col = (int) (Math.random() * aiChoices.grid.length);
            } while (opponentGrid.validFire(row, col));
        }

        if (opponentGrid.hit(row, col)) {
            System.out.println("AI hit at: " + (row+1) + ", " + (col+1));
            hits.add(new int[]{row, col});
            aiChoices.isShipEliminated(row, col);
            if (opponentGrid.isShipEliminated(row, col)) {
                System.out.println("One of your ships was eliminated!");
                hits.clear(); // Reset hits if ship is eliminated
            }
        } else {
            System.out.println("AI missed at: " + (row+1) + ", " + (col+1));
        }
    }
}