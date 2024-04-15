import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;


public class Game {
    private Player player1;
    private Player player2;
    private DeckOfCards deckOfCards =  new DeckOfCards();

    public Game(){
        ArrayList<Card> cards = deckOfCards.getDeck();
        splitDeck(cards);
    }

    // splits the shuffled main deck between two players evenly
    public void splitDeck(ArrayList<Card> cards) {
        player1 = new Player(cards.subList(0, cards.size() / 2));
        player2 = new Player(cards.subList(cards.size() / 2, cards.size()));
    }

    // starts the game
    public void start(){
        gameStartPrompt();
        getPlayersNames();

        //Declare & initialize player cards & piles
        Card player1Card;
        Card player2Card;
        ArrayList<Card> player1PlacedCards = new ArrayList<>();
        ArrayList<Card> player2PlacedCards = new ArrayList<>();
        ArrayList<Card> joinedPileOfCards = new ArrayList<>();
        String winner;

        // game is playing until we have a winner!
        while(true) {
            player1Card = player1.getDeck().dealCard();
            player2Card = player2.getDeck().dealCard();
            if (player1Card == null || player2Card == null)
                break;

            //adds a card to the pile of each player in the given round
            player1PlacedCards.add(player1Card);
            player2PlacedCards.add(player2Card);

            // both players have same value cards ITS MILHAMAM!
            if (player1Card.getCardVal() == player2Card.getCardVal()){
                // draws additional two cards without doing anything and the third from next round will be like the first drawn card
                displayMsg(String.format("%s: %s\n%s: %s\n",player1.getName(),player1Card,player2.getName(),player2Card), "MILHAMA!");

                for (int i=0; i < 2; i++) {
                    player1Card = player1.getDeck().dealCard();
                    player2Card = player2.getDeck().dealCard();

                    // in the case of inside Milhama one of the players don't have enough cards, he lost
                    if (player1Card == null || player2Card == null)
                        break;

                    player1PlacedCards.add(player1Card);
                    player2PlacedCards.add(player2Card);
                    displayMsg(String.format("%s: %s\n%s: %s",player1.getName(),player1Card,player2.getName(),player2Card),
                            String.format("MILHAMA deal num %d",i+1));
                }
            }

            // diff value cards
            else {
                // joins the two piles into 1 big pile
                joinedPileOfCards.addAll(player1PlacedCards);
                joinedPileOfCards.addAll(player2PlacedCards);

                if (player1Card.getCardVal() > player2Card.getCardVal()) {
                    player1.getDeck().insertWinCards(joinedPileOfCards);
                    winner = player1.getName();
                }
                else {
                    player2.getDeck().insertWinCards(joinedPileOfCards);
                    winner = player2.getName();
                }
                // clear the piles of cards
                player1PlacedCards.clear();
                player2PlacedCards.clear();
                joinedPileOfCards = new ArrayList<>();

                displayMsg(String.format("%s: %s\n%s: %s\n\n",player1.getName(),player1Card,player2.getName(),player2Card)
                        ,winner + " won this round!");
            }
        }
        // a tie
        if (player1Card == null && player2Card == null)
            displayMsg("SUPER RARE but the game ended in even!", "GAME OVER"); // card values matched

        // player1 has lost
        else if (player1Card == null)
            displayMsg(String.format("%s has Won!",player2.getName()),"GAME OVER");

        //player2 has lost
        else
            displayMsg(String.format("%s has Won!",player1.getName()), "GAME OVER");

    }

    // gets input from the user regarding the players names
    private void getPlayersNames() {
        // Create a text input dialog
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Milhama Game");
        dialog.setHeaderText("First Player Name");
        dialog.setContentText("Name:");

        Optional<String> player1NameResult = dialog.showAndWait();
        // Show the dialog and wait for user interaction, then doing the same for the second
        if (player1NameResult.isPresent()) {
            player1.setName(player1NameResult.get());
            dialog.setHeaderText("Second Player Name");
            Optional<String> player2NameResult = dialog.showAndWait();
            if (player2NameResult.isPresent())
                player2.setName(player2NameResult.get());
        }
    }

    // displays a given prompt to the screen as an alert
    public void displayMsg(String msg, String header) {
        // Create an alert object with desired type (INFORMATION here)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Set the title and content text of the alert
        alert.setTitle("Milhama Game");
        alert.setContentText(msg);
        alert.setHeaderText(header);

        // Show the alert dialog
        alert.showAndWait();
    }

    // initial game prompt
    public void gameStartPrompt(){

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Milhama Game");
        alert.setHeaderText("WELCOME TO MILHAMA");
        alert.setContentText("Do you want to start the game?");

        // Add buttons to the dialog
        ButtonType startButton = new ButtonType("Start");
        ButtonType exitButton = new ButtonType("Exit");
        alert.getButtonTypes().setAll(startButton, exitButton);
        Optional<ButtonType> option = alert.showAndWait();
        // Show the dialog and wait for user interaction
        if (option.get() == startButton){
                System.out.println("Game started!");
            }
        else if (option.get() == exitButton) {
            // Exit the application
            System.exit(0);
        }
    }
}