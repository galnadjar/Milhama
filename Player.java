import java.util.List;

public class Player {
    private DeckOfCards deck;
    private String name;

    public Player(List<Card> cards){
        this.deck = new DeckOfCards(cards);
    }

    // returns the deck of cards the current player has
    public DeckOfCards getDeck(){
        return this.deck;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
