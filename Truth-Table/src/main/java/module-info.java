module org.group24.truthtable {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires java.desktop;
    requires jdk.xml.dom;


    opens org.group24.truthtable to javafx.fxml;
    exports org.group24.truthtable;
}