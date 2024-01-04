module com.example.lv10lv11 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ba.unsa.etf.rpr.lv10lv11 to javafx.fxml;
    exports ba.unsa.etf.rpr.lv10lv11;
}