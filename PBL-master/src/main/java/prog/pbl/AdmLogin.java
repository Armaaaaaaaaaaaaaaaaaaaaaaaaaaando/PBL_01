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

public class AdmLogin {
    private Stage stage;

    @FXML
    private Button entrarButton;

    @FXML
    private Label error;

    @FXML
    private TextField idtext;

    @FXML
    private TextField senhaText;

    @FXML
    private Button voltarbutton;


    @FXML
    void entrarButtonAction(ActionEvent event) {
        try{
            dao.findLogin(this.idtext.getText(),this.senhaText.getText());
            this.OpenTela01("AdmTela01.fxml");
            this.stage.close();


        }catch (Exception e){
            this.error.setText("Preencha os campos");
        }
    }



    @FXML
    void voltarbuttonAction(ActionEvent event) {
        this.stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }



    private void OpenTela01(String url){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Bem vindo!");
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
}
