package org.group24.truthtable;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import model.Circ;
import model.Table;
import model.VarLed;

import java.util.ArrayList;

public class AddTable extends Application {
    @FXML
    private CheckBox input0, input1, input2, input3, input4, input5, input6, input7;
    @FXML
    private CheckBox varIn0, varIn1, varIn2, varIn3;
    @FXML
    private CheckBox varOut0, varOut1, varOut2, varOut3;
    @FXML
    private CheckBox LED0, LED1, LED2, LED3;
    CheckBox[] inputs;
    CheckBox[] varIns;
    CheckBox[] varOuts;
    CheckBox[] LEDs;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationRunner.class.getResource("FXML/AddTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Logic Circuit Simulator");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    public void initialize() {
        inputs = new CheckBox[]{input0, input1, input2, input3, input4, input5, input6, input7};
        varIns = new CheckBox[]{varIn0, varIn1, varIn2, varIn3};
        varOuts = new CheckBox[]{varOut0, varOut1, varOut2, varOut3};
        LEDs = new CheckBox[]{LED0, LED1, LED2, LED3};
        for (int i = 0; i < 4; i++) {
            if (Circ.getVar(i).getOutputIn() != -1)
                varOuts[i].setDisable(true);
            if (Circ.getLed(i).getOutputIn() != -1)
                LEDs[i].setDisable(true);
        }
    }

    public void backToMainMenu() throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(App.getStage());
    }

    public void reset() throws Exception {
        this.start(App.getStage());
    }

    public void addTable() throws Exception {
        ArrayList<Integer> switches = new ArrayList<>();
        ArrayList<Integer> varIn = new ArrayList<>();
        ArrayList<VarLed> varOut = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (inputs[i].isSelected())
                switches.add(i);
        }
        for (int i = 0; i < 4; i++) {
            if (varIns[i].isSelected())
                varIn.add(i);
            if (LEDs[i].isSelected())
                varOut.add(Circ.getLed(i));
        }
        for (int i = 0; i < 4; i++) {
            if (varOuts[i].isSelected())
                varOut.add(Circ.getVar(i));
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure?");
        alert.getDialogPane().getStylesheets().
                add(ApplicationRunner.class.getResource("CSS/Confirmation.css").toExternalForm());
        alert.showAndWait();
        if (!alert.getResult().getButtonData().isCancelButton()) {
            Table.createTable(switches, varIn, varOut);
            this.start(App.getStage());
        }
    }
}
