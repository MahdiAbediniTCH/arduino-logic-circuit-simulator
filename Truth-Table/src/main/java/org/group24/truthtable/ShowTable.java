package org.group24.truthtable;

import controller.TableController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Circ;
import model.Table;
import model.VarLed;
import org.w3c.dom.css.RGBColor;

public class ShowTable extends Application {
    int tableNumber;
    VBox basePane;
    ScrollPane scrollPane;
    GridPane table;

    public ShowTable(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public void start(Stage stage) throws Exception {
        basePane = new VBox();
        setBasePaneSettings();
        addNodes();
        Scene scene = new Scene(basePane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private void setBasePaneSettings() {
        basePane.setPrefSize(400, 600);
        Image image = new Image(ApplicationRunner.class.getResource("Images/ShowTableMenu.jpg").toExternalForm()
                , 400, 600, false, false);
        basePane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        basePane.setSpacing(20);
        basePane.setAlignment(Pos.CENTER);
        basePane.getStylesheets().add(ApplicationRunner.class.getResource("CSS/ShowTable.css").toExternalForm());
    }

    private void addNodes() {
        scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxSize(350, 300);
        basePane.getChildren().add(scrollPane);
        table = new GridPane();
        table.setAlignment(Pos.CENTER);
        scrollPane.setContent(table);
        int numberOfSwitches = Table.getFromAllTables(tableNumber).getSwitchNumbers().size();
        int numberOfVars = Table.getFromAllTables(tableNumber).getVarNumbers().size();
        int numberOfOutputs = Table.getFromAllTables(tableNumber).getOutputs().size();
        Label label;
        for (int i = 0; i < numberOfSwitches; i++) {
            label = new Label("A" + Table.getFromAllTables(tableNumber).getSwitchNumbers().get(i));
            label.setStyle("-fx-text-fill: blue");
            label.setAlignment(Pos.CENTER);
            GridPane.setRowIndex(label, 0);
            GridPane.setColumnIndex(label, i);
            GridPane.setHalignment(label, HPos.CENTER);
            table.getChildren().add(label);
        }
        for (int i = 0; i < numberOfVars; i++) {
            label = new Label("C" + Table.getFromAllTables(tableNumber).getVarNumbers().get(i));
            label.setStyle("-fx-text-fill: blue");
            label.setAlignment(Pos.CENTER);
            GridPane.setRowIndex(label, 0);
            GridPane.setColumnIndex(label, i + numberOfSwitches);
            GridPane.setHalignment(label, HPos.CENTER);
            table.getChildren().add(label);
        }
        VarLed varled;
        for (int i = 0; i < numberOfOutputs; i++) {
            varled = Table.getFromAllTables(tableNumber).getOutputs().get(i);
            if (varled.IS_LED) label = new Label("LED" + varled.NUMBER);
            else label = new Label("C" + varled.NUMBER);
            label.setStyle("-fx-text-fill: red");
            label.setAlignment(Pos.CENTER);
            GridPane.setRowIndex(label, 0);
            GridPane.setColumnIndex(label, i + numberOfSwitches + numberOfVars);
            GridPane.setHalignment(label, HPos.CENTER);
            table.getChildren().add(label);
        }
        int[] binaryForm;
        for (int i = 0; i < (1 << (numberOfSwitches + numberOfVars)); i++) {
            binaryForm = TableController.convertToBinary(i, numberOfSwitches + numberOfVars);
            for (int j = 0; j < numberOfSwitches + numberOfVars; j++) {
                label = new Label(Integer.toString(binaryForm[j]));
                GridPane.setRowIndex(label, i + 1);
                GridPane.setColumnIndex(label, j);
                label.setAlignment(Pos.CENTER);
                GridPane.setHalignment(label, HPos.CENTER);
                table.getChildren().add(label);
            }
            for (int j = 0; j < numberOfOutputs; j++) {
                varled = Table.getFromAllTables(tableNumber).getOutputs().get(j);
                int number = Table.getFromAllTables(tableNumber).getOutPutStatus(varled)[i];
                label = new Label(Integer.toString(number));
                label.setAlignment(Pos.CENTER);
                GridPane.setRowIndex(label, i + 1);
                GridPane.setColumnIndex(label, j + numberOfSwitches + numberOfVars);
                GridPane.setHalignment(label, HPos.CENTER);
                table.getChildren().add(label);
                final Label finalLabel = label;
                final VarLed finalVarled = varled;
                final int finalI = i;
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        finalLabel.setText(Integer.toString(1 - Integer.parseInt(finalLabel.getText())));
                        Table.getFromAllTables(tableNumber).changeState(finalVarled, finalI);
                        Circ.resetAllEvals();
                    }
                });
                label.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        finalLabel.setBackground(Background.fill(Color.web("99FF99")));
                        finalLabel.setCursor(Cursor.HAND);
                    }
                });
                label.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        finalLabel.setBackground(Background.fill(Color.TRANSPARENT));
                        finalLabel.setCursor(Cursor.DEFAULT);
                    }
                });
            }
        }
        Button backButton = new Button("Back");
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ExistingTables existingTable = new ExistingTables();
                try {
                    existingTable.start(App.getStage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        basePane.getChildren().add(backButton);
    }
}
