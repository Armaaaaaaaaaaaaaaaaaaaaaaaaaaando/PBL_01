package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainWindowController {

    @FXML
    private Button admButton;

    @FXML
    private Button bibliotecariobutton;

    @FXML
    private BorderPane inicio;

    @FXML
    private Button leitorButton;

    @FXML
    private Button pesquisarButton;

    @FXML
    void admButtonAction(ActionEvent event) {
        this.openPage("AdmOpcoes.fxml");

    }

    @FXML
    void bibliotecariobuttonAction(ActionEvent event) {
        this.openPage("BibliotecarioLogin.fxml");
    }

    @FXML
    void leitorButtonAction(ActionEvent event) {
        this.openPage("LeitorLogin.fxml");
    }



    @FXML
    void pesquisarButtonAction(ActionEvent event) {
        this.AbrirPesquisa("Pesquisa.fxml");
    }

    private void openPage(String url){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.inicio.setCenter(root);
    }


    private void AbrirPesquisa(String url){
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
