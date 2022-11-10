import java.util.ArrayList;
import java.util.Collections;

public class Pile extends QueueArray<Card> {
    // creates all the necessary fields
    private QueueArray<Card> pile;
    private String[] suits = { "h", "d", "s", "c" };
    private String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A" };

    // create a constructor that take a boolean parameter
    // this parameter will determine whether to create a deck or not
    public Pile(boolean createDeck) {
        this.pile = new QueueArray<>();
        if (createDeck) {
            // loops through card value
            for (String value : values) {
                // loops through card suits
                for (String suit : suits) {
                    // create a new card and adds it to the pile
                    Card nCard = new Card(value, suit);
                    pile.enqueue(nCard);
                }
            }
        }
    }

    // creates a constructor for creating empty pile
    public Pile() {
        this.pile = new QueueArray<>();
    }

    // creates a constructor that creates a pile from String input
    // this constructor throws an exception since it uses Card(String)
    // constructor that throws an exception when it encounters invalid input
    public Pile(String cards) throws Exception {
        this.pile = new QueueArray<>();
        for (String scard : cards.split(" ")) {
            Card card = new Card(scard);
            pile.enqueue(card);
        }
    }

    // overrides superclass method to ensure it will do as intended
    public Card dequeue() {
        return pile.dequeue();
    }

    // overrides superclass method to ensure it will do as intended
    public void enqueue(Card x) {
        pile.enqueue(x);
    }

    // creates a method that shuffles the deck
    public void shuffle_deck() {
        ArrayList<Card> copy = pile.getQueue();
        Collections.shuffle(copy);
        pile.setQueue(copy);
    }

    // creates a method that will allow us to append any number of piles to a pile
    public void append(Pile... p) {
        for (Pile np : p) {
            while (!np.empty()) {
                pile.enqueue(np.dequeue());
            }
        }
    }

    // creates a method that will allow us to append any number of cards to a pile
    public void append(Card... c) {
        for (Card card : c) {
            pile.enqueue(card);
        }
    }

    /*
     * More overrides below
     */
    public boolean empty() {
        return pile.empty();
    }

    public int size() {
        return pile.size();
    }

    // overrides toString method to print the pile of card neatly
    @Override
    public String toString() {
        // prints the cards into 10 columns
        String ans = "";
        int flag = 1;
        for (Card card : pile.getQueue()) {
            ans += card + (flag % 10 == 0 ? "\n" : " ");
            flag++;
        }
        return ans;
    }
}
