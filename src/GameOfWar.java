public class GameOfWar {
    // creates necessary fields
    Player player1;
    Player player2;
    Player winner;
    Pile deck;
    Pile warPile = new Pile();
    int turn = 1;

    // creates a game constructor with all necessary fields
    public GameOfWar(Player player1, Player player2, Pile deck) {
        this.player1 = player1;
        this.player2 = player2;
        this.deck = deck;
    }

    // creates a method for initializing game randomly
    // this method will shuffle the deck
    // and distributes it alternately and equally to the both player
    public void initializeGame() {
        int flag = 0;
        // do shuffle
        deck.shuffle_deck();
        // loop until deck is fully distributed
        while (!deck.empty()) {
            // checks if flag is even
            // player 1 gets the card if flag is even
            // while [player will get the card if it is odd
            if (flag % 2 == 0) {
                player1.take(deck.dequeue());
            } else {
                player2.take(deck.dequeue());
            }
            // increase the flag by 1
            flag++;
        }
    }

    // overloads initializeGame method to allow user the define the cards for each
    // player
    // this method will parse the input and perform validation
    // if the validation fails then this method will throw an Exception
    // otherwise players pile will be set accordingly
    public void initializeGame(String p1_cards, String p2_cards) throws Exception {
        Pile p1_pile = new Pile(p1_cards);
        Pile p2_pile = new Pile(p2_cards);
        if (p1_pile.size() != 26) {
            throw new Exception("Player 1's pile is incomplete!");
        }
        if (p2_pile.size() != 26) {
            throw new Exception("Player 2's pile is incomplete!");
        }
        player1.setPile(p1_pile);
        player2.setPile(p2_pile);
    }

    // defines the method for starting the game
    public void startGame() {
        // loops until turn is less than 5000 and there is no winner
        while (turn < 5000 && winner == null) {
            // invokes play method passing the current turn number
            play(turn);
            // invokes printStatus method
            printStatus();
            // increment the turn by 1
            turn++;
        }
        // check if there is a winner
        if (winner != null) {
            // prints the winner
            System.out.println(winner.getName() + " wins!");
        } else {
            // no winner so therefore it is a draw
            // prints draw
            System.out.println();
            System.out.println(" ".repeat(25) + "*** DRAW ***" + " ".repeat(25));
        }
    }

    // creates a method print current game status
    // this method will also check if a player is already holding all the cards
    // then declare them as the winner
    public void printStatus() {
        System.out.println("Turn: " + this.turn);
        Pile p1Pile = player1.getPile();
        Pile p2Pile = player2.getPile();
        if (p1Pile.size() == 52) {
            winner = player1;
        }
        if (p2Pile.size() == 52) {
            winner = player2;
        }
        System.out.println(this.player1.getName() + ":\n" + p1Pile);
        System.out.println();
        System.out.println(this.player2.getName() + ":\n" + p2Pile);
        System.out.println("-".repeat(29));
    }

    // defines method play to simulate every turn
    public void play(int turn) {
        // check if the game has just started
        if (turn == 1) {
            // won't touch anything yet
            return;
        }
        // gets player1's top card
        Card p1_card = player1.getTopCard();
        // check if player1's card is null
        if (p1_card == null) {
            // if null means player1 does not have card to play anymore
            // player 2 wins
            winner = player2;
            return;
        }
        // gets player2's top card

        Card p2_card = player2.getTopCard();
        // check if player2's card is null
        if (p2_card == null) {
            // if null means player2 does not have card to play anymore
            // player 1 wins
            winner = player1;
            return;
        }
        // compares the players respective card
        int comp = p1_card.compareTo(p2_card);
        // check if it is a tie
        if (comp == 0) {
            // prints *** WAR! *** to notify that war is initiated
            System.out.println(" ".repeat(25) + "*** WAR! ***" + " ".repeat(25));
            // gets 3 cards from both player
            // if the pile is null therefore the player does not have enough cards
            // and the other player wins
            Pile p1Pile = player1.getCards(3);
            Pile p2Pile = player2.getCards(3);
            if (p1Pile == null) {
                winner = player2;
                return;
            }
            if (p2Pile == null) {
                winner = player1;
                return;
            }
            // add the drawn card to the warPile preserving their order of appearance
            warPile.append(p1_card);
            warPile.append(p1Pile);
            warPile.append(p2_card);
            warPile.append(p2Pile);
            // call play method recursively until there is no longer a war or a player wins
            play(turn);
        } else if (comp == -1) {
            // player 2 wins and will take the cards
            // if there is a war pile the pile will be added to their pile
            if (!warPile.empty()) {
                player2.getPile().append(warPile);
            }
            player2.take(p1_card, p2_card);
        } else if (comp == 1) {
            // player 1 wins and will take the cards
            // if there is a war pile the pile will be added to their pile
            if (!warPile.empty()) {
                player1.getPile().append(warPile);
            }
            player1.take(p1_card, p2_card);
        }
    }
}
