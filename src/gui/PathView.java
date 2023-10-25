package gui;

import agent.Node;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class PathView {
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 400;

    private final Stage stage;
    private final Scene scene;
    private final VBox container;

    private static PathView instance;

    private PathView() {
        this.stage = new Stage();
        container = new VBox();
        container.setSpacing(5);
        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setFitToWidth(true);
        this.scene = new Scene(scrollPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.stage.setScene(scene);
    }

    public static PathView getInstance() {
        if (instance == null)
            instance = new PathView();
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void createGrid(List<Node> nodeList) {
        for (Node node : nodeList) {
            GridPane grid = createGridPane(node);
            container.getChildren().add(grid);
            VBox.setVgrow(grid, Priority.ALWAYS);
            container.setAlignment(Pos.CENTER);
        }
    }

    private GridPane createGridPane(Node node) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(2));
        grid.setHgap(3);
        grid.setVgap(3);
        grid.setMaxWidth(80);
        grid.setStyle("-fx-border-color: #000000; -fx-border-width: 2px;");

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Label label = createLabel(String.valueOf(node.getTiles()[row][col]));
                grid.add(label, col, row);
            }
        }

        return grid;
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setMinWidth(25);
        label.setMinHeight(25);
        label.setStyle("-fx-background-color: #00ff00;");
        label.setFont(new Font("Arial", 12));
        label.setAlignment(javafx.geometry.Pos.CENTER);
        return label;
    }
}
