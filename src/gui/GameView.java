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

    private static final String PROMPT_LABEL_ID = "prompt-label";
    private static final String HEADER_LABEL_ID = "header-label";
    private static final String VALUE_LABEL_ID = "value-label";
    private static final String GRID_LABEL_ID = "grid-label";

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

    private void setupLayouts() {
        VBox leftLayout = createLeftLayout();
        VBox rightLayout = createRightLayout();

        mainLayout = new HBox();
        mainLayout.setId("main-layout");
        mainLayout.getChildren().addAll(leftLayout, rightLayout);
    }

    private VBox createLeftLayout() {
        VBox topLeftLayout = createTopLeftLayout();
        HBox middleLeftLayout = createMiddleLeftLayout();
        VBox downLeftLayout = createBottomLeftLayout();

        VBox leftLayout = new VBox();
        leftLayout.getChildren().addAll(topLeftLayout, middleLeftLayout, downLeftLayout);

        return leftLayout;
    }

    private VBox createTopLeftLayout() {
        VBox topLayout = new VBox();
        topLayout.setMinWidth((double) 11 * WINDOW_WIDTH / 20);
        topLayout.setMinHeight((double) WINDOW_HEIGHT / 3);
        topLayout.setFillWidth(false);
        topLayout.setAlignment(Pos.CENTER);
        topLayout.getChildren().addAll(puzzleInputLabel, puzzleTextField, solveButton, viewPathButton);
        VBox.setMargin(puzzleInputLabel, new Insets(10, 0, 5, 0));
        VBox.setMargin(puzzleTextField, new Insets(10, 0, 5, 0));
        VBox.setMargin(solveButton, new Insets(10, 0, 5, 0));
        VBox.setMargin(viewPathButton, new Insets(10, 0, 5, 0));

        VBox bottomLayout = new VBox();
        bottomLayout.setMinWidth((double) 11 * WINDOW_WIDTH / 20);
        bottomLayout.setMinHeight((double) WINDOW_HEIGHT / 4);
        bottomLayout.setFillWidth(true);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.getChildren().addAll(algorithmSelectionLabel, searchAlgorithmsBox);
        VBox.setMargin(algorithmSelectionLabel, new Insets(10, 0, 5, 0));
        VBox.setMargin(searchAlgorithmsBox, new Insets(10, 0, 5, 0));

        VBox topLeftLayout = new VBox();
        topLeftLayout.getChildren().addAll(topLayout, bottomLayout);

        return topLeftLayout;
    }

    private HBox createMiddleLeftLayout() {
        VBox headerLabelsLayout = new VBox();
        headerLabelsLayout.setMinWidth((double) 3 * WINDOW_WIDTH / 8);
        headerLabelsLayout.setMinHeight((double) 5 * WINDOW_HEIGHT / 16);
        headerLabelsLayout.setFillWidth(true);
        headerLabelsLayout.getChildren().addAll(costOfPathHeaderLabel, searchDepthHeaderLabel,
                maxDepthHeaderLabel, expandedNodesHeaderLabel, timeHeaderLabel);
        VBox.setMargin(costOfPathHeaderLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(searchDepthHeaderLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(maxDepthHeaderLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(expandedNodesHeaderLabel, new Insets(6, 0, 6, 15));
        VBox.setMargin(timeHeaderLabel, new Insets(6, 0, 6, 15));

        VBox valueLabelsLayout = new VBox();
        valueLabelsLayout.setMinWidth((double) WINDOW_WIDTH / 8);
        valueLabelsLayout.setMinHeight((double) 5 * WINDOW_HEIGHT / 16);
        valueLabelsLayout.setFillWidth(true);
        valueLabelsLayout.setAlignment(Pos.TOP_LEFT);
        valueLabelsLayout.getChildren().addAll(costOfPathValueLabel, searchDepthValueLabel,
                maxDepthValueLabel, expandedNodesValueLabel, timeValueLabel);
        VBox.setMargin(costOfPathValueLabel, new Insets(6, 0, 6, 0));
        VBox.setMargin(searchDepthValueLabel, new Insets(6, 0, 6, 0));
        VBox.setMargin(maxDepthValueLabel, new Insets(6, 0, 6, 0));
        VBox.setMargin(expandedNodesValueLabel, new Insets(6, 0, 6, 0));
        VBox.setMargin(timeValueLabel, new Insets(6, 0, 6, 0));

        HBox middleLeftLayout = new HBox();
        middleLeftLayout.getChildren().addAll(headerLabelsLayout, valueLabelsLayout);

        return middleLeftLayout;
    }

    private VBox createBottomLeftLayout() {
        VBox layout = new VBox();
        layout.setMinWidth((double) 11 * WINDOW_WIDTH / 20);
        layout.setMinHeight((double) 5 * WINDOW_HEIGHT / 48);
        layout.setFillWidth(true);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(resetButton);
        VBox.setMargin(resetButton, new Insets(0, 0, 30, 0));

        return layout;
    }

    private VBox createRightLayout() {
        GridPane gridLayout = createTopRightLayout();
        HBox buttonsLayout = createBottomRightLayout();

        VBox rightLayout = new VBox();
        rightLayout.getChildren().addAll(gridLayout, buttonsLayout);

        return rightLayout;
    }

    private GridPane createTopRightLayout() {
        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(7));
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        gridLayout.setMinHeight((double) 3 * WINDOW_HEIGHT / 4);
        gridLayout.setAlignment(Pos.CENTER);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gridLayout.add(gridLabel[row][col], col, row);
            }
        }

        return gridLayout;
    }

    private HBox createBottomRightLayout() {
        HBox buttonsLayout = new HBox();
        buttonsLayout.setAlignment(Pos.CENTER);
        buttonsLayout.getChildren().addAll(nextButton, prevButton);
        HBox.setHgrow(nextButton, javafx.scene.layout.Priority.ALWAYS);
        buttonsLayout.setSpacing(110);

        return buttonsLayout;
    }

    private void createLabels() {
        createPromptLabels();
        createHeaderLabels();
        createValueLabels();
        createGridLabels();
    }

    private void createPromptLabels() {
        puzzleInputLabel = new Label("Enter your Puzzle in this form 123456780: ");
        puzzleInputLabel.setId(PROMPT_LABEL_ID);
        algorithmSelectionLabel = new Label("Choose a Search Algorithm: ");
        algorithmSelectionLabel.setId(PROMPT_LABEL_ID);
    }

    private void createHeaderLabels() {
        costOfPathHeaderLabel = new Label("Cost of Path = ");
        costOfPathHeaderLabel.setId(HEADER_LABEL_ID);
        searchDepthHeaderLabel = new Label("Search Depth = ");
        searchDepthHeaderLabel.setId(HEADER_LABEL_ID);
        maxDepthHeaderLabel = new Label("Max Depth = ");
        maxDepthHeaderLabel.setId(HEADER_LABEL_ID);
        expandedNodesHeaderLabel = new Label("No. of Expanded Nodes = ");
        expandedNodesHeaderLabel.setId(HEADER_LABEL_ID);
        timeHeaderLabel = new Label("Time = ");
        timeHeaderLabel.setId(HEADER_LABEL_ID);
    }

    private void createValueLabels() {
        costOfPathValueLabel = new Label("");
        costOfPathValueLabel.setId(VALUE_LABEL_ID);
        searchDepthValueLabel = new Label("");
        searchDepthValueLabel.setId(VALUE_LABEL_ID);
        maxDepthValueLabel = new Label("");
        maxDepthValueLabel.setId(VALUE_LABEL_ID);
        expandedNodesValueLabel = new Label("");
        expandedNodesValueLabel.setId(VALUE_LABEL_ID);
        timeValueLabel = new Label("");
        timeValueLabel.setId(VALUE_LABEL_ID);
    }

    private void createGridLabels() {
        gridLabel = new Label[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridLabel[i][j] = new Label("");
                gridLabel[i][j].setId(GRID_LABEL_ID);
            }
        }
    }

    private void createPuzzleTextField() {
        puzzleTextField = new TextField();
        puzzleTextField.setPromptText("Enter Your Puzzle");
    }

    private void createButtons() {
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

    private void createSearchAlgorithmsComboBox() {
        searchAlgorithmsBox = new ComboBox<>();
        searchAlgorithmsBox.getItems().addAll("DFS", "BFS", "A* using Euclidean", "A* using Manhattan");
        searchAlgorithmsBox.setValue("BFS");
    }
}
