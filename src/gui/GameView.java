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
import javafx.stage.Stage;

public class GameView {
    private static final int WINDOW_WIDTH = 710;
    private static final int WINDOW_HEIGHT = 550;

    private Stage stage;
    private Scene scene;
    private HBox mainLayout;
    private Controller controller;

    private Label puzzleInputLabel;
    private Label algorithmSelectionLabel;
    private Label costOfPathHeaderLabel;
    private Label costOfPathValueLabel;
    private Label searchDepthHeaderLabel;
    private Label searchDepthValueLabel;
    private Label maxDepthHeaderLabel;
    private Label maxDepthValueLabel;
    private Label expandedNodesHeaderLabel;
    private Label expandedNodesValueLabel;
    private Label timeHeaderLabel;
    private Label timeValueLabel;
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

    public Label getCostOfPathValueLabel() {
        return costOfPathValueLabel;
    }

    public Label getSearchDepthValueLabel() {
        return searchDepthValueLabel;
    }

    public Label getMaxDepthValueLabel() {
        return maxDepthValueLabel;
    }

    public Label getExpandedNodesValueLabel() {
        return expandedNodesValueLabel;
    }

    public Label getTimeValueLabel() {
        return timeValueLabel;
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
        layout1.setMinWidth((double) 11 * WINDOW_WIDTH / 20);
        layout1.setMinHeight((double) WINDOW_HEIGHT / 3);
        layout1.setFillWidth(false);
        layout1.setAlignment(Pos.CENTER);
        layout1.setId("layout1");
        layout1.getChildren().addAll(puzzleInputLabel, puzzleTextField, solveButton, viewPathButton);
        VBox.setMargin(puzzleInputLabel, new Insets(10, 0, 5, 0));
        VBox.setMargin(puzzleTextField, new Insets(10, 0, 5, 0));
        VBox.setMargin(solveButton, new Insets(10, 0, 5, 0));
        VBox.setMargin(viewPathButton, new Insets(10, 0, 5, 0));

        VBox layout2 = new VBox();
        layout2.setMinWidth((double) 11 * WINDOW_WIDTH / 20);
        layout2.setMinHeight((double) WINDOW_HEIGHT / 4);
        layout2.setFillWidth(true);
        layout2.setAlignment(Pos.CENTER);
        layout2.getChildren().addAll(algorithmSelectionLabel, searchAlgorithmsBox);
        VBox.setMargin(algorithmSelectionLabel, new Insets(10, 0, 5, 0));
        VBox.setMargin(searchAlgorithmsBox, new Insets(10, 0, 5, 0));

        VBox layout3 = new VBox();
        layout3.setMinWidth((double) 3 * WINDOW_WIDTH / 8);
        layout3.setMinHeight((double) 5 * WINDOW_HEIGHT / 16);
        layout3.setFillWidth(true);
        layout3.getChildren().addAll(costOfPathHeaderLabel, searchDepthHeaderLabel,
                maxDepthHeaderLabel, expandedNodesHeaderLabel, timeHeaderLabel);
        VBox.setMargin(costOfPathHeaderLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(searchDepthHeaderLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(maxDepthHeaderLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(expandedNodesHeaderLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(timeHeaderLabel, new Insets(6, 0, 6, 15));

        VBox layout4 = new VBox();
        layout4.setMinWidth((double) WINDOW_WIDTH / 8);
        layout4.setMinHeight((double) 5 * WINDOW_HEIGHT / 16);
        layout4.setFillWidth(true);
        layout4.setAlignment(Pos.TOP_LEFT);
        layout4.getChildren().addAll(costOfPathValueLabel, searchDepthValueLabel,
                maxDepthValueLabel, expandedNodesValueLabel, timeValueLabel);
        VBox.setMargin(costOfPathValueLabel, new Insets(6, 0, 6, 0));
        VBox.setMargin(searchDepthValueLabel, new Insets(6, 0, 6, 0));
        VBox.setMargin(maxDepthValueLabel, new Insets(6, 0, 6, 0));
        VBox.setMargin(expandedNodesValueLabel, new Insets(6, 0, 6, 0));
        VBox.setMargin(timeValueLabel, new Insets(6, 0, 6, 0));

        HBox layout5 = new HBox();
        layout5.getChildren().addAll(layout3, layout4);

        VBox layout6 = new VBox();
        layout6.setMinWidth((double) 11 * WINDOW_WIDTH / 20);
        layout6.setMinHeight((double) 5 * WINDOW_HEIGHT / 48);
        layout6.setFillWidth(true);
        layout6.setAlignment(Pos.TOP_CENTER);
        layout6.getChildren().addAll(resetButton);
        VBox.setMargin(resetButton, new Insets(0, 0, 30, 0));

        VBox leftLayout = new VBox();
        leftLayout.getChildren().addAll(layout1, layout2, layout5, layout6);

        GridPane layout7 = new GridPane();
        layout7.setPadding(new Insets(7));
        layout7.setHgap(10);
        layout7.setVgap(10);
        layout7.setMinHeight((double) 3 * WINDOW_HEIGHT / 4);
        layout7.setAlignment(Pos.CENTER);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                layout7.add(gridLabel[row][col], col, row);
            }
        }

        HBox layout8 = new HBox();
        layout8.setAlignment(Pos.CENTER);
        layout8.getChildren().addAll(nextButton, prevButton);
        HBox.setHgrow(nextButton, javafx.scene.layout.Priority.ALWAYS);
        layout8.setSpacing(110);

        VBox rightLayout = new VBox();
        rightLayout.getChildren().addAll(layout7, layout8);

        mainLayout = new HBox();
        mainLayout.setStyle("-fx-background-color: #FFFFFF;");
        mainLayout.getChildren().addAll(leftLayout, rightLayout);
    }

    public void createLabels() {
        puzzleInputLabel = new Label("Enter your Puzzle in this form 123456780: ");
        puzzleInputLabel.setId("prompt-label");
        algorithmSelectionLabel = new Label("Choose a Search Algorithm: ");
        algorithmSelectionLabel.setId("prompt-label");

        costOfPathHeaderLabel = new Label("Cost of Path = ");
        costOfPathHeaderLabel.setId("header-label");
        searchDepthHeaderLabel = new Label("Search Depth = ");
        searchDepthHeaderLabel.setId("header-label");
        maxDepthHeaderLabel = new Label("Max Depth = ");
        maxDepthHeaderLabel.setId("header-label");
        expandedNodesHeaderLabel = new Label("No. of Expanded Nodes = ");
        expandedNodesHeaderLabel.setId("header-label");
        timeHeaderLabel = new Label("Time = ");
        timeHeaderLabel.setId("header-label");

        costOfPathValueLabel = new Label("");
        costOfPathValueLabel.setId("value-label");
        searchDepthValueLabel = new Label("");
        searchDepthValueLabel.setId("value-label");
        maxDepthValueLabel = new Label("");
        maxDepthValueLabel.setId("value-label");
        expandedNodesValueLabel = new Label("");
        expandedNodesValueLabel.setId("value-label");
        timeValueLabel = new Label("");
        timeValueLabel.setId("value-label");

        gridLabel = new Label[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridLabel[i][j] = new Label("");
                gridLabel[i][j].setId("grid-label");
            }
        }
    }

    public void createPuzzleTextField() {
        puzzleTextField = new TextField();
        puzzleTextField.setPromptText("Enter Your Puzzle");
    }

    public void createButtons() {
        solveButton = new Button("Solve");
        solveButton.setId("btn");
        solveButton.setOnAction(controller.solve());

        resetButton = new Button("Reset");
        resetButton.setId("btn");
        resetButton.setOnAction(controller.reset());

        prevButton = new Button("Prev");
        prevButton.setId("btn");
        prevButton.setDisable(true);
        prevButton.setOnAction(controller.getPrevState());

        nextButton = new Button("Next");
        nextButton.setId("btn");
        nextButton.setDisable(true);
        nextButton.setOnAction(controller.getNextState());

        viewPathButton = new Button("View Path");
        viewPathButton.setId("view-path-btn");
        viewPathButton.setOnAction(controller.viewPath());
    }

    public void createSearchAlgorithmsComboBox() {
        searchAlgorithmsBox = new ComboBox<>();
        searchAlgorithmsBox.getItems().addAll("DFS", "BFS", "A* using Euclidean", "A* using Manhattan");
        searchAlgorithmsBox.setValue("BFS");
    }
}
