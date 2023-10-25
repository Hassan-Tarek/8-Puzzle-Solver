package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameView {
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 550;

    private Stage stage;
    private Scene scene;
    private HBox mainLayout;
    private Controller controller;

    private Label puzzleInputLabel;
    private Label algorithmSelectionLabel;
    private Label costOfPathLabel;
    private Label searchDepthLabel;
    private Label maxDepthLabel;
    private Label expandedNodesLabel;
    private Label timeLabel;
    private Label[][] gridLabel;
    private TextField puzzleTextField;
    private Button solveButton;
    private Button resetButton;
    private Button nextButton;
    private Button prevButton;
    private Button viewPathButton;
    private ComboBox<String> searchAlgorithmsBox;

    private static GameView view;

    private GameView() {
    }

    public static GameView getInstance() {
        if (view == null)
            view = new GameView();
        return view;
    }

    public void setup(Stage stage) {
        this.controller = new Controller();
        createLabels();
        createPuzzleTextField();
        createSearchAlgorithmsComboBox();
        createButtons();
        setupLayouts();
        this.scene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        setStage(stage);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public Label getCostOfPathLabel() {
        return costOfPathLabel;
    }

    public Label getSearchDepthLabel() {
        return searchDepthLabel;
    }

    public Label getMaxDepthLabel() {
        return maxDepthLabel;
    }

    public Label getExpandedNodesLabel() {
        return expandedNodesLabel;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public Label[][] getGridLabel() {
        return gridLabel;
    }

    public TextField getPuzzleTextField() {
        return puzzleTextField;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Button getPrevButton() {
        return prevButton;
    }

    public ComboBox<String> getSearchAlgorithmsBox() {
        return searchAlgorithmsBox;
    }

    public void setupLayouts() {
        VBox layout1 = new VBox();
        layout1.setMinWidth((double) WINDOW_WIDTH / 2);
        layout1.setMinHeight((double) WINDOW_HEIGHT / 3);
        layout1.setFillWidth(false);
        layout1.setAlignment(Pos.CENTER);
        layout1.getChildren().addAll(puzzleInputLabel, puzzleTextField, solveButton, viewPathButton);
        VBox.setMargin(puzzleInputLabel, new Insets(10, 0, 5, 0));
        VBox.setMargin(puzzleTextField, new Insets(10, 0, 5, 0));
        VBox.setMargin(solveButton, new Insets(10, 0, 5, 0));
        VBox.setMargin(viewPathButton, new Insets(10, 0, 5, 0));

        VBox layout2 = new VBox();
        layout2.setMinWidth((double) WINDOW_WIDTH / 2);
        layout2.setMinHeight((double) WINDOW_HEIGHT / 4);
        layout2.setFillWidth(true);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(algorithmSelectionLabel, searchAlgorithmsBox);
        VBox.setMargin(algorithmSelectionLabel, new Insets(10, 0, 5, 0));
        VBox.setMargin(searchAlgorithmsBox, new Insets(10, 0, 5, 0));

        VBox layout3 = new VBox();
        layout3.setMinWidth((double) WINDOW_WIDTH / 2);
        layout3.setMinHeight((double) 5 * WINDOW_HEIGHT / 16);
        layout3.setFillWidth(true);
        layout3.getChildren().addAll(costOfPathLabel, searchDepthLabel,
                maxDepthLabel, expandedNodesLabel, timeLabel);
        VBox.setMargin(costOfPathLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(searchDepthLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(maxDepthLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(expandedNodesLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(timeLabel, new Insets(6, 0, 6, 15));

        VBox layout4 = new VBox();
        layout4.setMinWidth((double) WINDOW_WIDTH / 2);
        layout4.setMinHeight((double) 5 * WINDOW_HEIGHT / 48);
        layout4.setFillWidth(true);
        layout4.setAlignment(Pos.TOP_CENTER);
        layout4.getChildren().addAll(resetButton);
        VBox.setMargin(resetButton, new Insets(0, 0, 30, 0));

        VBox leftLayout = new VBox();
        leftLayout.getChildren().addAll(layout1, layout2, layout3, layout4);

        GridPane layout5 = new GridPane();
        layout5.setPadding(new Insets(7));
        layout5.setHgap(10);
        layout5.setVgap(10);
        layout5.setMinHeight((double) 3 * WINDOW_HEIGHT / 4);
        layout5.setAlignment(Pos.CENTER);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                layout5.add(gridLabel[row][col], col, row);
            }
        }

        HBox layout6 = new HBox();
        layout6.setAlignment(Pos.CENTER);
        layout6.getChildren().addAll(nextButton, prevButton);
        HBox.setHgrow(nextButton, javafx.scene.layout.Priority.ALWAYS);
        layout6.setSpacing(110);


        VBox rightLayout = new VBox();
        rightLayout.getChildren().addAll(layout5, layout6);

        mainLayout = new HBox();
        mainLayout.setStyle("-fx-background-color: #FFFFFF;");
        mainLayout.getChildren().addAll(leftLayout, rightLayout);
    }

    public void createLabels() {
        puzzleInputLabel = new Label("Enter your Puzzle in this form 123456780: ");
        puzzleInputLabel.setFont(Font.font("Arial", FontWeight.BLACK, 16));
        algorithmSelectionLabel = new Label("Choose a Search Algorithm: ");
        algorithmSelectionLabel.setFont(Font.font("Arial", FontWeight.BLACK, 16));
        costOfPathLabel = new Label("Cost of Path = ");
        costOfPathLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        searchDepthLabel = new Label("Search Depth = ");
        searchDepthLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        maxDepthLabel = new Label("Max Depth = ");
        maxDepthLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        expandedNodesLabel = new Label("No. of Expanded Nodes = ");
        expandedNodesLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        timeLabel = new Label("Time = ");
        timeLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        gridLabel = new Label[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridLabel[i][j] = new Label("");
                gridLabel[i][j].setMinWidth(90);
                gridLabel[i][j].setMinHeight(90);
                gridLabel[i][j].setStyle("-fx-background-color: #00ff00;");
                gridLabel[i][j].setFont(new Font("Arial", 30));
                gridLabel[i][j].setAlignment(javafx.geometry.Pos.CENTER);
            }
        }
    }

    public void createPuzzleTextField() {
        puzzleTextField = new TextField();
        puzzleTextField.setPromptText("Enter Your Puzzle");
        puzzleTextField.setMinWidth(280);
        puzzleTextField.setMinHeight(35);
        puzzleTextField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
    }

    public void createButtons() {
        solveButton = new Button("Solve");
        solveButton.setMinWidth(90);
        solveButton.setMinHeight(35);
        solveButton.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        solveButton.setOnAction(controller.solve());

        resetButton = new Button("Reset");
        resetButton.setMinWidth(90);
        resetButton.setMinHeight(35);
        resetButton.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        resetButton.setOnAction(controller.reset());

        prevButton = new Button("Prev");
        prevButton.setMinWidth(90);
        prevButton.setMinHeight(35);
        prevButton.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        prevButton.setDisable(true);
        prevButton.setOnAction(controller.getPrevState());

        nextButton = new Button("Next");
        nextButton.setMinWidth(90);
        nextButton.setMinHeight(35);
        nextButton.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        nextButton.setDisable(true);
        nextButton.setOnAction(controller.getNextState());

        viewPathButton = new Button("View Path");
        viewPathButton.setMaxWidth(90);
        viewPathButton.setMaxHeight(35);
        viewPathButton.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        viewPathButton.setOnAction(controller.viewPath());
    }

    public void createSearchAlgorithmsComboBox() {
        searchAlgorithmsBox = new ComboBox<>();
        searchAlgorithmsBox.getItems().addAll("DFS", "BFS", "A* using Euclidean", "A* using Manhattan");
        searchAlgorithmsBox.setMinWidth(200);
        searchAlgorithmsBox.setMinHeight(30);
        searchAlgorithmsBox.setStyle("-fx-font-family: Arial; -fx-font-size: 14;");
        searchAlgorithmsBox.setValue("BFS");
    }
}
