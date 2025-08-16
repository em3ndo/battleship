import java.io.*;
import java.util.Scanner;

public class AIChoices extends Grid {
    private final Scanner scanner = new Scanner(System.in);
    private int getInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter an integer:");
            scanner.next(); // consume non-integer input
        }
        return this.scanner.nextInt();
    }
    @Override
    public void chooseShip() throws IOException {
        //print instructions here
        File file = new File("C:\\Users\\edwar\\IdeaProjects\\csabattleship\\src\\Instructions.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
        System.out.println();
        System.out.println("Place your ships:");
        Player.aiChoices.display();
        for (int size : BattleshipGame.SHIP_SIZES) {
            shipLocation(size);
            Player.aiChoices.display();
        }
    }
    @Override
    public void shipLocation(int size) {
        while (true) {
            System.out.print("Enter row for the top part of the ship (size " + size + "): ");
            int row = getInput() - 1;
            System.out.print("Enter column for the top part of the ship: ");
            int col = getInput() - 1;
            System.out.print("Enter 'v' for vertical or 'h' for horizontal: ");
            char direction = scanner.next().charAt(0);

            if (Player.aiChoices.isValidPlacement(row, col, size, direction)) {
                Player.aiChoices.placeShip(row, col, size, direction);
                break;
            } else {
                System.out.println("Invalid placement! Please try again.");
            }
        }
    }
}
