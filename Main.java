import javafx.application.Application;
import javafx.stage.Stage;

// made by GAL NADJAR
// 10/04/2024

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize and start the game
        Game game = new Game();
        game.start();
    }
}