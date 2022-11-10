public class Card implements Comparable<Card> {
    // creates fields for Card object
    String value;
    String suit;
    // creates a variable with cards value in ascending order
    private String rank = "23456789TJQKA";
    // creates a variable to hold valid card suits
    private String valid_suit = "hdsc";

    // creates a constructor from value and suit
    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    // creates a constructor from a single string
    // this will parse the input and handle all
    // possible error
    public Card(String value_suit) throws Exception {
        String[] parsed = value_suit.split("");
        if (parsed.length != 2) {
            throw new Exception("Card" + value_suit + " is not valid! Must be \"ab\" a=value, b=suit");
        }
        String value = parsed[0];
        if (!rank.contains(value)) {
            throw new Exception(value + " is not a valid card value! valid cards are " + rank);
        }
        String suit = parsed[1];
        if (!valid_suit.contains(suit)) {
            throw new Exception(suit + " is not a valid card suit! valid suits are " + valid_suit);
        }
        this.value = parsed[0];
        this.suit = parsed[1];
    }

    // overrides the compareTo method from Comparable interface
    // this methods takes the index of card's value from rank variable
    // then will return integer representing the comparison result
    // 0 for equal
    // -1 for less than
    // 1 for greater than
    @Override
    public int compareTo(Card o) {
        int a = rank.indexOf(value);
        int b = rank.indexOf(o.value);
        return a == b ? 0 : a > b ? 1 : -1;
    }

    // overrides toString method
    // this will card to be printed appropriately
    @Override
    public String toString() {
        return this.value + this.suit;
    }

}
