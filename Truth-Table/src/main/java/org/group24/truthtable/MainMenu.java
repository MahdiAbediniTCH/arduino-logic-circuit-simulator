package org.group24.truthtable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Table;

public class MainMenu extends Application {
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
        System.out.println(Table.getTableNum());
    }
}
