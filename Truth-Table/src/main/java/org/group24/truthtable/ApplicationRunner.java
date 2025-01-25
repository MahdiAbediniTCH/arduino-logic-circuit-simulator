package org.group24.truthtable;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationRunner extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MainMenu mainMenu = new MainMenu();
        App.setStage(stage);
        mainMenu.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
