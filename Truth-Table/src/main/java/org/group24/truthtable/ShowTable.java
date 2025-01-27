package org.group24.truthtable;

import controller.TableController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Circ;
import model.Table;
import model.VarLed;

public class ShowTable extends Application {
    int tableNumber;
    VBox basePane;
    GridPane table;
//    TableView<ObservableList<Label>> table;

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
        basePane.setPrefSize(600, 600);
        basePane.setSpacing(20);
        basePane.setAlignment(Pos.CENTER);
        basePane.getStylesheets().add(ApplicationRunner.class.getResource("CSS/Style.css").toExternalForm());
    }

    private void addNodes() {
        int numberOfSwitches = Table.getFromAllTables(tableNumber).getSwitchNumbers().size();
        int numberOfVars = Table.getFromAllTables(tableNumber).getVarNumbers().size();
        int numberOfOutputs = Table.getFromAllTables(tableNumber).getOutputs().size();
        table = new GridPane();
        table.setAlignment(Pos.CENTER);
        Label label;
        for (int i = 0; i < numberOfSwitches; i++) {
            label = new Label("A" + Table.getFromAllTables(tableNumber).getSwitchNumbers().get(i));
            GridPane.setRowIndex(label, 0);
            GridPane.setColumnIndex(label, i);
            label.setAlignment(Pos.CENTER);
            table.getChildren().add(label);
        }
        for (int i = 0; i < numberOfVars; i++) {
            label = new Label("C" + Table.getFromAllTables(tableNumber).getVarNumbers().get(i));
            GridPane.setRowIndex(label, 0);
            GridPane.setColumnIndex(label, i + numberOfSwitches);
            label.setAlignment(Pos.CENTER);
            table.getChildren().add(label);
        }
        VarLed varled;
        for (int i = 0; i < numberOfOutputs; i++) {
            varled = Table.getFromAllTables(tableNumber).getOutputs().get(i);
            if (varled.IS_LED) label = new Label("LED" + varled.NUMBER);
            else label = new Label("C" + varled.NUMBER);
            GridPane.setRowIndex(label, 0);
            GridPane.setColumnIndex(label, i + numberOfSwitches + numberOfVars);
            label.setAlignment(Pos.CENTER);
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
                table.getChildren().add(label);
            }
            for (int j = 0; j < numberOfOutputs; j++) {
                varled = Table.getFromAllTables(tableNumber).getOutputs().get(j);
                int number = Table.getFromAllTables(tableNumber).getOutPutStatus(varled)[i];
                label = new Label(Integer.toString(number));
                GridPane.setRowIndex(label, i + 1);
                GridPane.setColumnIndex(label, j + numberOfSwitches + numberOfVars);
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
                        finalLabel.setBackground(Background.fill(Color.GRAY));
                    }
                });
                label.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        finalLabel.setBackground(Background.fill(Color.TRANSPARENT));
                    }
                });
            }
        }
        basePane.getChildren().add(table);
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
