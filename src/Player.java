public class Player {
    // creates necessary fields for player object
    private Pile pile;
    private String name;

    // creates player constructor that take a String parameter
    // the parameter will be the player's name
    // this will also give the player an empty pile
    public Player(String name) {
        this.name = name;
        this.pile = new Pile();
    }

    // this method will make the player take any number of cards and adds it to its
    // pile
    public void take(Card... cards) {
        for (Card card : cards) {
            pile.enqueue(card);
        }
    }

    // this method will return the player's top card and removes it from its pile
    public Card getTopCard() {
        Card top_card = pile.dequeue();
        return top_card;
    }

    // this method will return a Pile of N number of cards from a player's pile
    // this will return null if the player does not have enough cards
    public Pile getCards(int n) {
        if (pile.size() < n) {
            return null;
        }
        Pile npile = new Pile();
        for (int i = 0; i < n; i++) {
            Card pcard = pile.dequeue();
            npile.enqueue(pcard);
        }
        return npile;
    }

    /*
     * GETTERS AND SETTERS
     */
    public Pile getPile() {
        return pile;
    }

    public String getName() {
        return name;
    }

    public void setPile(Pile pile) {
        this.pile = pile;
    }

    public void setName(String name) {
        this.name = name;
    }

}
