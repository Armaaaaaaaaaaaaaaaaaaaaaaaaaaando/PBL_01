package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdmOpcoes {

    @FXML
    private Button cadastrarButton;

    @FXML
    private Button entrarButton;

    @FXML
    void cadastrarButtonAction(ActionEvent event) {
        this.OpenCadastro("cadastrarAdm.fxml");

    }

    @FXML
    void entrarButtonAction(ActionEvent event) {
        this.OpenLogin("AdmLogin.fxml");
    }

    private void OpenCadastro(String url){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Cadastro Administrador");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            cadastrarAdm controller = loader.getController();
            controller.setStage(stage);




            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            stage.setTitle("Login Administrador");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            AdmLogin controller = loader.getController();
            controller.setStage(stage);




            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
