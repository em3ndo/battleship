import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Grid {
    public static final int GRID_SIZE = 10;
    protected char[][] grid;

    public Grid() {
        grid = new char[GRID_SIZE][GRID_SIZE];
        initializeGrid();
    }
    public abstract void chooseShip() throws IOException;
    public abstract void shipLocation(int size);
    private void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public void display() {

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

    public boolean isValidPlacement(int row, int col, int size, char direction) {
        if(row > 9 || row < 0 || col > 9 || col < 0)
            return false;
        if(direction != 'v' && direction != 'h')
            return false;
        if (direction == 'v') {
            if (row + size > GRID_SIZE) {
                return false;  // Ship would leak beyond grid
            }
            for (int i = row; i < row + size; i++) {
                if (grid[i][col] == 'S') {
                    return false;  // Ship overlaps with existing ship
                }
            }
        } else {
            if (col + size > GRID_SIZE) {
                return false;  // Ship would leak beyond grid
            }
            for (int i = col; i < col + size; i++) {
                if (grid[row][i] == 'S') {
                    return false;  // Ship overlaps with existing ship
                }
            }
        }
        return true;
    }

    public void placeShip(int row, int col, int size, char direction) {
        if (direction == 'v') {
            for (int i = 0; i < size; i++) {
                grid[row + i][col] = 'S';
            }
        } else {
            for (int i = 0; i < size; i++) {
                grid[row][col + i] = 'S';
            }
        }
    }

    public boolean validFire(int row, int col) {
        return row < 0 || row >= GRID_SIZE || col < 0 || col >= GRID_SIZE || grid[row][col] == 'X' || grid[row][col] == 'O';
    }
    public boolean hit(int row, int col) {
        if (grid[row][col] == 'S') {
            grid[row][col] = 'X';
            return true;
        } else {
            grid[row][col] = 'O';
            return false;
        }
    }

    public boolean isShipEliminated(int i, int j) {
        int a = 0;
        int b = 0;
        if (grid[i][j] == 'X') {
            while(grid[i+a][j] != '-' && grid[i+a][j] != 'O' && i+a<9) {
                a++;
                if(grid[i+a][j] == 'S')
                    return false;
            }
            a=0;
            while(grid[i+a][j] != '-' && grid[i+a][j] != 'O' && i+a>0) {
                a--;
                if(grid[i+a][j] == 'S')
                    return false;
            }
            while(grid[i][j+b] != '-' && grid[i][j+b] != 'O' && j+b<9) {
                b++;
                if(grid[i][j+b] == 'S')
                    return false;
            }
            b=0;
            while(grid[i][j+b] != '-' && grid[i][j+b] != 'O' && j+b>0) {
                b--;
                if(grid[i][j+b] == 'S')
                    return false;
            }
        }
        return true;
    }
}