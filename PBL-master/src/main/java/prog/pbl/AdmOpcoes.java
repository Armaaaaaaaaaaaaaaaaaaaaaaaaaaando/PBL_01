package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static prog.pbl.cadastrarAdm.dao;

public class AdmOpcoes {

    @FXML
    private Button cadastrarButton;

    @FXML
    private Button entrarButton;

    @FXML
    private TextField loginTex;

    @FXML
    private TextField senhaText;
    @FXML
    private Label error;


    @FXML
    void entrarButtonAction(ActionEvent event) {
        try{
            dao.findLogin(this.loginTex.getText(),this.senhaText.getText());
            this.OpenLogin("AdmTela01.fxml");
            this.clearAll();



        }catch (Exception e){
            this.error.setText("Preencha os campos adequadamente");
        }
    }


    private void OpenLogin(String url){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Central de Administração");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            AdmTela01 controller = loader.getController();
            controller.setStage(stage);

            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void clearAll(){
        this.error.setText("");
        this.loginTex.clear();
        this.senhaText.clear();
    }

}
