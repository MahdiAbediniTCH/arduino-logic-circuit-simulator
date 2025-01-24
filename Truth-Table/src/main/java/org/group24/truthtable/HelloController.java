package org.group24.truthtable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {
    @FXML
    Button addTable;
    @FXML
    Button currentTable;
    @FXML
    Button export;

    public void goToAddTableMenu() {
        System.out.println("Add Table Menu");
    }

    public void goToCurrentTableMenu() {
        System.out.println("Current Table Menu");
    }
}
