package org.group24.truthtable;

import controller.TableController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import model.Circ;
import model.Table;

public class ExistingTables extends Application {
    VBox basePane;

    @Override
    public void start(Stage stage) throws Exception {
        basePane = new VBox();
        setPaneSettings();
        addNodes();
        Scene scene = new Scene(basePane);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private void setPaneSettings() {
        basePane.setAlignment(Pos.CENTER);
        basePane.setPrefSize(400, 600);
        Image image = new Image(ApplicationRunner.class.getResource("Images/ShowTableMenu.jpg").toExternalForm()
                ,400, 600, false, false);
        basePane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        basePane.setSpacing(10);
        basePane.getStylesheets().add(ApplicationRunner.class.getResource("CSS/MainMenu.css").toExternalForm());
    }

    private void addNodes() {
        HBox row;
        Label name;
        for (int i = 0 ; i < Table.getTableNum(); i++) {
            row = new HBox();
            row.setAlignment(Pos.CENTER);
            row.setSpacing(10);
            name = new Label("Table" + i);
            row.getChildren().add(name);
            basePane.getChildren().add(row);
            Button showButton = new Button("Show");
            Button removeButton = new Button("Remove");
            row.getChildren().addAll(showButton, removeButton);
            int finalI = i;
            showButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ShowTable showTable = new ShowTable(finalI);
                    try {
                        showTable.start(App.getStage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("Are you sure?");
                    alert.showAndWait();
                    if (!alert.getResult().getButtonData().isCancelButton()) {
                        TableController.removeTable(finalI);
                        Circ.resetAllEvals();
                        ExistingTables existingTables = new ExistingTables();
                        try {
                            existingTables.start(App.getStage());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }
        Button backButton = new Button("Back");
        basePane.getChildren().add(backButton);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainMenu mainMenu = new MainMenu();
                try {
                    mainMenu.start(App.getStage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
