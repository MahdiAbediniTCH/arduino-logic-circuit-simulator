package org.group24.truthtable;

import controller.CircController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import model.Circ;
import model.Table;

import java.io.IOException;

public class MainMenu extends Application {
    @FXML
    CheckBox ledDef, varDef;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationRunner.class.getResource("FXML/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Logic Circuit Simulator");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void goToAddTableMenu() throws Exception {
        AddTable addTable = new AddTable();
        addTable.start(App.getStage());
    }

    public void goToExistingTablesMenu() throws Exception {
        ExistingTables existingTables = new ExistingTables();
        existingTables.start(App.getStage());
    }

    public void export() throws IOException, InterruptedException {
        if (ledDef.isSelected()) {Circ.setDefaultLed(1);}
        else Circ.setDefaultLed(0);
        if (varDef.isSelected()) Circ.setDefaultVariable(1);
        else Circ.setDefaultVariable(0);
        Circ.resetAllEvals();
        if(!CircController.prepareLEDs()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You have a Loop in your variable definitions!");
            alert.showAndWait();
        }
        CircController.writeInFile();
        CircController.sendThroughSerial();
    }
}
