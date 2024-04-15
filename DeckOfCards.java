import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeckOfCards {

    // Generating random number
    private static final SecureRandom randomNumbers = new SecureRandom();
    private static final int NUMBER_OF_CARDS = 52; // constant - amount of cards
    private ArrayList<Card> deck;

    // constructor for constructing the initial deck
    public DeckOfCards(){
        final List<String> facesList = Arrays.asList("Deuce", "Three", "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace");
        final List<String> suitsList = Arrays.asList("Hearts", "Diamonds", "Clubs", "Spades");

        deck = new ArrayList<>(NUMBER_OF_CARDS);
        ArrayList<String> faces = new ArrayList<>(facesList);
        ArrayList<String> suits = new ArrayList<>(suitsList);

        // populate the deck with all the cards
        for (String suit : suits) {
            for (String face : faces) {
                deck.add(new Card(face, suit));
            }
        }
        // shuffle the initialized deck
        shuffle();
    }

    //constructor for constructing the player deck
    public DeckOfCards(List<Card> cards) {
        deck = new ArrayList<>(cards);
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }

    // Shuffle the Deck of Cards with one-pass algorithm
    public void shuffle() {

        // for each card pick another random card 0-51 and swap them
        for (int first = 0; first < deck.size(); first++) {
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            //swap current card with randomly selected card (second)
            Card temp = deck.get(first);
            deck.set(first, deck.get(second));
            deck.set(second, temp);
        }
    }

    //deal one Card from the deck
    public Card dealCard() {
        // there's still cards in the deck
        if (!deck.isEmpty()) {
            Card card = deck.get(0);
            deck.remove(0);
            return card;
        }

        else
            return null; // returns null to indicate that the deck is empty
    }

    //inserts the cards earned from winning the round
    public void insertWinCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            deck.add(card);
        }
    }

    public String toString() {
        String deckString = "";
        for (Card card: deck){
            deckString += String.format("%s\n", card);
        }
        return deckString;
    }
}

