package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LeitorTela01 {

    @FXML
    private Button SairButton;

    @FXML
    private Button emprestimoButton;

    @FXML
    private Button verificaButton;
    private Stage stage;

    @FXML
    void SairButtonAction(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void emprestimoButtonAction(ActionEvent event) {

    }

    @FXML
    void verificaButtonAction(ActionEvent event) {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
