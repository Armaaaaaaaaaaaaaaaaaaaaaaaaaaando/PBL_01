package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LeitorLogin {

    @FXML
    private Button EntrarButton;

    @FXML
    private Label error;

    @FXML
    private TextField loginText;

    @FXML
    private TextField senhaText;

    @FXML
    void EntrarButtonAction(ActionEvent event) {
        try{
            System.out.println("Acesso liberado");
        }catch (Exception e){
            this.error.setText("Preencha os campos");
        }
    }

}
