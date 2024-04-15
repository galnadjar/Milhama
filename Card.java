import java.util.Arrays;
import java.util.List;

// Card class represents a single playing card
public class Card {
    private final String face;
    private final String suit;
    private final List<String> facesList = Arrays.asList("Deuce", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace");

    public Card(String cardFace, String cardSuit) {
        this.face = cardFace;
        this.suit = cardSuit;
    }

    // used like a enum from the string to its numeric value
    public int getCardVal(){
        return facesList.indexOf(face)+2; // Ace is the greatest therefore we start from 2
    }

    public String toString() {
        return face + " of " + suit;
    }
}
