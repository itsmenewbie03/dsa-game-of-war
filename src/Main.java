import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // creates a Pile object
        Pile p;
        // creates two player to play the game
        Player player1 = new Player("Sylvester Stallone");
        Player player2 = new Player("Arnold Schwarzenegger");
        // creates a scanner to prompt the user
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        // prints a header text
        headerText("GAME OF WAR");
        // prompts the user if they wants random cards
        System.out.print("Random Cards? Y/N : ");
        String yn = scan.nextLine().toLowerCase();
        // checks if the answer is y for yes
        if (yn.equals("y")) {
            // invokes the constructor the creates a deck of cards
            p = new Pile(true);
            // creates game object with a full deck of cards
            GameOfWar game = new GameOfWar(player1, player2, p);
            // invokes the initializeGame method which will shuffle
            // and divides them for both player
            game.initializeGame();
            // starts the game
            game.startGame();

            // checks if the answer is n for no
        } else if (yn.equals("n")) {
            // prompts the user for the cards of both player
            System.out.println("Enter Player 1's cards (from top to bottom):");
            String p1_cards = scan.nextLine();
            System.out.println("Enter Player 2's cards (from top to bottom):");
            String p2_cards = scan.nextLine();
            // invoke the constructor the creates and empty deck
            p = new Pile();
            // creates a game object with empty deck of cards
            GameOfWar game = new GameOfWar(player1, player2, p);
            // handles possible error in parsing the input
            try {
                // tries to initialize the game from user input
                game.initializeGame(p1_cards, p2_cards);
            } catch (Exception e) {
                // catches the exception and prints the message to the user
                // calls the main method recursively to start the game again
                System.out.println("\n\n" + e.getMessage());
                main(args);
            }
            // starts the game
            game.startGame();

        }
        // handles invalid input for the first prompt warns the user
        // and calls the main method recursively
        else {
            System.out.println("\n\nInvalid Input! Please Try Again...");
            main(args);
        }
        // closes the scanner
        scan.close();
    }

    /*
     * Extra Methods To Print Texts with a Simple Border
     */
    private static void headerText(String s) {
        String border = "-".repeat(29);
        int w = border.length();

        System.out.println(border);
        System.out.println(centerText(s, w));
        System.out.println(border);
    }

    private static String centerText(String s, int w) {
        int toPad = (w - (s.length() + 2)) / 2;
        String centered = "|" + " ".repeat(toPad) + s + " ".repeat(toPad) + "|";
        return centered;
    }
}
