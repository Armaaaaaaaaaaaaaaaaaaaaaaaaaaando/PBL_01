package prog.pbl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdmTela01 {
    private Stage stage;

    @FXML
    private Button AcoesLeitor;
    @FXML
    private Button relatorioButton;

    @FXML
    private Button SairButton;

    @FXML
    private Button acoesbibliotecario;

    @FXML
    private Label entrada;

    @FXML
    void LeitorAction(ActionEvent event) {
        this.OpenAnaliseLeitor("AnaliseUsuario.fxml");
    }

    @FXML
    void SairButtonActino(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void acoesbibliotecarioaction(ActionEvent event) {
        this.OpenAnaliseBibliotecario("AnaliseBibliotecario.fxml");
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void OpenAnaliseLeitor(String url){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Analise Leitor");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            AnaliseUsuario controller = loader.getController();
            controller.setStage(stage);




            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void OpenAnaliseBibliotecario(String url){
        try{
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Analise Leitor");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            AnaliseBibliotecario controller = loader.getController();
            controller.setStage(stage);




            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void relatorioButtonAction(ActionEvent event) {
        this.OpenRelatorio("Relatorio.fxml");
    }


    private void OpenRelatorio(String url) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL xmlURL = getClass().getResource(url);
            loader.setLocation(xmlURL);

            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.setTitle("Relatorio");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.initModality(Modality.APPLICATION_MODAL); // Define a modalidade da janela

            Relatorio controller = loader.getController();
            controller.setStage(stage);


            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
