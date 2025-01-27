package org.group24.truthtable;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
        int numberOfColumns = numberOfSwitches + numberOfVars +
                Table.getFromAllTables(tableNumber).getOutputs().size();
        table = new GridPane();
        table.setAlignment(Pos.CENTER);
        Label label;
        for (int i = 0; i < numberOfSwitches; i++) {
            label = new Label("A" + Table.getFromAllTables(tableNumber).getSwitchNumbers().get(i));
            GridPane.setRowIndex(label, 0);
            GridPane.setColumnIndex(label, i);
            table.getChildren().add(label);
        }
        for (int i = 0; i < numberOfVars; i++) {
            label = new Label("C" + Table.getFromAllTables(tableNumber).getVarNumbers().get(i));
            GridPane.setRowIndex(label, 0);
            GridPane.setColumnIndex(label, i + numberOfSwitches);
            table.getChildren().add(label);
        }
        VarLed varled;
        for (int i = 0; i < numberOfOutputs; i++) {
            varled = Table.getFromAllTables(tableNumber).getOutputs().get(i);
            if (varled.IS_LED) label = new Label("LED" + varled.NUMBER);
            else label = new Label("C" + varled.NUMBER);
            GridPane.setRowIndex(label, 0);
            GridPane.setColumnIndex(label, i + numberOfSwitches + numberOfVars);
            table.getChildren().add(label);
        }

        basePane.getChildren().add(table);
    }
}
