package sample.Helpers;

import javafx.scene.control.Alert;

public class AlertHelper {

    private Alert alert;

    public AlertHelper(Alert.AlertType alertType,String title,String context) {
        this.alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(context);
        alert.showAndWait();
    }
}
