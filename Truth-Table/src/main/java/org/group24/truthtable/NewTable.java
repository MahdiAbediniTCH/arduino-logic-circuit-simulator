package org.group24.truthtable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class NewTable extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationRunner.class.getResource("FXML/NewTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.requestFocus();
        stage.setTitle("Logic Circuit Simulator");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void backToMainMenu() throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(App.getStage());
    }

    public void reset() throws Exception {
        this.start(App.getStage());
    }

    public void addTable() throws Exception {
        Alert alert =  new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Are you sure?");
        alert.showAndWait();
        if (!alert.getResult().getButtonData().isCancelButton())
            this.start(App.getStage());
    }
}
