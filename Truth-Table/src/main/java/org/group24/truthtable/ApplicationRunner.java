package org.group24.truthtable;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ApplicationRunner extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MainMenu mainMenu = new MainMenu();
        App.setStage(stage);
        stage.setResizable(false);
        stage.getIcons().add(new Image(ApplicationRunner.class.getResource("Images/Icon.png").toExternalForm()));
        mainMenu.start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
