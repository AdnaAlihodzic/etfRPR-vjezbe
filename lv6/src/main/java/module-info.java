module com.example.lv6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}