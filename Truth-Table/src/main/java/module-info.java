module org.group24.truthtable {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.group24.truthtable to javafx.fxml;
    exports org.group24.truthtable;
}