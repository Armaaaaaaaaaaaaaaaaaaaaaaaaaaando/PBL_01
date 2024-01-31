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

public class BibliotecarioTela01 {

    @FXML
    private Button emprestarButton;

    @FXML
    private Button pesquisarButton;

    @FXML
    private Button registrarButton;

    @FXML
    private Button sairButton;
    private Stage stage;

    @FXML
    void emprestarButtonAction(ActionEvent event) {

    }

    @FXML
    void pesquisarButtonAction(ActionEvent event) {
        this.OpenPesquisa("Pesquisa.fxml");
    }

    @FXML
    void registrarButtonAction(ActionEvent event) {
        this.Openregistro("RegistroLivro.fxml");
    }

    @FXML
    void sairButtonAction(ActionEvent event) {
        this.stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void Openregistro(String url){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Registro de livros");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            RegistroLivro controller = loader.getController();
            controller.setStage(stage);




            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void OpenPesquisa(String url){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Pesquisa");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            Pesquisa controller = loader.getController();
            controller.setStage(stage);




            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
