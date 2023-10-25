package runner;

import gui.GameView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static final GameView view = GameView.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("8-Puzzle Solver");
        view.setup(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
